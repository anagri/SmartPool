package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.JoinRequestMapper;
import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;

import java.util.UUID;

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

    public void addUniqueIdToPendingRequest(String buddyUsername, String carpoolName, UUID uuid) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            mapper.addUniqueIdToPendingRequest(buddyUsername, carpoolName,uuid.toString());
            session.commit();
        } finally {
            session.close();
        }

    }

    public UUID getUniqueIdFromPendingRequest(String buddyUsername, String carpoolName) {
        String uuid;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            uuid = mapper.getUniqueIdToPendingRequest(buddyUsername, carpoolName);
            session.commit();
        } finally {
            session.close();
        }
        return uuid == null? null : UUID.fromString(uuid);
    }

    public void deletePendingRequest(String uid) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            mapper.deletePendingRequest(uid);
            session.commit();
        } finally {
            session.close();
        }
    }

    public String getBuddyUserNameFromUid(String uid) {
        String buddyUserName;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            buddyUserName = mapper.getBuddyUserNameFromUid(uid);
            session.commit();
        } finally {
            session.close();
        }
        return buddyUserName;
    }

    public String getCarpoolNameFromUid(String uid) {
        String carpoolName;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            carpoolName = mapper.getCarpoolNameFromUid(uid);
            session.commit();
        } finally {
            session.close();
        }
        return carpoolName;
    }

    public boolean isUidPresent(String uid) {
        Boolean result;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            JoinRequestMapper mapper = session.getMapper(JoinRequestMapper.class);
            result = mapper.isUidPresent(uid);
            session.commit();
        } finally {
            session.close();
        }
        return result;
    }
}
