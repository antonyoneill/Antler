package tech.antonyoneill.antler.utils;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;

public class TimeUtilTest {

    @Test
    public void testDifference() {
        assertEquals("just now", TimeUtil.timeDifference(Instant.now()));

        assertEquals("1 second ago", TimeUtil.timeDifference(Instant.now().minusSeconds(1)));
        assertEquals("2 seconds ago", TimeUtil.timeDifference(Instant.now().minusSeconds(2)));

        assertEquals("1 minute ago", TimeUtil.timeDifference(Instant.now().minusSeconds(60)));
        assertEquals("5 minutes ago", TimeUtil.timeDifference(Instant.now().minusSeconds(60 * 5)));

        assertEquals("1 hour ago", TimeUtil.timeDifference(Instant.now().minusSeconds(60 * 60)));
        assertEquals("5 hours ago", TimeUtil.timeDifference(Instant.now().minusSeconds(60 * 60 * 5)));

        assertEquals("1 day ago", TimeUtil.timeDifference(Instant.now().minusSeconds(60 * 60 * 24)));
        assertEquals("5 days ago", TimeUtil.timeDifference(Instant.now().minusSeconds(60 * 60 * 24 * 5)));

    }

}
