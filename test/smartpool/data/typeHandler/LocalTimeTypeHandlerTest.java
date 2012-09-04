package smartpool.data.typeHandler;

import junit.framework.Assert;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.ResultSet;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LocalTimeTypeHandlerTest {
    @Mock
    ResultSet rs;

    @Test
    public void testGetResult() throws Exception {
        initMocks(this);
        when(rs.getString("column")).thenReturn("10:50");
        LocalTimeTypeHandler localTimeTypeHandler = new LocalTimeTypeHandler();
        Assert.assertEquals(new LocalTime(10, 50), localTimeTypeHandler.getResult(rs, "column"));
    }
}
