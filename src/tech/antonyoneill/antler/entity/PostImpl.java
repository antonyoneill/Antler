package tech.antonyoneill.antler.entity;

import java.time.Instant;

/**
 * This class represents a Post, or Message that has been added to a
 * {@link UserImpl}'s timeline.
 * 
 * @author @antonyoneill
 *
 */
public class PostImpl implements Post {

    public static final String ERROR_CREATED_DATE_NULL      = "Argument createdDate must not be null";
    public static final String ERROR_CREATED_DATE_IN_FUTURE = "Argument createdDate cannot be in the future";
    public static final String ERROR_USER_NULL = "Argument user cannot must not be null";
    public static final String ERROR_MESSAGE_NULL_OR_EMPTY  = "Argument message must not be null or empty";

    private Instant            createdDate;
    private User               author;
    private String             message;

    /**
     * 
     * @param createdDate
     * @param message
     */
    public PostImpl(Instant createdDate, User user, String message) {
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

    /* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.Post#getMessage()
     */
    @Override
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

    /* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.Post#getAuthor()
     */
    @Override
    public User getAuthor() {
        return author;
    }

    /* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.HasCreatedInstant#getCreatedInstant()
     */
    @Override
    public Instant getCreatedInstant() {
        return createdDate;
    }
}
