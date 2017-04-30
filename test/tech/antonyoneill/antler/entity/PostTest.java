package tech.antonyoneill.antler.entity;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PostTest {
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    User                     user       = new UserImpl("Tester");

    @Test
    public void testPostCreation() {
        Instant createdDate = Instant.now();
        Post post = new PostImpl(createdDate, user, "Hello");
        assertEquals("The message is accessible", "Hello", post.getMessage());
        assertEquals("The toString method contains the date", "Hello", post.toString());
    }

    @Test
    public void testNullCreatedDate() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(PostImpl.ERROR_CREATED_DATE_NULL);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null createdDate");
        new PostImpl(null, user, "Hello");
    }

    @Test
    public void testFutureCreatedDate() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(PostImpl.ERROR_CREATED_DATE_IN_FUTURE);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with future createdDate");
        new PostImpl(Instant.now().plusSeconds(60), user, "Hello");
    }

    @Test
    public void testNullUser() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(PostImpl.ERROR_USER_NULL);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null user");
        new PostImpl(Instant.now(), null, "Hello");
    }

    @Test
    public void testNullMessage() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(PostImpl.ERROR_MESSAGE_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null message");
        new PostImpl(Instant.now(), user, null);
    }

    @Test
    public void testEmptyMessage() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(PostImpl.ERROR_MESSAGE_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with empty message");
        new PostImpl(Instant.now(), user, "");
    }
}
