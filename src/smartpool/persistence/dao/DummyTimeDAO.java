package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.domain.DummyTime;

import java.util.List;


class DummyTimeDAO {

	private SqlSessionFactory sqlSessionFactory;

	public DummyTimeDAO(){
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	

	public List<DummyTime> selectAll(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
            List<DummyTime> list= session.selectList("DummyTime.getAll");
            return list;
		} finally {
			session.close();
		}
	}
}
