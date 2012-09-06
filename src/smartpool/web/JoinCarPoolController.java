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
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.JoinRequestService;
import smartpool.web.form.JoinRequestForm;
import smartpool.web.form.JoinRequestFormValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JoinCarPoolController {
    private BuddyService buddyService;
    private JoinRequestService joinRequestService;
    private CarpoolService carpoolService;
    private final JoinRequestFormValidator validator;

    @Autowired
    public JoinCarPoolController(BuddyService buddyService,
                                 JoinRequestService joinRequestService,
                                 CarpoolService carpoolService,
                                 JoinRequestFormValidator validator) {
        this.buddyService = buddyService;
        this.joinRequestService = joinRequestService;
        this.carpoolService = carpoolService;
        this.validator = validator;
    }


    @RequestMapping(value = "carpool/join/{carpoolName}", method = RequestMethod.GET)
    public String getUserDetails(@PathVariable String carpoolName, ModelMap model, HttpServletRequest request) {
        String userName = getCurrentUserNameFromRequest(request);

        Carpool carpool = carpoolService.getByName(carpoolName);
        if (carpool == null) {
            return "redirect:/carpool/search";
        }
        carpoolService.canUserSendRequest(userName, carpool);

        Buddy buddy = buddyService.getBuddy(userName);
        JoinRequestForm joinRequestForm = new JoinRequestForm(buddy, carpoolName);
        model.put("buddy", buddy);
        model.put("carpoolName", carpoolName);
        model.put("joinRequestForm", joinRequestForm);
        model.put("isRequestSent", joinRequestService.isRequestSent(buddy, carpoolName));

        return "carpool/joinRequest";
    }

    @RequestMapping(value = "carpool/join/{carpoolName}", method = RequestMethod.POST)
    public ModelAndView submitUserDetails(@PathVariable String carpoolName,
                                          @ModelAttribute("joinRequestForm") JoinRequestForm joinRequestForm,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        String username = getCurrentUserNameFromRequest(request);

        joinRequestForm.setUsername(username);
        joinRequestForm.setCarpoolName(carpoolName);
        Buddy buddy = buddyService.getBuddy(username);

        boolean requestSent = joinRequestService.isRequestSent(buddy, carpoolName);

        validator.validate(joinRequestForm, bindingResult);
        if (requestSent || bindingResult.hasErrors()) {
            ModelMap model = new ModelMap();
            model.put("buddy", buddy);
            model.put("joinRequestForm", joinRequestForm);
            model.put("isRequestSent", requestSent);
            return new ModelAndView("carpool/joinRequest", model);
        } else {
            joinRequestService.sendJoinRequest(joinRequestForm.createDomainObject());
            return new ModelAndView(new RedirectView("../../carpool/" + carpoolName));
        }
    }


    private String getCurrentUserNameFromRequest(HttpServletRequest request) {
        return buddyService.getUserNameFromCAS(request);
    }

}
