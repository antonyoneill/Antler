package tech.antonyoneill.antler.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import org.junit.Test;

import tech.antonyoneill.antler.command.ReadCommand;
import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.PostImpl;
import tech.antonyoneill.antler.entity.User;

public class ReadCommandTest extends CommandTest {

    ReadCommand command = new ReadCommand(app);

    @Test
    public void testInput() {
        assertTrue("Accepts single word", command.isInputValid("single"));
        assertTrue("Accepts single word with numbers", command.isInputValid("single123"));
        assertTrue("Accepts single word with dashes", command.isInputValid("single-user"));
        assertFalse("Declines input with spaces", command.isInputValid("some space"));
    }

    @Test
    public void testExecuteUserExists() {
        User user = app.addUser("tester");
        Post post = new PostImpl(Instant.now(), user, "Hello World");
        user.post(post);
        
        command.execute(user.getUsername());
        assertEquals("tester - Hello World (just now)", outContent.toString().trim());
        assertEquals("", errContent.toString());
    }
    
    @Test
    public void testExecuteUserNoExists() {
        command.execute("missing");
        assertEquals("", outContent.toString());
        assertEquals("The user [missing] doesn't exist.", errContent.toString().trim());
    }

}
