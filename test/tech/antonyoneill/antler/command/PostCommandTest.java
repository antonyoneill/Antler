package tech.antonyoneill.antler.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.CommandException;
import tech.antonyoneill.antler.exceptions.CommandSyntaxException;

public class PostCommandTest extends CommandTest {

    PostCommand              command;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup() {
        setupApp();
        command = new PostCommand(app);
    }

    @Test
    public void testInput() {
        assertTrue("Accepts username -> and text", command.isInputValid("single -> message"));
        assertTrue("Accepts single word with numbers", command.isInputValid("single123 -> message"));
        assertTrue("Accepts single word with dashes", command.isInputValid("single-user -> message"));
        assertFalse("Declines input with spaces in username", command.isInputValid("some space -> message"));
        assertFalse("Declines input without whitespace", command.isInputValid("somespace->message"));
    }

    @Test
    public void testExecutionWithInvalidInput() throws CommandException {
        expectedEx.expect(CommandSyntaxException.class);
        command.execute("invalid input");
    }

    @Test
    public void testExecuteAddMessage() throws CommandException {
        command.execute("tester -> Hello World");
        User user = app.getUserManager().getUser("tester");
        assertNotNull("User was created", user);
        assertEquals("User has a message", 1, user.getTimeline().size());

        Post post = user.getTimeline().get(0);
        assertEquals("Hello World", post.getMessage());

        command.execute("tester -> Hello again");
        post = user.getTimeline().get(1);
        assertEquals("Hello again", post.getMessage());

    }

    @Test
    public void testExecuteAddMessageContainingArrow() throws CommandException {
        command.execute("tester -> Hello World -> Hurrah");
        User user = app.getUserManager().getUser("tester");
        assertNotNull("User was created", user);
        assertEquals("User has a message", 1, user.getTimeline().size());

        Post post = user.getTimeline().get(0);
        assertEquals("Hello World -> Hurrah", post.getMessage());
    }

    @Test
    public void testExecuteAddBlankMessage() throws CommandException {
        command.execute("tester ->  ");
        User user = app.getUserManager().getUser("tester");
        assertNotNull("User was created", user);
        assertEquals("User has no messages", 0, user.getTimeline().size());
    }
}
