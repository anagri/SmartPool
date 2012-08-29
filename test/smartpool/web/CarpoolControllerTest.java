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
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
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
        when(carpoolService.getByName("carpool")).thenReturn(expectedCarpool);
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

    @Test
    public void shouldRedirectToCreateCarpool() throws Exception {
        assertThat(carpoolController.create(),equalTo("carpool/create"));
    }

    @Test
    public void shouldRedirectToViewCarpoolWhenPostedOnCreate(){
        assertThat(carpoolController.create("name",model),equalTo("redirect:/carpool/name"));
    }

    @Test
    public void shouldInsertIntoDBWhenPostedOnCreate() throws Exception {
        carpoolController.create("name",model);
        verify(carpoolService).insert(new Carpool("name"));
    }
}
