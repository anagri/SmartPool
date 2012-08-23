package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Carpool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SearchCarpoolServiceTest {

    private SearchCarpoolService searchCarpoolService;

    @Before
    public void setUp() {
        searchCarpoolService = new SearchCarpoolService();
    }

    @Test
    public void shouldFindCarpoolByPickupPoint() {
        String pickupPoint = "domlur";

        Carpool carpoolExpected = CarpoolBuilder.CARPOOL_2;
        assertThat(searchCarpoolService.searchCarpoolsBy(pickupPoint), equalTo(carpoolExpected));
    }

    @Test
    public void shouldNotFindAnyCarpool() {
        String pickupPoint = "carpool-na";

        assertNull(searchCarpoolService.searchCarpoolsBy(pickupPoint));
    }
}