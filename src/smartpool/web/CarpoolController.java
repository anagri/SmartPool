package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.domain.Buddy;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.JoinRequestService;
import smartpool.service.RouteService;
import smartpool.web.form.CreateCarpoolForm;
import smartpool.web.form.CreateCarpoolFormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CarpoolController {

    private CarpoolService carpoolService;
    private BuddyService buddyService;
    private RouteService routeService;
    private CreateCarpoolFormValidator validator;
    private JoinRequestService joinRequestService;

    @Autowired
    public CarpoolController(CarpoolService carpoolService, JoinRequestService joinRequestService,BuddyService buddyService, RouteService routeService, CreateCarpoolFormValidator validator) {

        this.carpoolService = carpoolService;
        this.joinRequestService = joinRequestService;
        this.buddyService = buddyService;
        this.routeService = routeService;
        this.validator = validator;
    }

    @RequestMapping(value = "/carpool/{name}", method = RequestMethod.GET)
    public String viewCarpool(@PathVariable String name, ModelMap model, HttpServletRequest request) {
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
        validator.validate(createCarpoolForm,bindingResult);
        if(bindingResult.hasErrors()){
            model.put("createCarpoolForm",createCarpoolForm);
            return "carpool/create";
        }
        Carpool carpool = createCarpoolForm.getDomainObject(buddyService.getCurrentBuddy(request));
        carpoolService.insert(carpool);
        return "redirect:/carpool/" + carpool.getName();
    }


}
