package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.service.BuddyService;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class HomePageController {
    private BuddyService buddyService;

    @Autowired
    public HomePageController(BuddyService buddyService)
    {
        this.buddyService=buddyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        model.put("username", buddyService.getUserNameFromCAS(request));
        return "index";
    }
}


