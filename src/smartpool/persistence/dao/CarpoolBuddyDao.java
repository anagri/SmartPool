package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.CarpoolBuddyMapper;
import smartpool.domain.Carpool;
import smartpool.domain.CarpoolBuddy;

import java.util.ArrayList;

@Component
public class CarpoolBuddyDao {
    private final SqlSessionFactory sqlSessionFactory;

    public CarpoolBuddyDao(SqlSessionFactory sqlSessionFactory) {

        this.sqlSessionFactory = sqlSessionFactory;
    }

    public CarpoolBuddyDao() {
        this(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public ArrayList<CarpoolBuddy> getCarpoolBuddiesByCarpoolName(String carpoolName) {
        ArrayList<CarpoolBuddy> carpoolBuddies;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            CarpoolBuddyMapper carpoolBuddyMapper = sqlSession.getMapper(CarpoolBuddyMapper.class);
            carpoolBuddies = carpoolBuddyMapper.getByCarpoolName(carpoolName);
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
        return carpoolBuddies;
    }

    public void insert(CarpoolBuddy carpoolBuddy, Carpool carpool) {
        carpoolBuddy.getBuddy().addCarpool(carpool);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            CarpoolBuddyMapper carpoolBuddyMapper = sqlSession.getMapper(CarpoolBuddyMapper.class);
            carpoolBuddyMapper.insert(carpoolBuddy.getBuddy().getUserName(),carpool.getName(),carpoolBuddy.getPickupPoint(),carpoolBuddy.getPickupTime().toString("HH:mm"));
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
    }

    public void remove(String buddyUserName, String carpoolName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CarpoolBuddyMapper carpoolBuddyMapper = sqlSession.getMapper(CarpoolBuddyMapper.class);
        try{
        carpoolBuddyMapper.remove(buddyUserName,carpoolName);
        sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
    }

    public CarpoolBuddy getCarpoolBuddyFromUsernameAndCarpoolName(String userName, String carpoolName) {
        CarpoolBuddy carpoolBuddy;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CarpoolBuddyMapper carpoolBuddyMapper = sqlSession.getMapper(CarpoolBuddyMapper.class);
        try{
            carpoolBuddy = carpoolBuddyMapper.getByUserNameAndCarpoolName(userName,carpoolName);
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
        return carpoolBuddy;
    }
}
