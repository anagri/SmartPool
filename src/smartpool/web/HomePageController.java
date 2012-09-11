package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.service.BuddyService;
import smartpool.service.LDAPService;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class HomePageController {
    private LDAPService ldapService;
    private BuddyService buddyService;

    @Autowired
    public HomePageController(LDAPService ldapService, BuddyService buddyService) {
        this.ldapService = ldapService;
        this.buddyService = buddyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String userNameFromCAS = buddyService.getUserNameFromCAS(request);
        request.getSession().setAttribute("ldapUserName", ldapService.searchByUserName(userNameFromCAS).name);
        return "index";
    }
}


