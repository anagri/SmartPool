package smartpool.dao;

import junit.framework.Assert;
import org.junit.Test;
import smartpool.domain.DummyTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DummyTimeDAOTest {
    @Test
    public void testSelectAll() throws Exception {
        DummyTimeDAO dummyTimeDAO=new DummyTimeDAO();
        List<DummyTime> list = new ArrayList<DummyTime>();
        list.add(new DummyTime(1,"Alisha"));
        list.add(new DummyTime(2,"Charming"));
        list.add(new DummyTime(3,"Ishak"));
        Assert.assertEquals(list, dummyTimeDAO.selectAll());
    }
}
