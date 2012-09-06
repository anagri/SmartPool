package smartpool.web;

import org.junit.Test;
import org.mockito.Mock;
import smartpool.util.EnvironmentLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LogoutControllerTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private EnvironmentLoader environmentLoader;

    @Test
    public void shouldCheckSessionIsInvalid() throws Exception {
        initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("username")).thenReturn("suganthi");
        when(environmentLoader.getProperty(EnvironmentLoader.CAS_SERVER_URL)).thenReturn("testCAS");

        LogoutController logoutController = new LogoutController(environmentLoader);
        String logoutURL = logoutController.logout(request);

        assertEquals("redirect:testCAS/logout", logoutURL);
        verify(session).invalidate();
    }
}
