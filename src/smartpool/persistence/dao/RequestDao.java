package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.RequestMapper;
import smartpool.domain.JoinRequest;

public class RequestDao {
    private SqlSessionFactory sqlSessionFactory;

    public RequestDao() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public void insertRequest(JoinRequest joinRequest) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            RequestMapper mapper = session.getMapper(RequestMapper.class);
            mapper.insertRequest(joinRequest);
        } finally {
            session.close();
        }
    }
}
