package smartpool.web;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.*;

@RequestMapping("/")
@Controller
public class HomePageController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute(CASFilter.CAS_FILTER_USER));
        return "index";
    }
}


