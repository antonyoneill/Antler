package tech.antonyoneill.antler.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import org.junit.Test;

import tech.antonyoneill.antler.entity.PostImpl;
import tech.antonyoneill.antler.entity.UserImpl;

public class PostTest {

    User user = new UserImpl("Tester");
    
	@Test
	public void testPostCreation() {
		Instant createdDate = Instant.now();
		Post post = testForException("args are valid", createdDate, "Hello", null);
		assertEquals("The message is accessible", "Hello", post.getMessage());
		assertEquals("The toString method contains the date", "Hello (" + createdDate.toString() + ")",
				post.toString());
	}

	@Test
	public void testNullCreatedDate() {
		testForException("createdDate is null", null, "Hello", PostImpl.ERROR_CREATED_DATE_NULL);
	}

	@Test
	public void testFutureCreatedDate() {
		testForException("createdDate in the future", Instant.now().plusSeconds(60), "Hello", PostImpl.ERROR_CREATED_DATE_IN_FUTURE);
	}

	@Test
	public void testNullMessage() {
		testForException("message is null", Instant.now(), null, PostImpl.ERROR_MESSAGE_NULL_OR_EMPTY);
	}
	
	@Test
	public void testNullEmpty() {
		testForException("message is empty", Instant.now(), "", PostImpl.ERROR_MESSAGE_NULL_OR_EMPTY);
	}

	private Post testForException(String reason, Instant date, String message, String exceptionMessage) {
		Exception exception = null;
		Post post = null;
		try {
			post = new PostImpl(date, user, message);
		} catch (Exception e) {
			exception = e;
		}
		if (exceptionMessage == null) {
			assertNull("An exception should not be thrown when " + reason, exception);
		} else {
			assertTrue("An IllegalArugmentException was thrown when " + reason,
					exception instanceof IllegalArgumentException);
			assertEquals("The correct message is returned when " + reason, exceptionMessage, exception.getMessage());
		}
		return post;
	}

}
