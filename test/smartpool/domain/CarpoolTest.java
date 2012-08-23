package smartpool.domain;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import smartpool.service.CarpoolBuilder;

import static org.junit.Assert.assertEquals;

public class CarpoolTest {

    private Carpool carpool;

    @Before
    public void setUp() {
        carpool = CarpoolBuilder.CARPOOL_1;
    }

    @Test
    public void shouldStartACarpool() {
        assertEquals("carpool-1", carpool.name());
        assertEquals(LocalDate.now(), carpool.startDate());
    }
}
