package tech.antonyoneill.antler.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest {

	@Test
	public void testCanBeCreated() {
		User user = testForException("valid user", "antonyoneill", null);
		assertEquals("Username is accessible", "antonyoneill", user.getUsername());
		assertEquals("The user is following no one", 0, user.getFollows().size());
		assertEquals("The user has no posts", 0, user.getTimeline().size());
	}
	
	@Test
	public void testCanFollow() {
		User userOne = new UserImpl("one");
		UserImpl userTwo = new UserImpl("two");
		UserImpl userThree = new UserImpl("three");
		
		userOne.follow(userTwo);
		assertTrue("UserOne is following UserTwo", userOne.getFollows().contains(userTwo));
		assertEquals("UserOne is only following UserTwo", 1, userOne.getFollows().size());
		
		userOne.follow(userTwo);
		assertEquals("UserOne can only follow UserTwo once", 1, userOne.getFollows().size());
		
		userOne.follow(userThree);
		assertTrue("UserOne is still following UserTwo", userOne.getFollows().contains(userTwo));
		assertTrue("UserOne is also following UserThree", userOne.getFollows().contains(userThree));
		assertEquals("UserOne is following both UserTwo and UserThree", 2, userOne.getFollows().size());
	}
	
	@Test
	public void testCannotFollowThemselves() {
	    User user = new UserImpl("Test");
        assertEquals("The user is following no one", 0, user.getFollows().size());
	    user.follow(user);
        assertEquals("The user is still following no one", 0, user.getFollows().size());
	}
	
	@Test
	public void testCanPost() {
		User user = new UserImpl("tester");
		PostImpl postOne = new PostImpl(Instant.now().minusSeconds(60), user, "First Post");
		user.post(postOne);
		assertEquals("User has a post", 1, user.getTimeline().size());
		
		PostImpl postTwo = new PostImpl(Instant.now(), user, "Second Post");
		user.post(postTwo);
		assertEquals("User has two posts", 2, user.getTimeline().size());
	}

	@Test
	public void testCannotHaveNullUsername() {
		testForException("username is null", null, UserImpl.ERROR_USERNAME_NULL_OR_EMPTY);
	}

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

}
