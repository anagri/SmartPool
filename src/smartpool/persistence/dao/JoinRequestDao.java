package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.JoinRequestMapper;
import smartpool.domain.JoinRequest;

import javax.servlet.http.HttpServletRequest;

public class JoinRequestDao {

        private SqlSessionFactory sqlSessionFactory;
        private HttpServletRequest request;


        public JoinRequestDao(){
            sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
        }


    public void sendJoinRequest(JoinRequest joinRequest) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            mapper.insertRequest(joinRequest);
        } finally {
            session.close();
        }
    }


}
