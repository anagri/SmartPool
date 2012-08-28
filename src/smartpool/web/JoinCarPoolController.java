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

@Controller
public class JoinCarPoolController {
    private BuddyService buddyService;

    @Autowired
    public JoinCarPoolController(BuddyService buddyService) {
        this.buddyService = buddyService;
    }

    @RequestMapping(value = "/carpool/{name}/join", method = RequestMethod.GET)
    public String getUserDetails(@PathVariable String name, ModelMap model){
        Buddy buddy=buddyService.buildBuddy("1");
        model.put("buddy",buddy);
        return "carpool/JoinRequest";
    }

    @RequestMapping(value = "/carpool/{name}/join", method = RequestMethod.POST)
    public String submitUserDetails(@PathVariable String name, @ModelAttribute("request")JoinRequest joinRequest){
        return "carpool/view";
    }



}
