package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
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
    private String carpoolName;
    private CarpoolDao carpoolDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(mockSqlSessionFactory.openSession()).thenReturn(mockSqlSession);
        when(mockSqlSession.getMapper(CarpoolMapper.class)).thenReturn(mockCarpoolMapper);
        carpoolName = "name";
        carpoolDao = new CarpoolDao(mockSqlSessionFactory);
    }

    @Test
    public void shouldInsertIntoDatabase() throws Exception {
        CarpoolDao carpoolDao = new CarpoolDao(mockSqlSessionFactory);
        Carpool carpool = new Carpool(carpoolName);
        carpoolDao.insert(carpool);
        verify(mockCarpoolMapper).insert(carpool);
    }

    @Test
    public void shouldGetFromDB() throws Exception {
        carpoolDao.get(carpoolName);

        verify(mockCarpoolMapper).get(carpoolName);
    }

    @Test
    public void shouldUpdate() throws Exception {
        Carpool carpool = carpoolDao.get(carpoolName);
        carpoolDao.update(carpool);

        verify(mockCarpoolMapper).update(carpool);
    }

    @After
    public void tearDown() throws Exception {
        carpoolDao.delete(carpoolName);
    }
}
