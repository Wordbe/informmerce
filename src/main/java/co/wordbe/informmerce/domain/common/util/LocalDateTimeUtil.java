package co.wordbe.informmerce.domain.common.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class LocalDateTimeUtil {

    public static Date date(LocalDateTime issuedAt) {
        return Date.from(issuedAt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
