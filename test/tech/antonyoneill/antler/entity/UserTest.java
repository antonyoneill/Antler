package tech.antonyoneill.antler.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testCanBeCreated() {
        User user = new User("antonyoneill");
        assertEquals("Username is accessible", "antonyoneill", user.getUsername());
        assertEquals("The user is following no one", 0, user.getFollows().size());
        assertEquals("The user has no posts", 0, user.getTimeline().size());
    }

    @Test
    public void testCannotHaveNullUsername() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(User.ERROR_USERNAME_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null username");
        new User(null);
    }

    @Test
    public void testCannotHaveSpaceInUsername() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(User.ERROR_USERNAME_CONTAINS_SPACE);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with space in username");
        new User(" ");
    }

    @Test
    public void testCannotHaveEmptyUsername() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(User.ERROR_USERNAME_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with empty username");
        new User("");
    }

    @Test
    public void testEqualityEqual() {
        User one = new User("one");
        assertTrue(one.equals(one));
    }

    @Test
    public void testEqualityNotEqual() {
        User one = new User("one");
        User two = new User("two");
        assertFalse(one.equals(two));
    }

    @Test
    public void testEqualityNotUser() {
        User one = new User("one");
        String two = "test";
        assertFalse(one.equals(two));
    }

}
