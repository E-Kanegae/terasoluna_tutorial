
package todo5.infra.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

public class TimestampJodaDateTimeTypeHandler extends BaseTypeHandler<DateTime> {

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return this.toDateTime(rs.getTimestamp(columnName));
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnindex)
            throws SQLException {
        return this.toDateTime(rs.getTimestamp(columnindex));
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnindex)
            throws SQLException {
        return this.toDateTime(cs.getTimestamp(columnindex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            DateTime datetime, JdbcType jdbc) throws SQLException {
        ps.setTimestamp(i, new Timestamp(datetime.getMillis()));
    }

    private DateTime toDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return new DateTime(timestamp.getTime());
        }
    }

}
