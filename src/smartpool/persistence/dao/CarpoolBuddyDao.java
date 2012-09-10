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
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CarpoolBuddyMapper carpoolBuddyMapper = sqlSession.getMapper(CarpoolBuddyMapper.class);
        ArrayList<CarpoolBuddy> carpoolBuddies = carpoolBuddyMapper.getByCarpoolName(carpoolName);
        sqlSession.close();
        return carpoolBuddies;
    }

    public void insert(CarpoolBuddy carpoolBuddy, Carpool carpool) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CarpoolBuddyMapper carpoolBuddyMapper = sqlSession.getMapper(CarpoolBuddyMapper.class);
        carpoolBuddyMapper.insert(carpoolBuddy.getBuddy().getUserName(),carpool.getName(),carpoolBuddy.getPickupPoint(),carpoolBuddy.getPickupTime().toString("HH:mm"));
        sqlSession.commit();
        sqlSession.close();
    }

    public void remove(CarpoolBuddy carpoolBuddy, String carpool) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CarpoolBuddyMapper carpoolBuddyMapper = sqlSession.getMapper(CarpoolBuddyMapper.class);
        carpoolBuddyMapper.remove(carpoolBuddy.getBuddy().getUserName(),
                carpool);
        sqlSession.commit();
        sqlSession.close();
    }
}
