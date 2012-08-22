package smartpool.persistence.dao;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class DummyTimeDAOTest {
    @Test
    @Ignore
    public void testSelectAll() throws Exception {
        DummyTimeDAO dummyTimeDAO=new DummyTimeDAO();
        Assert.assertEquals(3, dummyTimeDAO.selectAll().size());
    }
}
