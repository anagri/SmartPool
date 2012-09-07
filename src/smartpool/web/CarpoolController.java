package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.RouteService;
import smartpool.web.form.CreateCarpoolForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CarpoolController {

    private CarpoolService carpoolService;
    private BuddyService buddyService;
    private RouteService routeService;

    @Autowired
    public CarpoolController(CarpoolService carpoolService, BuddyService buddyService, RouteService routeService) {

        this.carpoolService = carpoolService;
        this.buddyService = buddyService;
        this.routeService = routeService;
    }

    @RequestMapping(value = "/carpool/{name}", method = RequestMethod.GET)
    public String viewCarpool(@PathVariable String name, ModelMap model, HttpServletRequest request) {
        Carpool carpool = carpoolService.getByName(name);
        String username = buddyService.getUserNameFromCAS(request);
//        boolean buddyIsInCarpool = carpoolService.hasBuddy(buddyService.getUserNameFromCAS(request), carpool);


        model.put("carpool", carpool);
        model.put("COMPANY", CabType.COMPANY);
        model.put("PERSONAL", CabType.PERSONAL);
        model.put("hasEnoughSpace", carpool.hasVacancy());
        model.put("buddyIsInCarpool", carpoolService.hasBuddy(username, carpool));
//        model.put("buddyIsInCarpool", buddyIsInCarpool);
        return "carpool/view";
    }

    @RequestMapping(value = "/carpool/search", method = RequestMethod.GET)
    public String searchByLocation(ModelMap model, HttpServletRequest request) {

        String query = request.getParameter("query");
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation(query);
        model.put("searchResult", carpools);

        List<String> routePoints = routeService.getAllLocation();
        model.put("routePoints", routePoints);

        model.put("searchQuery", query);

        return "carpool/search";
    }

    @RequestMapping(value = "/carpool/create", method = RequestMethod.GET)
    public String create() {
        return "carpool/create";
    }

    @RequestMapping(value = "/carpool/create", method = RequestMethod.POST)
    public String create(@ModelAttribute CreateCarpoolForm createCarpoolForm, ModelMap model, HttpServletRequest request) {
        Carpool carpool = createCarpoolForm.getDomainObject(buddyService.getCurrentBuddy(request));
        carpoolService.insert(carpool);
        return "redirect:/carpool/" + carpool.getName();
    }


}
