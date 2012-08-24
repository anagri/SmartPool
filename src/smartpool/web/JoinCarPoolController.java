package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.domain.Buddy;
import smartpool.service.BuddyService;

@Controller
@RequestMapping("/viewcarpool/{name}/join")
public class JoinCarPoolController {
    private BuddyService buddyService;

    @Autowired
    public JoinCarPoolController(BuddyService buddyService) {
        this.buddyService=buddyService;
    }

    @RequestMapping(value = "/viewcarpool/{name}/join", method = RequestMethod.GET)
    public String joinCarPool(@PathVariable String name, ModelMap model){
        String userName = buddyService.getUserName();
         Buddy buddy=new Buddy(userName);

        model.put("buddy",buddy);

        return "carpool/JoinRequest";
    }
}
