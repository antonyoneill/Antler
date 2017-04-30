package tech.antonyoneill.antler.utils;

import java.time.Duration;
import java.time.Instant;

public class TimeUtil {
    
    private static final String ERROR_THEN_IS_NULL = "Argument then cannot be null";

    /**
     * Calculates a pretty time string to output
     * 
     * @param then
     * @return String
     */
    public String timeDifference(Instant then) {
        if (then == null) {
            throw new IllegalArgumentException(ERROR_THEN_IS_NULL);
        }
        
        Duration duration = Duration.between(then, Instant.now());
        long time = 0;
        String unit = "";
        if (duration.toDays() > 0) {
            time = duration.toDays();
            unit = "day";
        } else if (duration.toHours() > 0) {
            time = duration.toHours();
            unit = "hour";
        } else if (duration.toMinutes() > 0) {
            time = duration.toMinutes();
            unit = "minute";
        } else if (duration.getSeconds() > 0) {
            time = duration.getSeconds();
            unit = "second";
        } else {
            return "just now";
        }
        return String.format("%d %s%s ago", time, unit, time > 1 ? "s" : "");
    }
}
