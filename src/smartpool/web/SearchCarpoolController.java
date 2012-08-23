package smartpool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import smartpool.domain.Carpool;
import smartpool.service.SearchCarpoolService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class SearchCarpoolController {


    private SearchCarpoolService searchCarpoolService;


    @Autowired
    public SearchCarpoolController(SearchCarpoolService searchCarpoolService) {
        this.searchCarpoolService = searchCarpoolService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String searchCarpool = request.getParameter("pickupPoint");
        Carpool carpool = searchCarpoolService.searchCarpoolsBy(searchCarpool.toLowerCase());
        return new ModelAndView("carpool/search", "carpool", carpool);
    }
}
