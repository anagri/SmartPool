package smartpool.persistence.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class CarpoolDaoIT {

    private CarpoolDao carpoolDao;

    @Before
    public void setUp() throws Exception {
        carpoolDao = new CarpoolDao();
    }

    @Test
    public void shouldInsertNewRowInDB() throws Exception {
        carpoolDao.insert("name");

        assertNotNull(carpoolDao.get("name"));
    }

    @After
    public void tearDown() throws Exception {
        carpoolDao.delete("name");
    }
}
