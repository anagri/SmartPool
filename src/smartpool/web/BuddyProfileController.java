package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.service.BuddyProfileService;

@Controller
@RequestMapping("/buddyProfile")
public class BuddyProfileController {
    private BuddyProfileService buddyProfileService;

    @Autowired
    public BuddyProfileController(BuddyProfileService buddyProfileService) {

        this.buddyProfileService = buddyProfileService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable int id, ModelMap model) {
        model.addAttribute("buddyProfile",buddyProfileService.findBuddyProfile(id));
        return "viewProfile";
    }
}
