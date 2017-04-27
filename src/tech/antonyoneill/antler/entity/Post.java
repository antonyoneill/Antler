package tech.antonyoneill.antler.entity;

/**
 * Defines the interface for a social Post
 * 
 * @author @antonyoneill
 *
 */
public interface Post extends HasCreatedInstant {

    /**
     * @return The message
     */
    String getMessage();

    /**
     * @return The author
     */
    User getAuthor();

}