package tech.antonyoneill.antler.entity;

import java.time.Instant;

/**
 * This class represents a Post, or Message that has been added to a
 * {@link User}'s timeline.
 * 
 * @author @antonyoneill
 *
 */
public class Post {

    public static final String ERROR_CREATED_DATE_NULL      = "Argument createdDate must not be null";
    public static final String ERROR_CREATED_DATE_IN_FUTURE = "Argument createdDate cannot be in the future";
    public static final String ERROR_USER_NULL              = "Argument user cannot must not be null";
    public static final String ERROR_MESSAGE_NULL_OR_EMPTY  = "Argument message must not be null or empty";

    private Instant            createdDate;
    private User               author;
    private String             message;

    /**
     * 
     * @param createdDate
     * @param message
     */
    public Post(Instant createdDate, User user, String message) {
        if (createdDate == null) {
            throw new IllegalArgumentException(ERROR_CREATED_DATE_NULL);
        }
        if (createdDate.isAfter(Instant.now())) {
            throw new IllegalArgumentException(ERROR_CREATED_DATE_IN_FUTURE);
        }
        if (user == null) {
            throw new IllegalArgumentException(ERROR_USER_NULL);
        }
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_OR_EMPTY);
        }

        this.createdDate = createdDate;
        this.author = user;
        this.message = message.trim();
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * This method will return a string representation of the Post.
     * 
     * @return a String representation of the post
     */
    public String toString() {
        return message;
    }

    /**
     * @return The author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * @return The {@link Instant} the entity was created
     */
    public Instant getCreatedInstant() {
        return createdDate;
    }
}
