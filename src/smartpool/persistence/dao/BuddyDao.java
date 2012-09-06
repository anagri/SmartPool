package smartpool.persistence.dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.BuddyMapper;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuddyDao {

    private SqlSessionFactory sqlSessionFactory;

    public BuddyDao() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public Buddy selectBuddy(String username) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            return mapper.selectBuddy(username);
        } finally {
            session.close();
        }
    }

    public List<Buddy> selectAllBuddies() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            return mapper.selectAll();
        } finally {
            session.close();
        }
    }

    public ArrayList<Buddy> getBuddyListByCarpoolName(String carpoolName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            return mapper.selectBuddyListByCarpoolName(carpoolName);
        } finally {
            session.close();
        }
    }

    public void addToCarpool(Buddy buddy, Carpool carpool) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            mapper.addToCarpool(buddy.getUserName(), carpool.getName());
            session.commit();
        } finally {
            session.close();
        }
    }

    public boolean exists(String username) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            return mapper.exists(username);
        } finally {
            session.close();
        }
    }
}

