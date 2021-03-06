package tech.antonyoneill.antler.utils;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;
import tech.antonyoneill.antler.tests.StreamBase;

public class PostPrinterTest extends StreamBase {

    PostPrinter printer = new PostPrinter(new MockTimeUtil());
    User        user    = new User("Tester");

    @Test
    public void testPrintPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(Instant.now(), user, "post 1"));
        posts.add(new Post(Instant.now(), user, "post 2"));
        posts.add(new Post(Instant.now(), user, "post 3"));
        printer.printPosts(posts);

        String out[] = getOutContent();
        assertEquals(3, out.length);
        assertEquals("Tester - post 1 (DIFF)", out[0]);
        assertEquals("Tester - post 2 (DIFF)", out[1]);
        assertEquals("Tester - post 3 (DIFF)", out[2]);
    }

    @Test
    public void testPrintException() {
        printer.printException(new UnableToFindUserException("Tester"));
        String err[] = getErrContent();
        assertEquals(1, err.length);
        assertEquals("Unable to find user [Tester]", err[0]);
    }

    //TODO: Use Mockito
    private class MockTimeUtil extends TimeUtil {
        public String timeDifference(Instant then) {
            return "DIFF";
        }
    }
}
