package smartpool.persistence.dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.BuddyMapper;
import smartpool.domain.Buddy;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BuddyDao {
    private SqlSessionFactory sqlSessionFactory;
    private HttpServletRequest request;


    public BuddyDao(){
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }


    public Buddy selectBuddy(String username){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            Buddy buddy=mapper.selectBuddy(username);
            return buddy;
        } finally {
            session.close();
        }
    }
    public Buddy selectAllBuddies(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            List<Buddy> buddy=mapper.selectAll();
            return buddy.get(0);
        } finally {
            session.close();
        }
    }
}

