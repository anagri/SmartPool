package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Carpool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CarpoolServiceTest {

    private CarpoolService carpoolService;

    @Before
    public void setUp() {
        carpoolService = new CarpoolService();
    }

    @Test
    public void shouldFindCarpoolByName() {
        String carpoolName = "carpool-1";

        Carpool carpoolExpected = CarpoolBuilder.CARPOOL_1;
        assertThat(carpoolService.findCarpoolBy(carpoolName), equalTo(carpoolExpected));
    }

    @Test
    public void shouldNotFindAnyCarpool() {
        String carpoolName = "carpool-na";

        assertNull(carpoolService.findCarpoolBy(carpoolName));
    }
}
