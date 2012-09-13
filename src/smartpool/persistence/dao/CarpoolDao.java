package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.CarpoolMapper;
import smartpool.domain.Carpool;

import java.util.List;

@Component
public class CarpoolDao {

    private SqlSessionFactory sqlSessionFactory;

    public CarpoolDao() {
        this(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public CarpoolDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public void insert(Carpool carpool) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CarpoolMapper carpoolMapper = sqlSession.getMapper(CarpoolMapper.class);
        carpoolMapper.insert(carpool);
        sqlSession.commit();
        sqlSession.close();
    }

    public Carpool get(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CarpoolMapper carpoolMapper = sqlSession.getMapper(CarpoolMapper.class);
        Carpool carpool = carpoolMapper.get(name);
        sqlSession.close();
        return carpool;
    }

    public void delete(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CarpoolMapper carpoolMapper = sqlSession.getMapper(CarpoolMapper.class);
        carpoolMapper.delete(name);
        sqlSession.commit();
        sqlSession.close();
    }

    public List<Carpool> selectAllCarpools() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            CarpoolMapper mapper = session.getMapper(CarpoolMapper.class);
            return mapper.selectAll();
        } finally {
            session.close();
        }
    }

    public void updateRequestSent(Carpool carpool) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            CarpoolMapper mapper = session.getMapper(CarpoolMapper.class);
            mapper.updateRequestSent(carpool);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void updateStatus(Carpool carpool) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            CarpoolMapper mapper = session.getMapper(CarpoolMapper.class);
            mapper.updateStatus(carpool);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void updateCarpool(Carpool updated) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            CarpoolMapper mapper = session.getMapper(CarpoolMapper.class);
            mapper.updateCarpool(updated);
            session.commit();
        } finally {
            session.close();
        }
    }
}
