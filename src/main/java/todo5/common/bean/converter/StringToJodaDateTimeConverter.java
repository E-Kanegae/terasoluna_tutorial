
package todo5.common.bean.converter;

import org.dozer.DozerConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

public class StringToJodaDateTimeConverter
        extends DozerConverter<String, DateTime> {

    public StringToJodaDateTimeConverter() {
        super(String.class, DateTime.class);
    }

    @Override
    public DateTime convertTo(String source, DateTime destination) {
        if (!StringUtils.hasLength(source)) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormat
                .forPattern("yyyy-MM-dd");
        DateTime dt = formatter.parseDateTime(source);
        return dt;
    }

    @Override
    public String convertFrom(DateTime source, String destination) {
        if (source == null) {
            return null;
        }
        return source.toString("yyyy-MM-dd HH:mm:ss");
    }

}
