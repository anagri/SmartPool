package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import smartpool.data.CarpoolMapper;
import smartpool.domain.Carpool;

import static org.mockito.Mockito.*;

public class CarpoolDaoTest {
    @Test
    public void shouldInsertIntoDatabase() throws Exception {
        SqlSessionFactory mockSqlSessionFactory = mock(SqlSessionFactory.class);

        SqlSession mockSqlSession = mock(SqlSession.class);
        CarpoolMapper mockCarpoolMapper = mock(CarpoolMapper.class);

        when(mockSqlSessionFactory.openSession()).thenReturn(mockSqlSession);
        when(mockSqlSession.getMapper(CarpoolMapper.class)).thenReturn(mockCarpoolMapper);

        CarpoolDao carpoolDao = new CarpoolDao(mockSqlSessionFactory);
        carpoolDao.insert("name");

        Carpool carpool = new Carpool("name");
        verify(mockCarpoolMapper).insert(carpool);

    }
}
