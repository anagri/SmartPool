package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import smartpool.domain.Carpool;
import smartpool.domain.Status;
import smartpool.service.CarpoolService;

import java.util.List;

@Controller
public class CarpoolController {

    private CarpoolService carpoolService;

    @Autowired
    public CarpoolController(CarpoolService carpoolService) {
        this.carpoolService = carpoolService;
    }

    @RequestMapping(value = "/carpool/{name}", method = RequestMethod.GET)
    public String viewCarpool(@PathVariable String name, ModelMap model) {
        Carpool carpool = carpoolService.getByName(name);
        model.put("carpool", carpool);

        return "carpool/view";
    }
    @RequestMapping(value = "/carpool/", method = RequestMethod.GET)
    public String searchByLocation(ModelMap model) {
        return searchByLocation("", model);
    }

    @RequestMapping(value = "/carpool/searchCarpool", method = RequestMethod.GET)
    public String searchCarpool(ModelMap modelMap) {
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("Diamond District ");
        modelMap.put("searchResult", carpools);

        return "carpool/search";
    }

    @RequestMapping(value = "/carpool/search", method = RequestMethod.GET)
    public String searchByLocation(@RequestParam String query, ModelMap model) {
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation(query);
        model.put("searchResult", carpools);

        return "carpool/search";
    }

    @RequestMapping(value = "/carpool/create", method = RequestMethod.GET)
    public String create() {
        return "carpool/create";
    }

    @RequestMapping(value = "/carpool/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Carpool carpool, @RequestParam String startDate,@RequestParam String officeETA,@RequestParam String officeETD, ModelMap model) {
        carpool.setStartDate(startDate);
        carpool.setOfficeETA(officeETA);
        carpool.setOfficeETD(officeETD);
        carpool.setStatus(Status.PENDING);
        carpoolService.insert(carpool);
        return "redirect:/carpool/"+carpool.getName();
    }
}
