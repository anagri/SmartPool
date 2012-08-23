package smartpool.web;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.domain.Carpool;
import smartpool.service.CarpoolBuilder;
import smartpool.service.SearchCarpoolService;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class searchCarpoolControllerTest {

    private SearchCarpoolController searchCarpoolController;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private SearchCarpoolService searchCarpoolService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        searchCarpoolController = new SearchCarpoolController(searchCarpoolService);
      when(httpServletRequest.getParameter("pickupPoint")).thenReturn("Domlur");
       when(searchCarpoolService.searchCarpoolsBy("domlur")).thenReturn(CarpoolBuilder.CARPOOL_2);
    }

@Test
    public void searchTest(){
        SearchCarpoolController searchCarpoolController = new SearchCarpoolController(searchCarpoolService);
    Carpool carpoolExpected = CarpoolBuilder.CARPOOL_2;
    Carpool carpoolActual = (Carpool) searchCarpoolController.handleRequest(httpServletRequest, null).getModel().get("carpool");
    Assert.assertEquals(carpoolExpected,carpoolActual );
}

}
