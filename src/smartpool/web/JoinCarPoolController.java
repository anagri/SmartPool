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
import smartpool.service.BuddyService;
import smartpool.service.JoinRequestService;
import smartpool.web.form.JoinRequestForm;
import smartpool.web.form.JoinRequestFormValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JoinCarPoolController {
    private BuddyService buddyService;
    private JoinRequestService joinRequestService;
    private final JoinRequestFormValidator validator;

    @Autowired
    public JoinCarPoolController(BuddyService buddyService, JoinRequestService joinRequestService, JoinRequestFormValidator validator) {
        this.buddyService = buddyService;
        this.joinRequestService = joinRequestService;
        this.validator = validator;
    }


    @RequestMapping(value = "carpool/{carpoolName}/join", method = RequestMethod.GET)
    public String getUserDetails(@PathVariable String carpoolName, ModelMap model, HttpServletRequest request) {
        String userName = getCurrentUserNameFromRequest(request);
        Buddy buddy = buddyService.getBuddy(userName);
        JoinRequestForm joinRequestForm = new JoinRequestForm(buddy, carpoolName);
        model.put("buddy", buddy);
        model.put("joinRequestForm", joinRequestForm);
        model.put("isRequestSent", joinRequestService.isRequestSent(buddy, carpoolName));
        return "carpool/joinRequest";
    }

    @RequestMapping(value = "carpool/{carpoolName}/join", method = RequestMethod.POST)
    public ModelAndView submitUserDetails(@PathVariable String carpoolName,
                                          @ModelAttribute("joinRequestForm") JoinRequestForm joinRequestForm,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        joinRequestForm.setUsername(getCurrentUserNameFromRequest(request));
        joinRequestForm.setCarpoolName(carpoolName);

        validator.validate(joinRequestForm, bindingResult);
        if (bindingResult.hasErrors()) {
            Buddy buddy = buddyService.getBuddy(getCurrentUserNameFromRequest(request));
            ModelMap model = new ModelMap();
            model.put("buddy", buddy);
            model.put("joinRequestForm", joinRequestForm);
            model.put("isRequestSent", joinRequestService.isRequestSent(buddy, carpoolName));
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
