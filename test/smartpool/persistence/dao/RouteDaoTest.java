package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.data.RouteMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        when(mockRouteMapper.getCarpoolNameList("diamond district")).thenReturn(Arrays.asList("carpool-1"));

        RouteDao routeDao = new RouteDao(mockSqlSessionFactory);
        List<String> carpoolNameList = routeDao.getCarpoolNameListByLocation("diamond district");

        assertEquals("carpool-1", carpoolNameList.get(0));
    }
}
