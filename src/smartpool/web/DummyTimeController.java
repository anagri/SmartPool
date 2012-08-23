package smartpool.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.mvc.*;
import smartpool.service.DummyTimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DummyTimeController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        String time = (new DummyTimeService()).getTime();

        return new ModelAndView("hello", "time", time);
    }
}
