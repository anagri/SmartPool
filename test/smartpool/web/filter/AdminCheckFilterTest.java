package smartpool.web.filter;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.common.Constants;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminCheckFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private Properties adminProperties;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(adminProperties.getProperty("admins")).thenReturn("adminUser,adminUser2");
    }

    @Test
    public void shouldRedirectNotAdminToErrorPage() throws Exception {
        when(session.getAttribute(CASFilter.CAS_FILTER_USER)).thenReturn("notAdminUser");
        new AdminCheckFilter(adminProperties).doFilter(request,response,chain);
        verify(response).sendError(403, Constants.MSG_403);
    }

    @Test
    public void shouldNotRedirectAdminToErrorPage() throws Exception {
        when(session.getAttribute(CASFilter.CAS_FILTER_USER)).thenReturn("adminUser");
        new AdminCheckFilter(adminProperties).doFilter(request,response,chain);
        verify(response,never()).sendError(403, Constants.MSG_403);
    }

    @Test
    public void shouldNotRedirectAdmin2ToErrorPage() throws Exception {
        when(session.getAttribute(CASFilter.CAS_FILTER_USER)).thenReturn("adminUser2");
        new AdminCheckFilter(adminProperties).doFilter(request,response,chain);
        verify(response,never()).sendError(403, Constants.MSG_403);
    }
}
