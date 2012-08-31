package smartpool.data.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.LocalDate;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalDateTypeHandler implements TypeHandler<LocalDate> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter == null ? null : parameter.toString());
    }

    @Override
    public LocalDate getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName) == null ? null : LocalDate.parse(rs.getString(columnName));
    }

    @Override
    public LocalDate getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex) == null ? null : LocalDate.parse(rs.getString(columnIndex));
    }

    @Override
    public LocalDate getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex) == null ? null : LocalDate.parse(cs.getString(columnIndex));
    }

}
