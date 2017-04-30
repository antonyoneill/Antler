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
import tech.antonyoneill.antler.utils.UserManager;

/**
 * These tests use Thread.sleep(1) in order 
 * @author @antonyoneill
 *
 */
public class WallCommandTest extends CommandTest {

    WallCommand command;
    
    @Before
    public void setup() {
        setupApp();
        command = new WallCommand(app);
    }
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testInput() {
        assertTrue("Accepts single word wall", command.isInputValid("single wall"));
        assertTrue("Accepts single word with numbers wall", command.isInputValid("single123 wall"));
        assertTrue("Accepts single word with dashes wall", command.isInputValid("single-user wall"));
        assertFalse("Declines input with spaces wall", command.isInputValid("some space wall"));
    }

    @Test
    public void testWallShowsOwnPosts() throws UnableToFindUserException, InterruptedException {
        User user = app.getUserManager().addUser("tester");
        app.getUserManager().post(user, "post 1");
        Thread.sleep(1);
        app.getUserManager().post(user, "post 2");
        Thread.sleep(1);
        app.getUserManager().post(user, "post 3");
        
        command.execute("tester wall");
        
        Post[] postsPrinted = printer.getPostsPrinted();
        assertEquals("post 3", postsPrinted[0].getMessage());
        assertEquals("post 2", postsPrinted[1].getMessage());
        assertEquals("post 1", postsPrinted[2].getMessage());
    }
    
    @Test
    public void testWallShowsFollowPosts() throws UnableToFindUserException, InterruptedException {
        UserManager userManager = app.getUserManager();
        User user = userManager.addUser("follower");
        User target = userManager.addUser("target");
        userManager.follow(user, target);
        userManager.post(user, "looking to follow");
        Thread.sleep(1);
        userManager.post(target, "follow me");
        Thread.sleep(1);
        userManager.post(user, "ok!");
        Thread.sleep(1);
        userManager.post(target, "thanks for the follow");
        
        command.execute("follower wall");
        
        Post[] postsPrinted = printer.getPostsPrinted();
        assertEquals("thanks for the follow", postsPrinted[0].getMessage());
        assertEquals("ok!", postsPrinted[1].getMessage());
        assertEquals("follow me", postsPrinted[2].getMessage());
        assertEquals("looking to follow", postsPrinted[3].getMessage());
    }

}
