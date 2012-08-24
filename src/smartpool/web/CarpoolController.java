package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import smartpool.domain.Carpool;
import smartpool.service.CarpoolService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CarpoolController {

    private CarpoolService carpoolService;

    @Autowired
    public CarpoolController(CarpoolService carpoolService) {
        this.carpoolService = carpoolService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String carpoolName = request.getParameter("name");
        Carpool carpool = carpoolService.findCarpoolBy(carpoolName);
        return new ModelAndView("carpool/view", "carpool", carpool);
    }
}
