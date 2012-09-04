package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.data.RouteMapper;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RouteDaoTest {
    @Mock
    SqlSessionFactory mockSqlSessionFactory;
    @Mock
    SqlSession mockSqlSession;
    @Mock
    RouteMapper mockRouteMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(mockSqlSessionFactory.openSession()).thenReturn(mockSqlSession);
        when(mockSqlSession.getMapper(RouteMapper.class)).thenReturn(mockRouteMapper);
    }

    @Test
    public void shouldGetCarpoolNameListByLocation() throws Exception {
        RouteDao routeDao = new RouteDao(mockSqlSessionFactory);
        List<String> carpoolNameList = routeDao.getCarpoolNameListByLocation("diamond district");

        verify(mockRouteMapper).getCarpoolNameList("diamond district");
    }

    @Test
    public void shouldInsertRoutePoint() throws Exception {

    }
}
