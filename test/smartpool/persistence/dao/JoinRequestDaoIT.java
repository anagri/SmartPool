package smartpool.persistence.dao;

import org.junit.Before;
import org.junit.Test;
import smartpool.domain.JoinRequest;

public class JoinRequestDaoIT {

    private JoinRequestDao joinRequestDao;

    @Before
    public void setUp() throws Exception {
        joinRequestDao = new JoinRequestDao();
    }

    @Test
    public void shouldInsertRequestToDB() {
        joinRequestDao.sendJoinRequest(new JoinRequest("ayusht","carpool-1","9:00","Domlur"));
    }
}
