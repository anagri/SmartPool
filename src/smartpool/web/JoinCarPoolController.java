package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;
import smartpool.service.BuddyService;
import smartpool.service.JoinRequestService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JoinCarPoolController {
    private BuddyService buddyService;
    private JoinRequestService joinRequestService;

    @Autowired
    public JoinCarPoolController(BuddyService buddyService, JoinRequestService joinRequestService) {
        this.buddyService = buddyService;
        this.joinRequestService = joinRequestService;
    }

    @RequestMapping(value = "carpool/{carpoolName}/join", method = RequestMethod.GET)
    public String getUserDetails(@PathVariable String carpoolName, ModelMap model, HttpServletRequest request){
        String userName = buddyService.getUserNameFromCAS(request);
        Buddy buddy = buddyService.getBuddy(userName);
        model.put("buddy",buddy);
        model.put("carpoolName", carpoolName);
        model.put("casUserName", userName);
        model.put("isRequestSent", joinRequestService.isRequestSent(buddy, carpoolName));
        return "carpool/joinRequest";
    }

    @RequestMapping(value = "carpool/{name}/join", method = RequestMethod.POST)
    public String submitUserDetails(@PathVariable String name, @ModelAttribute("request")JoinRequest joinRequest, ModelMap model){
        joinRequest.setCarpoolName(name);
        joinRequestService.sendJoinRequest(joinRequest);
        model.put("request",joinRequest);
        return "redirect:../../carpool/"+name;
    }
}
