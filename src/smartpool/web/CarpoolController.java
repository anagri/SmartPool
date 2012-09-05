package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import smartpool.common.Constants;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.Status;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.RouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        boolean buddyIsInCarpool = carpoolService.hasBuddy(buddyService.getUserNameFromCAS(request), carpool);
        model.put("carpool", carpool);
        model.put("buddyIsInCarpool", buddyIsInCarpool);

        return "carpool/view";
    }

    @RequestMapping(value = "/carpool/{name}", method = RequestMethod.POST)
    public String joinCarpool(@PathVariable String name) {
        return "redirect:/carpool/"+name+"/join";
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
    public String create(@ModelAttribute Carpool carpool, @RequestParam String startDateForm, @RequestParam String officeETAForm, @RequestParam String officeETDForm, ModelMap model, HttpServletRequest request) {
        carpool.setStartDate(Constants.DATE_FORMATTER.parseLocalDate(startDateForm));
        carpool.setOfficeETA(Constants.TIME_FORMATTER.parseLocalTime(officeETAForm));
        carpool.setOfficeETD(Constants.TIME_FORMATTER.parseLocalTime(officeETDForm));
        carpool.setStatus(Status.PENDING);
        ArrayList<Buddy> buddies = new ArrayList<Buddy>();
        buddies.add(buddyService.getCurrentBuddy(request));
        carpool.setBuddies(buddies);
        carpoolService.insert(carpool);
        return "redirect:/carpool/"+carpool.getName();
    }


}
