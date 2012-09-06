package smartpool.web.filter;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.junit.Before;
import org.junit.Test;
import smartpool.service.BuddyService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProfileCheckFilterTest {
    @Mock
    private HttpServletRequest servletRequest;
    @Mock
    private HttpServletResponse servletResponse;
    @Mock
    private FilterChain filterChain;
    @Mock
    private HttpSession session;

    @Mock
    private BuddyService buddyService;
    private ProfileCheckFilter profileCheckFilter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        profileCheckFilter = new ProfileCheckFilter(buddyService);
    }

    @Test
    public void shouldGiveErrorIfProfileForUserDoesNotExists() throws Exception {
        when(buddyService.exists("ghostuser")).thenReturn(false);
        when(servletRequest.getSession()).thenReturn(session);
        when(session.getAttribute(CASFilter.CAS_FILTER_USER)).thenReturn("ghostuser");
        when(servletRequest.getContextPath()).thenReturn("carpool");

        profileCheckFilter.doFilter(servletRequest, servletResponse, filterChain);

        verify(servletResponse).sendRedirect("carpool/buddy/create");
    }
}
