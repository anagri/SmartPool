package smartpool.data.typeHandler;

import org.junit.Test;
import smartpool.persistence.dao.BuddyDao;

import java.sql.ResultSet;

import static org.mockito.Mockito.*;

public class BuddyTypeHandlerTest {

    @Test
    public void shouldGetBuddyObjectFromBuddyUserName() throws Exception {
        BuddyDao buddyDao = mock(BuddyDao.class);
        BuddyTypeHandler buddyTypeHandler = new BuddyTypeHandler(buddyDao);

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("buddyUserName")).thenReturn("buddy");

        buddyTypeHandler.getResult(resultSet, "buddyUserName");

        verify(buddyDao).selectBuddy("buddy");
    }
}
