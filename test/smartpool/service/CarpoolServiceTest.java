package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.CarpoolDao;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
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
        assertNotNull(carpoolService.findCarpoolByName(carpoolName));
    }


    @Test
    public void shouldNotFindAnyCarpool() {
        String carpoolName = "carpool-na";

        assertNull(carpoolService.getByName(carpoolName));
    }

    @Test
    public void shouldFindCarpoolsByLocation() {
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("Diamond District");
        assertThat(carpools.contains(carpoolExpected),equalTo(true));
    }

    @Test
    public void shouldReturnAllCarpoolsWhenSearchStringIsBlank() throws Exception {
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("");
        assertThat(carpools.size(),is(1));
    }

    @Test
    public void shouldInsertIntoDatabase(){
        carpoolService.insert(new Carpool("name"));
        verify(carpoolDao).insert(new Carpool("name"));
    }
}
