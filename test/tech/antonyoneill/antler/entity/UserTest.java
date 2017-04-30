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
        User user = new UserImpl("antonyoneill");
        assertEquals("Username is accessible", "antonyoneill", user.getUsername());
        assertEquals("The user is following no one", 0, user.getFollows().size());
        assertEquals("The user has no posts", 0, user.getTimeline().size());
    }

    @Test
    public void testCannotHaveNullUsername() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(UserImpl.ERROR_USERNAME_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with null username");
        new UserImpl(null);
    }

    @Test
    public void testCannotHaveSpaceInUsername() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(UserImpl.ERROR_USERNAME_CONTAINS_SPACE);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with space in username");
        new UserImpl(" ");
    }

    @Test
    public void testCannotHaveEmptyUsername() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(UserImpl.ERROR_USERNAME_NULL_OR_EMPTY);
        expectedEx.reportMissingExceptionWithMessage("Expected exception with empty username");
        new UserImpl("");
    }
    
    @Test
    public void testEqualityEqual() {
        User one = new UserImpl("one");
        assertTrue(one.equals(one));
    }
    
    @Test
    public void testEqualityNotEqual() {
        User one = new UserImpl("one");
        User two = new UserImpl("two");
        assertFalse(one.equals(two));
    }
    
    @Test
    public void testEqualityNotUser() {
        User one = new UserImpl("one");
        String two = "test";
        assertFalse(one.equals(two));
    }

}
