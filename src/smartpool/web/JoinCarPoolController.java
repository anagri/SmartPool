package smartpool.web;

import org.joda.time.LocalTime;
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
import smartpool.domain.CarpoolBuddy;
import smartpool.domain.JoinRequest;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolBuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.JoinRequestService;
import smartpool.web.form.JoinRequestForm;
import smartpool.web.form.JoinRequestFormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class JoinCarPoolController {
    private BuddyService buddyService;
    private JoinRequestService joinRequestService;
    private CarpoolService carpoolService;
    private final JoinRequestFormValidator validator;
    private CarpoolBuddyService carpoolBuddyService;

    @Autowired
    public JoinCarPoolController(BuddyService buddyService,
                                 JoinRequestService joinRequestService,
                                 CarpoolService carpoolService,
                                 JoinRequestFormValidator validator,
                                 CarpoolBuddyService carpoolBuddyService) {
        this.buddyService = buddyService;
        this.joinRequestService = joinRequestService;
        this.carpoolService = carpoolService;
        this.validator = validator;
        this.carpoolBuddyService = carpoolBuddyService;
    }

    @RequestMapping(value = "carpool/join/{carpoolName}", method = RequestMethod.GET)
    public String getUserDetails(@PathVariable String carpoolName, ModelMap model, HttpServletRequest request) {
        String username = getCurrentUserNameFromRequest(request);
        if (!isJoinRequestPossible(username, carpoolName)) {
            return "redirect:/carpool/search";
        }
        carpoolService.canUserSendRequest(username, carpoolName);

        CarpoolBuddy carpoolBuddy = new CarpoolBuddy(buddyService.getBuddy(username), "pickupPoint", new LocalTime(10, 0));
        JoinRequestForm joinRequestForm = new JoinRequestForm(carpoolBuddy, carpoolName);
        model.put("buddy", carpoolBuddy.getBuddy());
        model.put("carpoolName", carpoolName);
        model.put("joinRequestForm", joinRequestForm);
        model.put("isRequestSent", joinRequestService.isRequestSent(carpoolBuddy.getBuddy(), carpoolName));
        return "carpool/joinRequest";
    }

    @RequestMapping(value = "carpool/join/{carpoolName}", method = RequestMethod.POST)
    public ModelAndView submitUserDetails(@PathVariable String carpoolName,
                                          @ModelAttribute("joinRequestForm") JoinRequestForm joinRequestForm,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        String username = getCurrentUserNameFromRequest(request);
        if (!isJoinRequestPossible(username, carpoolName)) {
            return new ModelAndView(new RedirectView("../../carpool/search"));
        }
        joinRequestForm.setUsername(username);
        joinRequestForm.setCarpoolName(carpoolName);
        Buddy buddy = buddyService.getBuddy(username);
        boolean requestSent = joinRequestService.isRequestSent(buddy, carpoolName);
        validator.validate(joinRequestForm, bindingResult);
        String pickUpTimeStr = joinRequestForm.getPreferredPickupTime();
        LocalTime pickUpTime;
        if(pickUpTimeStr.length() < 5) {
            pickUpTime = new LocalTime(0, 0);
        } else {
            pickUpTime = new LocalTime(Integer.parseInt(pickUpTimeStr.substring(0, 2)),
                                             Integer.parseInt(pickUpTimeStr.substring(3, 5)));
        }
        if(pickUpTime.isAfter(carpoolService.getByName(carpoolName).getOfficeETA())){
            bindingResult.rejectValue("preferredPickupTime", "field.invalid");
        }
        if (requestSent || bindingResult.hasErrors()) {
            ModelMap model = new ModelMap();
            model.put("buddy", buddy);
            model.put("joinRequestForm", joinRequestForm);
            model.put("carpoolName", carpoolName);
            model.put("isRequestSent", requestSent);
            return new ModelAndView("carpool/joinRequest", model);
        } else {
            joinRequestService.sendJoinRequest(joinRequestForm.createDomainObject(), buddy, generateUid());
            return new ModelAndView(new RedirectView("../../carpool/" + carpoolName));
        }
    }

    public boolean isJoinRequestPossible(String username, String carpoolName) {
        return carpoolService.isValidCarpool(carpoolName) && carpoolService.canUserSendRequest(username, carpoolName);
    }

    private String getCurrentUserNameFromRequest(HttpServletRequest request) {
        return buddyService.getUserNameFromCAS(request);
    }

    public UUID generateUid() {
        return UUID.randomUUID();
    }

    @RequestMapping(value = "carpool/approve/{uid}", method = RequestMethod.GET)
    public ModelAndView approveJoinRequest(@PathVariable String uid) {
        ModelMap model = new ModelMap();
        model.put("isUidPresent", joinRequestService.isUidPresent(uid));
        if (joinRequestService.isUidPresent(uid)) {

            String buddyUserName = joinRequestService.getBuddyUserNameFromUid(uid);
            String carpoolName = joinRequestService.getCarpoolNameFromUid(uid);

            Buddy buddy = buddyService.getBuddy(buddyUserName);
            JoinRequest joinRequest = joinRequestService.getJoinRequestByUserNameAndCarpoolName(buddyUserName, carpoolName);
            Carpool carpool = carpoolService.findCarpoolByName(carpoolName);

            CarpoolBuddy carpoolBuddy = new CarpoolBuddy(buddy, joinRequest.getPickupPoint(), joinRequest.getPreferredPickupTime());
            carpoolBuddyService.insert(carpoolBuddy, carpool);

            joinRequestService.deletePendingRequest(uid);
            model.put("buddy",buddy);
            model.put("carpool",carpool);
            model.put("approve",true);
        }
        return new ModelAndView("carpool/approve",model);
    }

    @RequestMapping(value = "carpool/disapprove/{uid}", method = RequestMethod.GET)
    public ModelAndView disapproveJoinRequest(@PathVariable String uid) {
        ModelMap model = new ModelMap();
        model.put("isUidPresent", joinRequestService.isUidPresent(uid));
        if (joinRequestService.isUidPresent(uid)) {
            String buddyUserName = joinRequestService.getBuddyUserNameFromUid(uid);
            String carpoolName = joinRequestService.getCarpoolNameFromUid(uid);

            Buddy buddy = buddyService.getBuddy(buddyUserName);
            Carpool carpool = carpoolService.findCarpoolByName(carpoolName);

            joinRequestService.deletePendingRequest(uid);
            model.put("buddy",buddy);
            model.put("carpool",carpool);
            model.put("approve",false);
        }

        return new ModelAndView("carpool/approve",model);

    }

}
