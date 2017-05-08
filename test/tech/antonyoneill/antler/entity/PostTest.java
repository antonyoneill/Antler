package tech.antonyoneill.antler.entity;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PostTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    User                     user       = new User("Tester");

    @Test
    public void testPostCreation() {
        Instant createdDate = Instant.now();
        Post post = new Post(createdDate, user, "Hello");
        assertEquals("The message is accessible", "Hello", post.getMessage());
        assertEquals("The toString method contains the date", "Hello", post.toString());
    }

    @Test
    public void testNullCreatedDate() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(Post.ERROR_CREATED_DATE_NULL);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null createdDate");
        new Post(null, user, "Hello");
    }

    @Test
    public void testFutureCreatedDate() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(Post.ERROR_CREATED_DATE_IN_FUTURE);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with future createdDate");
        new Post(Instant.now().plusSeconds(60), user, "Hello");
    }

    @Test
    public void testNullUser() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(Post.ERROR_USER_NULL);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null user");
        new Post(Instant.now(), null, "Hello");
    }

    @Test
    public void testNullMessage() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(Post.ERROR_MESSAGE_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null message");
        new Post(Instant.now(), user, null);
    }

    @Test
    public void testEmptyMessage() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(Post.ERROR_MESSAGE_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with empty message");
        new Post(Instant.now(), user, "");
    }
}
