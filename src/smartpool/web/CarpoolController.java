package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import smartpool.common.Constants;
import smartpool.domain.Buddy;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.domain.Status;
import smartpool.service.*;
import smartpool.web.form.CarpoolUpdateForm;
import smartpool.web.form.CarpoolUpdateFormValidator;
import smartpool.web.form.CreateCarpoolForm;
import smartpool.web.form.CreateCarpoolFormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Properties;

@Controller
public class CarpoolController {

    private CarpoolService carpoolService;
    private BuddyService buddyService;
    private RouteService routeService;
    private CarpoolBuddyService carpoolBuddyService;
    private CreateCarpoolFormValidator validator;
    private MailService mailService;
    private JoinRequestService joinRequestService;
    private Properties appProperties;
    private CarpoolUpdateFormValidator updateValidator;

    @Autowired
    public CarpoolController(CarpoolService carpoolService, JoinRequestService joinRequestService, BuddyService buddyService,
                             RouteService routeService, CreateCarpoolFormValidator validator, MailService mailService,
                             CarpoolBuddyService carpoolBuddyService, Properties appProperties, CarpoolUpdateFormValidator updateValidator) {
        this.carpoolService = carpoolService;
        this.joinRequestService = joinRequestService;
        this.buddyService = buddyService;
        this.routeService = routeService;
        this.carpoolBuddyService = carpoolBuddyService;
        this.validator = validator;
        this.mailService = mailService;
        this.appProperties = appProperties;
        this.updateValidator = updateValidator;
    }

    @RequestMapping(value = "/carpool/{name}", method = RequestMethod.GET)
    public String viewCarpool(@PathVariable String name, ModelMap model, HttpServletRequest request) {
        if (!carpoolService.isValidCarpool(name)) {
            return "redirect:search";
        }
        Carpool carpool = carpoolService.getByName(name);
        String username = buddyService.getUserNameFromCAS(request);

        model.put("carpool", carpool);
        model.put("COMPANY", CabType.COMPANY);
        model.put("PERSONAL", CabType.PERSONAL);
        model.put("hasEnoughSpace", carpool.hasVacancy());
        Buddy buddy = buddyService.getBuddy(username);
        if(buddy!=null){
            model.put("isRequestSent", joinRequestService.isRequestSent(buddy, carpool.getName()));
            model.put("buddyIsInCarpool", carpoolService.hasBuddy(username, carpool));
        }
        return "carpool/view";
    }

    @RequestMapping(value = "/carpool/search", method = RequestMethod.GET)
    public String searchByLocation(ModelMap model, HttpServletRequest request) {

        String query = request.getParameter("query");
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation(query);
        model.put("searchResult", carpools);

        List<String> routePoints = routeService.getAllLocation();
        model.put("routePoints", routePoints);

        model.put("searchQuery", query);

        return "carpool/search";
    }

    @RequestMapping(value = "/carpool/create", method = RequestMethod.GET)
    public String create() {
        return "carpool/create";
    }

    @RequestMapping(value = "/carpool/create", method = RequestMethod.POST)
    public String create(@ModelAttribute CreateCarpoolForm createCarpoolForm, BindingResult bindingResult, ModelMap model, HttpServletRequest request) {
        validator.validate(createCarpoolForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.put("createCarpoolForm", createCarpoolForm);
            return "carpool/create";
        }
        Carpool carpool = createCarpoolForm.getDomainObject(buddyService.getCurrentBuddy(request));
        carpoolService.insert(carpool);
        return "redirect:/carpool/" + carpool.getName();
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public String viewDashboard(@ModelAttribute("updateCarpoolForm") CarpoolUpdateForm updateForm, ModelMap model, HttpServletRequest request) {
        searchByLocation(model, request);
        model.put("errors", false);
        return "admin/dashboard";
    }

    @RequestMapping(value = "/admin/{carpoolName}/{buddyUserName}/delete")
    public String deleteBuddy(@PathVariable String carpoolName,@PathVariable String buddyUserName) {
        carpoolBuddyService.delete(carpoolName,buddyUserName);
        return "redirect:/admin/dashboard";
    }

    @RequestMapping(value = "/carpool/{name}/start", method = RequestMethod.GET)
    public String startCarpool(@PathVariable String name, HttpServletRequest request) {
        String domainUrl =  String.format(Constants.APPLICATION_PATH, request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath());
        String acceptLink = String.format(Constants.ACCEPT_START_CARPOOL_REQUEST, domainUrl, name);
        String rejectLink = String.format(Constants.REJECT_START_CARPOOL_REQUEST, domainUrl, name);
        String subject = String.format(Constants.START_CARPOOL_NOTIFICATION_SUBJECT, name);
        String message = String.format(Constants.START_CARPOOL_NOTIFICATION_MESSAGE, name, acceptLink, rejectLink);

        mailService.sendMailTo(appProperties.getProperty(Constants.ADMIN_EMAIL), subject, message);
        carpoolService.updateRequestSent(name, true);
        return "redirect:/carpool/" + name;
    }

    @RequestMapping(value = "/carpool/{name}/acceptStartRequest", method = RequestMethod.GET)
    public String acceptStartRequest(@PathVariable String name) {
        carpoolService.updateStatus(name, Status.ACTIVE);

        return "redirect:/carpool/" + name;
    }

    @RequestMapping(value = "/carpool/{name}/rejectStartRequest", method = RequestMethod.GET)
    public String rejectStartRequest(@PathVariable String name) {
        carpoolService.updateRequestSent(name, false);

        return "redirect:/carpool/" + name;

    }
    @RequestMapping(value = "/admin/dashboard/{carpoolName}/update", method = RequestMethod.POST)
    public ModelAndView updateCarpoolAttributes(@PathVariable String carpoolName,
                                                @ModelAttribute("updateCarpoolForm") CarpoolUpdateForm updateForm,
                                                BindingResult bindingResult,
                                                ModelMap model, HttpServletRequest request) {
        if (!carpoolService.isValidCarpool(carpoolName)) {
            return new ModelAndView(new RedirectView("../../"), model);

        }
        updateValidator.validate(updateForm, bindingResult);

        model.put("updateCarpoolForm", updateForm);
        model.put("errors", bindingResult.hasErrors());
        model.put("carpoolName", carpoolName);

        if (bindingResult.hasErrors()) {
            searchByLocation(model, request);
            return new ModelAndView("admin/dashboard", model);
        }
        searchByLocation(model, request);

        Carpool carpool = carpoolService.getByName(carpoolName);
        Carpool updated = updateForm.createDomainObject(carpool);
        carpoolService.updateCarpool(updated);

        return new ModelAndView(new RedirectView("../../"), model);
    }
}
