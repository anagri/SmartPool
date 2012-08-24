package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Carpool;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CarpoolServiceTest {

    private CarpoolService carpoolService;
    Carpool carpoolExpected;

    @Before
    public void setUp() {
        carpoolService = new CarpoolService();
        carpoolExpected = CarpoolBuilder.CARPOOL_1;
    }

    @Test
    public void shouldFindCarpoolByName() {
        String carpoolName = "carpool-1";


        assertThat(carpoolService.findCarpoolBy(carpoolName), equalTo(carpoolExpected));
    }

    @Test
    public void shouldNotFindAnyCarpool() {
        String carpoolName = "carpool-na";

        assertNull(carpoolService.findCarpoolBy(carpoolName));
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
}
