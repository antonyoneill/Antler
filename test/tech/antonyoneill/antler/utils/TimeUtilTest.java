package tech.antonyoneill.antler.utils;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TimeUtilTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private TimeUtil timeUtil = new TimeUtil();
    
    @Test
    public void testArgument() {
        expectedEx.expect(IllegalArgumentException.class);
        timeUtil.timeDifference(null);
    }
    
    @Test
    public void testDifference() {
        assertEquals("just now", timeUtil.timeDifference(Instant.now()));

        assertEquals("1 second ago", timeUtil.timeDifference(Instant.now().minusSeconds(1)));
        assertEquals("2 seconds ago", timeUtil.timeDifference(Instant.now().minusSeconds(2)));

        assertEquals("1 minute ago", timeUtil.timeDifference(Instant.now().minusSeconds(60)));
        assertEquals("5 minutes ago", timeUtil.timeDifference(Instant.now().minusSeconds(60 * 5)));

        assertEquals("1 hour ago", timeUtil.timeDifference(Instant.now().minusSeconds(60 * 60)));
        assertEquals("5 hours ago", timeUtil.timeDifference(Instant.now().minusSeconds(60 * 60 * 5)));

        assertEquals("1 day ago", timeUtil.timeDifference(Instant.now().minusSeconds(60 * 60 * 24)));
        assertEquals("5 days ago", timeUtil.timeDifference(Instant.now().minusSeconds(60 * 60 * 24 * 5)));
    }

}
