package smartpool.persistence.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import smartpool.domain.JoinRequest;

public class JoinRequestDaoTest {

    private JoinRequestDao joinRequestDao;

    @Before
    public void setUp() throws Exception {
        joinRequestDao = new JoinRequestDao();
    }

    @Test
    @Ignore
    public void shouldInsertRequestToDB() {
        joinRequestDao.sendJoinRequest(new JoinRequest("1","carpool-1","9:00","Domlur"));
    }
}
