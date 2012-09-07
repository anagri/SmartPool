package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.data.CarpoolBuddyMapper;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.CarpoolBuddy;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarpoolBuddyDaoTest {

    @Mock
    SqlSessionFactory mockSqlSessionFactory;
    @Mock
    SqlSession mockSqlSession;
    @Mock
    CarpoolBuddyMapper mockCarpoolBuddyMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(mockSqlSessionFactory.openSession()).thenReturn(mockSqlSession);
        when(mockSqlSession.getMapper(CarpoolBuddyMapper.class)).thenReturn(mockCarpoolBuddyMapper);
    }

    @Test
    public void shouldGetCarpoolBuddiesByCarpoolName() throws Exception {
        ArrayList<CarpoolBuddy> carpoolBuddy = new CarpoolBuddyDao(mockSqlSessionFactory).getCarpoolBuddiesByCarpoolName("carpool-1");
        verify(mockCarpoolBuddyMapper).getByCarpoolName("carpool-1");
    }

    @Test
    public void shouldInsertIntoCarpoolBuddy() throws Exception {
        CarpoolBuddy carpoolBuddy = new CarpoolBuddy(new Buddy("prithvin"),"location",new LocalTime(10,0));

        new CarpoolBuddyDao(mockSqlSessionFactory).insert(carpoolBuddy, new Carpool("name"));
        verify(mockCarpoolBuddyMapper).insert("prithvin", "name", "location", "10:00");
    }
}
