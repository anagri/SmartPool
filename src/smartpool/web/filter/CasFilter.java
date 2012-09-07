package smartpool.web.filter;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import smartpool.common.Constants;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class CasFilter extends GenericFilterBean {

    @Autowired
    private Properties appProperties;

    private CASFilter delegate = new CASFilter();

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        delegate.init(new FilterConfig() {
            @Override
            public String getFilterName() {
                return getFilterConfig().getFilterName();
            }

            @Override
            public ServletContext getServletContext() {
                return getFilterConfig().getServletContext();
            }

            @Override
            public String getInitParameter(String name) {
                if (name.equals("edu.yale.its.tp.cas.client.filter.loginUrl")){
                    return String.format("%s/login", appProperties.getProperty(Constants.CAS_SERVER_URL));
                }
                if (name.equals("edu.yale.its.tp.cas.client.filter.validateUrl")){
                    return String.format("%s/serviceValidate", appProperties.getProperty(Constants.CAS_SERVER_URL));
                }
                if (name.equals("edu.yale.its.tp.cas.client.filter.serverName")){
                    return String.format("%s:%s", appProperties.getProperty(Constants.HOST_NAME), appProperties.getProperty(Constants.PORT_NUMBER));
                }
                if (name.startsWith("edu.yale.its.tp.cas.client.filter")){
                    return null;
                }
                return getFilterConfig().getInitParameter(name);
            }

            @Override
            public Enumeration getInitParameterNames() {
                return getFilterConfig().getInitParameterNames();
            }
        });
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        delegate.doFilter(request, response, chain);
    }
}
