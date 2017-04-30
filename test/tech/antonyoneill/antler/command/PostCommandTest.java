package tech.antonyoneill.antler.command;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;

public class PostCommandTest extends CommandTest {

    PostCommand command = new PostCommand(app);

    @Test
    public void testInput() {
        assertTrue("Accepts username -> and text", command.isInputValid("single -> message"));
        assertTrue("Accepts single word with numbers", command.isInputValid("single123 -> message"));
        assertTrue("Accepts single word with dashes", command.isInputValid("single-user -> message"));
        assertFalse("Declines input with spaces in username", command.isInputValid("some space -> message"));
        assertFalse("Declines input without whitespace", command.isInputValid("somespace->message"));
    }

    @Test
    public void testExecuteAddMessage() throws UnableToFindUserException {
        command.execute("tester -> Hello World");
        User user = app.getUserManager().getUser("tester");
        assertNotNull("User was created", user);
        assertEquals("User has a message", 1, user.getTimeline().size());
        
        Post post = user.getTimeline().get(0);
        assertEquals("Hello World", post.getMessage());
    }

    @Test
    public void testExecuteAddMessageContainingArrow() throws UnableToFindUserException {
        command.execute("tester -> Hello World -> Hurrah");
        User user = app.getUserManager().getUser("tester");
        assertNotNull("User was created", user);
        assertEquals("User has a message", 1, user.getTimeline().size());
        
        Post post = user.getTimeline().get(0);
        assertEquals("Hello World -> Hurrah", post.getMessage());
    }

    @Test
    public void testExecuteAddBlankMessage() throws UnableToFindUserException {
        command.execute("tester ->  ");
        User user = app.getUserManager().getUser("tester");
        assertNotNull("User was created", user);
        assertEquals("User has no messages", 0, user.getTimeline().size());
    }
}
