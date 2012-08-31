package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CarpoolTest {
    @Test
    public void shouldAcceptOfficePickUpTimeAsString() throws Exception {
        Carpool carpool = new Carpool();
        carpool.setOfficeETD("10:30");
        assertThat(carpool.getOfficeETD(),equalTo(new LocalTime(10,30)));
    }

    @Test
    public void shouldAcceptStartDateAsString() throws Exception {
        Carpool carpool = new Carpool();
        carpool.setStartDate("15/03/2012");
        assertThat(carpool.getStartDate(),equalTo(new LocalDate(2012,3,15)));
    }

    @Test
    public void shouldAcceptOfficeETDAsString() throws Exception{
        Carpool carpool = new Carpool();
        carpool.setOfficeETD("10:00");
        assertThat(carpool.getOfficeETD(),equalTo(new LocalTime("10:00")));
    }

    @Test
    public void shouldSetStartDateAsNullWhenStringPassedIsNull() throws Exception {
        Carpool carpool = new Carpool();
        carpool.setStartDate(null);
        assertThat(carpool.getStartDate(),equalTo(null));
    }
}
