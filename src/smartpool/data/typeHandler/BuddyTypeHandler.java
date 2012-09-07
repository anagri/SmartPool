package smartpool.data.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import smartpool.domain.Buddy;
import smartpool.persistence.dao.BuddyDao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuddyTypeHandler implements TypeHandler<Buddy> {
    private final BuddyDao buddyDao;

    public BuddyTypeHandler(BuddyDao buddyDao) {
        this.buddyDao = buddyDao;
    }

    public BuddyTypeHandler(){
        buddyDao = new BuddyDao();
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, Buddy parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter == null ? null : parameter.getUserName());
    }

    @Override
    public Buddy getResult(ResultSet rs, String columnName) throws SQLException {
        return buddyDao.selectBuddy(rs.getString(columnName));
    }

    @Override
    public Buddy getResult(ResultSet rs, int columnIndex) throws SQLException {
        return buddyDao.selectBuddy(rs.getString(columnIndex));
    }

    @Override
    public Buddy getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return buddyDao.selectBuddy(cs.getString(columnIndex));
    }
}
