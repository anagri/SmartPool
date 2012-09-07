package smartpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smartpool.common.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Controller
public class LogoutController {

    private Properties appProperties;

    @Autowired
    public LogoutController(Properties appProperties) {
        this.appProperties = appProperties;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpRequest){
        httpRequest.getSession().invalidate();
        String casServerURL = appProperties.getProperty(Constants.CAS_SERVER_URL);
        return String.format("redirect:%s/logout", casServerURL);
    }
}
