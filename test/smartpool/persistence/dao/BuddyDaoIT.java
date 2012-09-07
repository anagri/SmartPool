package smartpool.persistence.dao;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Buddy;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BuddyDaoIT {

    private BuddyDao buddyDao;

    @Before
    public void setUp() throws Exception {
        buddyDao = new BuddyDao();
    }

    @Test
    public void shouldGetBuddyByUserName(){
        Buddy expectedBuddy = new Buddy("arnavku");
        Assert.assertEquals(expectedBuddy, buddyDao.selectBuddy("arnavku"));
    }

    @Test
    public void shouldGetFirstBuddy(){
        Buddy expectedBuddy = new Buddy("arnavku");
        assertTrue(buddyDao.selectAllBuddies().contains(expectedBuddy));
    }

    @Test
    public void shouldGetBuddyListByCarpoolName() {
        List<Buddy> buddyList = buddyDao.getBuddyListByCarpoolName("carpool-1");
        assertThat(buddyList.size(), not(0));
    }

    @Test
    public void shouldReturnFalseIfBuddyProfileDoesNotExist() throws Exception {
        boolean result = buddyDao.exists("ghostuser");
        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnTrueIfBuddyProfileExists() throws Exception {
        boolean result = buddyDao.exists("arnavku");
        assertThat(result, is(true));
    }
}
