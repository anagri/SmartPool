package smartpool.web.filter;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import smartpool.common.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class AdminCheckFilter extends GenericFilterBean {

    @Autowired
    private Properties adminProperties;

    public AdminCheckFilter() {
    }

    public AdminCheckFilter(Properties adminProperties) {
        this.adminProperties = adminProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String currentUser = (String) ((HttpServletRequest) request).getSession().getAttribute(CASFilter.CAS_FILTER_USER);
        String[] admins = adminProperties.getProperty("admins").split(",");

        if (Constants.isAdministrator(currentUser, admins)) {
            chain.doFilter(request,response);
            return;
        }
        ((HttpServletResponse) response).sendError(403, Constants.MSG_403);
        chain.doFilter(request,response);
    }

}
