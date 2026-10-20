package theuxzn16.com.github.diabetiq.dto.mappers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class TimeMapper {
    static OffsetDateTime toTimeZone(OffsetDateTime defaultTime){
        if (defaultTime == null) {
            return null;
        }
        Instant instant = defaultTime.toInstant();
        return OffsetDateTime.ofInstant(instant, ZoneId.of("America/Sao_Paulo"));
    }
}
