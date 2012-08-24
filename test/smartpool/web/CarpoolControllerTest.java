package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.domain.Carpool;
import smartpool.service.CarpoolBuilder;
import smartpool.service.CarpoolService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarpoolControllerTest {

    private CarpoolController carpoolController;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private CarpoolService carpoolService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        carpoolController = new CarpoolController(carpoolService);
        when(httpServletRequest.getParameter("name")).thenReturn("carpool");
        when(carpoolService.findCarpoolBy("carpool")).thenReturn(CarpoolBuilder.CARPOOL_1);
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        assertEquals(carpoolController.handleRequest(httpServletRequest, null).getViewName(), "carpool/view");
    }

    @Test
    public void shouldHaveCarpoolInstanceInModel() throws Exception {
        Carpool carpoolExpected = CarpoolBuilder.CARPOOL_1;
        Carpool carpoolActual = (Carpool) carpoolController.handleRequest(httpServletRequest, null).getModel().get("carpool");

        assertEquals(carpoolActual, carpoolExpected);
    }
}
