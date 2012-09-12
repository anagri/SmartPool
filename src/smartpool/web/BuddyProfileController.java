package smartpool.web;

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
import smartpool.domain.LDAPResultSet;
import smartpool.service.BuddyService;
import smartpool.service.LDAPService;
import smartpool.web.form.CreateProfileForm;
import smartpool.web.form.CreateProfileFormValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/buddyProfile")
public class BuddyProfileController {
    private BuddyService buddyService;
    private LDAPService ldapService;

    @Autowired
    public BuddyProfileController(BuddyService buddyService, LDAPService ldapService) {
        this.buddyService = buddyService;
        this.ldapService = ldapService;
    }

    @RequestMapping(value = "/{userName:.*}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable String userName, ModelMap model) {
        model.addAttribute("buddyProfile", buddyService.getBuddy(userName));
        return "buddy/viewUserProfile";
    }

    @RequestMapping(value = "/{userName:.*}", method = RequestMethod.POST)
    public String dropFromCarpool(@PathVariable String userName, ModelMap model, HttpServletRequest request) {
//        buddyService.getBuddy(userName).getCarpools().remove("carpool");
        return "buddy/viewUserProfile";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewMyProfile(ModelMap model, HttpServletRequest request) {
        model.addAttribute("buddyProfile", buddyService.getCurrentBuddy(request));
        model.addAttribute("carpools", buddyService.getCurrentBuddy(request).getCarpools());
        return "buddy/viewUserProfile";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String renderForm(ModelMap model, HttpServletRequest request) {
        String username = buddyService.getUserNameFromCAS(request);
        LDAPResultSet ldapResultSet = ldapService.searchByUserName(username);
        CreateProfileForm createProfileForm = new CreateProfileForm(username, ldapResultSet.name, ldapResultSet.email, null, null, null, null);
        model.addAttribute("createProfileForm", createProfileForm);

        return "buddy/form";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("createProfileForm") CreateProfileForm form, BindingResult bindingResult, HttpServletRequest request) {
        new CreateProfileFormValidator().validate(form, bindingResult);

        String username = buddyService.getUserNameFromCAS(request);
        LDAPResultSet ldapResultSet = ldapService.searchByUserName(username);

        form.setUsername(username);
        form.setName(ldapResultSet.name);
        form.setEmail(ldapResultSet.email);
        if (bindingResult.hasErrors()) {
            ModelMap model = new ModelMap();
            model.addAttribute("createProfileForm", form);
            return new ModelAndView("buddy/form", model);
        }

        if (!buddyService.exists(username)) {
            buddyService.insert(form.createBuddy());
        }
        return new ModelAndView(new RedirectView("/buddyProfile", true));
    }
}
