package smartpool.data.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.LocalTime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalTimeTypeHandler implements TypeHandler<LocalTime>{
    @Override
    public void setParameter(PreparedStatement ps, int i, LocalTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter== null ? null : parameter.toString());
    }

    @Override
    public LocalTime getResult(ResultSet rs, String columnName) throws SQLException {
        return  rs.getString(columnName) == null ? null : LocalTime.parse(rs.getString(columnName));
    }

    @Override
    public LocalTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex) == null ? null :  LocalTime.parse(rs.getString(columnIndex));
    }

    @Override
    public LocalTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return LocalTime.parse(cs.getString(columnIndex));
    }
}
