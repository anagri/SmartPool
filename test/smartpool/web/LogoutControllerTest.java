package smartpool.web;

import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LogoutControllerTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Test
    public void shouldCheckSessionIsInvalid() throws Exception {
        initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("username")).thenReturn("suganthi");

        LogoutController logoutController = new LogoutController();
        logoutController.logout(request);

        verify(session).invalidate();
    }
}
