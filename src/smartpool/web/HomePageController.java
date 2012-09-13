package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.common.Constants;
import smartpool.service.BuddyService;
import smartpool.service.LDAPService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Properties;

@RequestMapping("/")
@Controller
public class HomePageController {
    private LDAPService ldapService;
    private BuddyService buddyService;
    private Properties adminProperties;


    @Autowired
    public HomePageController(LDAPService ldapService, BuddyService buddyService, Properties adminProperties) {
        this.ldapService = ldapService;
        this.buddyService = buddyService;
        this.adminProperties = adminProperties;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String userNameFromCAS = buddyService.getUserNameFromCAS(request);
        String[] admins = adminProperties.getProperty("admins").split(",");
        request.getSession().setAttribute("ldapUserName", ldapService.searchByUserName(userNameFromCAS).name);
        model.put("adminUserNames", admins);

        boolean isAdministrator = Constants.isAdministrator(userNameFromCAS, admins);
        request.getSession().setAttribute("isAdmin", isAdministrator);
        if (isAdministrator) {
            return "redirect:admin/dashboard";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "whycarpool", method = RequestMethod.GET)
    public String whyCarpool(HttpServletRequest request, ModelMap model) {
        return "whycarpool";
    }

    @RequestMapping(value = "etiquette", method = RequestMethod.GET)
    public String etiquette(HttpServletRequest request, ModelMap model) {
        return "etiquette";
    }

    @RequestMapping(value = "calculator", method = RequestMethod.GET)
    public String calculator(HttpServletRequest request, ModelMap model) {
        return "calculator";
    }
}


