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
        System.out.println("BuddyDao");
    }


    public Buddy selectBuddy(String username){
        System.out.println("select buddy");

        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("factory ready");
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            System.out.println("calling select buddy");
            Buddy buddy=mapper.selectBuddy(username);
            System.out.println("buddy"+buddy);
            return buddy;
        } finally {
            session.close();
        }
    }
    public Buddy selectAllBuddies(){
        System.out.println("select buddy");

        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("factory ready");
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            List<Buddy> buddy=mapper.selectAll();
            return buddy.get(0);
        } finally {
            session.close();
        }
    }
}

