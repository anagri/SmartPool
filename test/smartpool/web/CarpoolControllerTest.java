package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import smartpool.domain.Carpool;
import smartpool.service.CarpoolBuilder;
import smartpool.service.CarpoolService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarpoolControllerTest {

    private CarpoolController carpoolController;
    @Mock
    private CarpoolService carpoolService;
    private ModelMap model;
    private Carpool expectedCarpool = CarpoolBuilder.CARPOOL_1;

    @Before
    public void setUp() throws Exception {
        carpoolController = new CarpoolController(carpoolService);
        when(carpoolService.findCarpoolBy("carpool")).thenReturn(expectedCarpool);
        model = new ModelMap();
        ArrayList<Carpool> carpools = new ArrayList<Carpool>();
        carpools.add(expectedCarpool);
        when(carpoolService.findCarpoolByLocation("Diamond District")).thenReturn(carpools);
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        assertEquals(carpoolController.viewCarpool("carpool", model), "carpool/view");
    }

    @Test
    public void shouldHaveCarpoolInstanceInModel() throws Exception {
        carpoolController.viewCarpool("carpool", model);
        Carpool carpoolActual = (Carpool) model.get("carpool");

        assertEquals(expectedCarpool, carpoolActual);
    }

    @Test
    public void shouldSearchForCarpool(){
        carpoolController.searchByLocation("Diamond District",model);
        List<Carpool> searchResult = (List<Carpool>) model.get("searchResult");
        assertThat(searchResult.contains(expectedCarpool),equalTo(true));
    }

    @Test
    public void shouldRedirectToViewSearchCarpool() throws Exception {
        assertThat(carpoolController.searchByLocation("Diamond District", model), equalTo("carpool/search"));
    }
}
