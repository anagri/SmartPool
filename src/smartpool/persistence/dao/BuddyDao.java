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


    public BuddyDao() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }


    public Buddy selectBuddy(String username) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            Buddy buddy = mapper.selectBuddy(username);
            return buddy;
        } finally {
            session.close();
        }
    }

    public Buddy selectAllBuddies() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BuddyMapper mapper = session.getMapper(BuddyMapper.class);
            List<Buddy> buddyList = mapper.selectAll();
            return buddyList.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuddyDao buddyDao = (BuddyDao) o;

        if (request != null ? !request.equals(buddyDao.request) : buddyDao.request != null) return false;
        if (sqlSessionFactory != null ? !sqlSessionFactory.equals(buddyDao.sqlSessionFactory) : buddyDao.sqlSessionFactory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sqlSessionFactory != null ? sqlSessionFactory.hashCode() : 0;
        result = 31 * result + (request != null ? request.hashCode() : 0);
        return result;
    }
}

