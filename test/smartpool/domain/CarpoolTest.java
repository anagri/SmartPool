package smartpool.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CarpoolTest {

    private Carpool carpool;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldStartItIfInNotStartedStateAndHaveMoreThanOneBuddy() throws Exception {
        List<CarpoolBuddy> carpoolBuddies = Arrays.asList(new CarpoolBuddy(), new CarpoolBuddy());
        carpool = new Carpool("can start", null, null, 0, null, null, Status.NOT_STARTED, carpoolBuddies, 4, null);

        assertThat(carpool.canStart(), is(true));
    }

    @Test
    public void shouldNotStartItIfHaveOnlyOneBuddy() throws Exception {
        List<CarpoolBuddy> carpoolBuddies = Arrays.asList(new CarpoolBuddy());
        carpool = new Carpool("can start", null, null, 0, null, null, Status.NOT_STARTED, carpoolBuddies, 4, null);

        assertThat(carpool.canStart(), is(false));
    }

    @Test
    public void shouldNotStartItIfInActiveState() throws Exception {
        List<CarpoolBuddy> carpoolBuddies = Arrays.asList(new CarpoolBuddy(), new CarpoolBuddy());
        carpool = new Carpool("can start", null, null, 0, null, null, Status.ACTIVE, carpoolBuddies, 4, null);

        assertThat(carpool.canStart(), is(false));
    }
}
