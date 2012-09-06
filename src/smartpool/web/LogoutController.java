package smartpool.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.util.EnvironmentLoader;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    private EnvironmentLoader environmentLoader;

    // called by spring
    public LogoutController() {
        this(new EnvironmentLoader());
    }

    public LogoutController(EnvironmentLoader environmentLoader) {
        this.environmentLoader = environmentLoader;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpRequest){
        httpRequest.getSession().invalidate();
        String casServerURL = environmentLoader.getProperty(EnvironmentLoader.CAS_SERVER_URL);
        return String.format("redirect:%s/logout", casServerURL);
    }
}
