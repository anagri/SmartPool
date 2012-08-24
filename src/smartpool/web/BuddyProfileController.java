package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.service.BuddyService;

@Controller
@RequestMapping("/buddyProfile")
public class BuddyProfileController {
    private BuddyService buddyService;

    @Autowired
    public BuddyProfileController(BuddyService buddyService) {

        this.buddyService = buddyService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable int id, ModelMap model) {
        model.addAttribute("buddyProfile",buddyService.findBuddyProfile(id));
        return "viewProfile";
    }
}