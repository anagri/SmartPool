package smartpool.persistence.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import smartpool.common.Constants;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.domain.Status;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CarpoolDaoIT {

    private CarpoolDao carpoolDao;
    private String name;
    private String carpoolName;

    @Before
    public void setUp() throws Exception {
        carpoolDao = new CarpoolDao();
        name = "name";
        carpoolName = "carpool-1";
    }

    @Test
    public void shouldInsertNewRowInDB() throws Exception {
        Carpool carpool = new Carpool(name);
        carpool.setStartDate(Constants.DATE_FORMATTER.parseLocalDate("10/12/2012"));

        carpoolDao.insert(carpool);

        assertThat(carpoolDao.get(name).getStartDate(),equalTo(carpool.getStartDate()));
    }

    @Test
    public void shouldGetCarpoolByName() throws Exception {
        Carpool carpoolActual = carpoolDao.get(carpoolName);

        assertNotNull(carpoolActual);
        assertEquals(100, carpoolActual.getTotalCabCharges());
        assertEquals(CabType.COMPANY, carpoolActual.getCabType());
        assertEquals(Status.ACTIVE, carpoolActual.getStatus());
        assertEquals(4, carpoolActual.getCapacity());
    }

    @Test
    public void shouldUpdateRequestSent() throws Exception {
        Carpool carpool = carpoolDao.get(carpoolName);
        carpool.setRequestSent(true);
        carpoolDao.update(carpool);
        assertThat(carpoolDao.get(carpool.getName()).getRequestSent(), is(true));
    }

    @After
    public void tearDown() throws Exception {
        carpoolDao.delete(name);
    }
}
