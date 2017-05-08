package tech.antonyoneill.antler.utils;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;

import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.User;

public class ReverseInstantComparatorTest {

    private ReverseInstantComparator comparator = new ReverseInstantComparator();
    private User user = new User("Tester");
    
    @Test
    public void testLeftEarlier() {
        Post left = new Post(Instant.now().minusSeconds(60), user, "left");
        Post right = new Post(Instant.now(), user, "right");

        assertEquals("Left is greater than right", 1, comparator.compare(left, right));
    }

    @Test
    public void testRightEarlier() {
        Post left = new Post(Instant.now(), user, "left");
        Post right = new Post(Instant.now().minusSeconds(60), user, "right");

        assertEquals("Left is less than right", -1, comparator.compare(left, right));
    }

    @Test
    public void testEqual() {
        Instant instant = Instant.now();
        Post left = new Post(instant, user, "left");
        Post right = new Post(instant, user, "right");

        assertEquals("Left is equal to right", 0, comparator.compare(left, right));
    }

}
