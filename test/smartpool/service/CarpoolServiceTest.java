package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.CarpoolDao;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarpoolServiceTest {

    private CarpoolService carpoolService;
    Carpool carpoolExpected;

    @Mock
    CarpoolDao carpoolDao;

    @Before
    public void setUp() {
        initMocks(this);
        carpoolService = new CarpoolService(carpoolDao);
        carpoolExpected = CarpoolBuilder.CARPOOL_1;
    }

    @Test
    public void shouldFindCarpoolByName() {
        String carpoolName = "carpool-1";
        carpoolService.getByName(carpoolName);
        verify(carpoolDao).get(carpoolName);
    }

    @Test
    public void shouldNotFindAnyCarpool() {
        String carpoolName = "carpool-na";

        assertNull(carpoolService.getByName(carpoolName));
    }

    @Test
    public void shouldFindCarpoolsByLocation() {
        List<Carpool> carpools = carpoolService.findCarpoolByLocation("Diamond District");
        assertThat(carpools.contains(carpoolExpected),equalTo(true));
    }

    @Test
    public void shouldReturnAllCarpoolsWhenSearchStringIsBlank() throws Exception {
        List<Carpool> carpools = carpoolService.findCarpoolByLocation("");
        assertThat(carpools.size(),not(0));
    }

    @Test
    public void shouldInsertIntoDatabase(){
        carpoolService.insert("name");
        verify(carpoolDao).insert("name");
    }
}
