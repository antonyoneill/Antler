package tech.antonyoneill.antler.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.CommandException;
import tech.antonyoneill.antler.exceptions.CommandSyntaxException;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;

/**
 * These tests use Thread.sleep(1) in order
 * 
 * @author @antonyoneill
 *
 */
public class FollowCommandTest extends CommandTest {

    FollowCommand command;

    @Before
    public void setup() {
        setupApp();
        command = new FollowCommand(app);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testInput() {
        assertTrue("Accepts follows user - simple", command.isInputValid("single follows single"));
        assertTrue("Accepts follows single word with numbers wall",
                command.isInputValid("single123 follows single123"));
        assertTrue("Accepts follows single word with dashes wall",
                command.isInputValid("single-user follows single-user"));
        assertFalse("Declines follows input with spaces wall", command.isInputValid("some space follows some space"));
    }
    
    @Test
    public void testExecutionWithInvalidInput() throws CommandException {
        expectedEx.expect(CommandSyntaxException.class);
        command.execute("invalid input");
    }

    @Test
    public void testUserCanFollowOthers() throws CommandException {
        User userOne = app.getUserManager().addUser("one");
        User userTwo = app.getUserManager().addUser("two");
        User userThree = app.getUserManager().addUser("three");

        assertEquals(0, userOne.getFollows().size());
        assertFalse(userOne.getFollows().contains(userTwo));
        assertFalse(userOne.getFollows().contains(userThree));

        command.execute("one follows two");

        assertEquals(1, userOne.getFollows().size());
        assertTrue(userOne.getFollows().contains(userTwo));
        assertFalse(userOne.getFollows().contains(userThree));

        command.execute("one follows three");
        assertEquals(2, userOne.getFollows().size());
        assertTrue(userOne.getFollows().contains(userTwo));
        assertTrue(userOne.getFollows().contains(userThree));
    }

    @Test
    public void testUserCannotFollowSelf() throws CommandException {
        User userOne = app.getUserManager().addUser("one");

        assertEquals(0, userOne.getFollows().size());

        command.execute("one follows one");

        assertEquals(0, userOne.getFollows().size());
    }

    @Test
    public void testUserTriesToFollowUnknown() throws CommandException {
        app.getUserManager().addUser("one");

        expectedEx.expect(UnableToFindUserException.class);
        expectedEx.expectMessage("Unable to find user [unknown]");
        
        command.execute("one follows unknown");
    }

    @Test
    public void testUnknownTriesToFollowUser() throws CommandException {
        app.getUserManager().addUser("one");

        expectedEx.expect(UnableToFindUserException.class);
        expectedEx.expectMessage("Unable to find user [unknown]");
        
        command.execute("unknown follows one");
    }
}
