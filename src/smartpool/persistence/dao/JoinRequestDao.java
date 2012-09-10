package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.JoinRequestMapper;
import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;

@Component
public class JoinRequestDao {

    private SqlSessionFactory sqlSessionFactory;

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

    public boolean isRequestSent(Buddy buddy, String carpoolName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            return mapper.selectRequestCount(buddy.getUserName(), carpoolName);
        } finally {
            session.close();
        }
    }

    public JoinRequest selectUsersRequest(String buddyUsername, String carpoolName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            return mapper.selectUserRequest(buddyUsername, carpoolName);
        } finally {
            session.close();
        }
    }

    public void deleteUsersRequest(String buddyUsername, String carpoolName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            mapper.deleteUserRequest(buddyUsername, carpoolName);
            session.commit();
        } finally {
            session.close();
        }
    }
}
