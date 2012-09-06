package smartpool.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import smartpool.service.BuddyService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static smartpool.service.BuddyService.extractUsernameFromRequest;


public class ProfileCheckFilter extends GenericFilterBean {
    @Autowired
    private BuddyService buddyService;

    public ProfileCheckFilter() {
    }

    public ProfileCheckFilter(BuddyService buddyService) {
        this.buddyService = buddyService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!buddyService.exists(extractUsernameFromRequest(request))) {
            ((HttpServletResponse) servletResponse).sendRedirect(format("%s/buddy/create", request.getContextPath()));
            return;
        }
        chain.doFilter(request, response);
    }
}
