package tech.antonyoneill.antler.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;

public class ReadCommandTest extends CommandTest {

    ReadCommand command;
    @Before
    public void setup() {
        setupApp();
        command = new ReadCommand(app);
    }
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testInput() {
        assertTrue("Accepts single word", command.isInputValid("single"));
        assertTrue("Accepts single word with numbers", command.isInputValid("single123"));
        assertTrue("Accepts single word with dashes", command.isInputValid("single-user"));
        assertFalse("Declines input with spaces", command.isInputValid("some space"));
    }

    @Test
    public void testExecuteUserExists() throws UnableToFindUserException {
        User user = app.getUserManager().addUser("tester");
        app.getUserManager().post(user, "Hello World");
        
        command.execute(user.getUsername());
        
        Post[] printedPosts = printer.getPostsPrinted();
        assertEquals("Hello World", printedPosts[0].toString());
        assertEquals("", getErrContent()[0]);
    }
    
    @Test
    public void testExecuteUserNoExists() throws UnableToFindUserException {
        expectedEx.expect(UnableToFindUserException.class);
        command.execute("missing");
    }

}
