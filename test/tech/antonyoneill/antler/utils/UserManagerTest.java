package tech.antonyoneill.antler.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.entity.User;

public class UserManagerTest {

    UserManager userManager = new UserManager();

    public void testCanFollow() {
        User userOne = new User("one");
        User userTwo = new User("two");
        User userThree = new User("three");

        userManager.follow(userOne, userTwo);
        assertTrue("UserOne is following UserTwo", userOne.getFollows().contains(userTwo));
        assertEquals("UserOne is only following UserTwo", 1, userOne.getFollows().size());

        userManager.follow(userOne, userTwo);
        assertEquals("UserOne can only follow UserTwo once", 1, userOne.getFollows().size());

        userManager.follow(userOne, userThree);
        assertTrue("UserOne is still following UserTwo", userOne.getFollows().contains(userTwo));
        assertTrue("UserOne is also following UserThree", userOne.getFollows().contains(userThree));
        assertEquals("UserOne is following both UserTwo and UserThree", 2, userOne.getFollows().size());
    }

    @Test
    public void testCannotFollowThemselves() {
        User user = new User("Test");
        assertEquals("The user is following no one", 0, user.getFollows().size());
        userManager.follow(user, user);
        assertEquals("The user is still following no one", 0, user.getFollows().size());
    }

    @Test
    public void testCanPost() {
        User user = new User("tester");

        userManager.post(user, "First Post");
        assertEquals("User has a post", 1, user.getTimeline().size());

        userManager.post(user, "Second Post");
        assertEquals("User has two posts", 2, user.getTimeline().size());
    }

}
