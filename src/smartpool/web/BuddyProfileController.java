package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.service.BuddyService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/buddyProfile")
public class BuddyProfileController {
    private BuddyService buddyService;

    @Autowired
    public BuddyProfileController(BuddyService buddyService) {
        this.buddyService = buddyService;
    }

    @RequestMapping(value = "/{userName:.*}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable String userName, ModelMap model, HttpServletRequest request) {
        model.addAttribute("buddyProfile", buddyService.getBuddy(userName));
        return "buddy/viewUserProfile";
    }
}
