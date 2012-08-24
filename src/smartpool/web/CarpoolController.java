package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.domain.Carpool;
import smartpool.service.CarpoolService;

@Controller
public class CarpoolController {

    private CarpoolService carpoolService;

    @Autowired
    public CarpoolController(CarpoolService carpoolService) {
        this.carpoolService = carpoolService;
    }

    @RequestMapping(value = "/viewcarpool.htm/{name}", method = RequestMethod.GET)
    public String viewCarpool(@PathVariable String name, ModelMap model){
        Carpool carpool = carpoolService.findCarpoolBy(name);
        model.put("carpool",carpool);

        return "carpool/view";
    }
}
