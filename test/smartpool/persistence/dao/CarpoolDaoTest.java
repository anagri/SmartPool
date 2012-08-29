package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.data.CarpoolMapper;
import smartpool.domain.Carpool;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarpoolDaoTest {
    @Mock
    SqlSessionFactory mockSqlSessionFactory;
    @Mock
    SqlSession mockSqlSession;
    @Mock
    CarpoolMapper mockCarpoolMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(mockSqlSessionFactory.openSession()).thenReturn(mockSqlSession);
        when(mockSqlSession.getMapper(CarpoolMapper.class)).thenReturn(mockCarpoolMapper);
    }

    @Test
    public void shouldInsertIntoDatabase() throws Exception {
        CarpoolDao carpoolDao = new CarpoolDao(mockSqlSessionFactory);
        Carpool carpool = new Carpool("name");
        carpoolDao.insert(carpool);
        verify(mockCarpoolMapper).insert(carpool);
    }

    @Test
    public void shouldGetFromDB() throws Exception {
        CarpoolDao carpoolDao = new CarpoolDao(mockSqlSessionFactory);
        carpoolDao.get("name");

        verify(mockCarpoolMapper).get("name");
    }
}
