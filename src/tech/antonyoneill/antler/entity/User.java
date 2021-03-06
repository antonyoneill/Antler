/**
 * 
 */
package tech.antonyoneill.antler.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a user that has a collection of Posts on their
 * timeline, and has a list of people they follow.
 * 
 * @author @antonyoneill
 */
/*
 * Since I know that this application will only be available to users using the
 * same terminal I have not introduced any thread safe data structures on the
 * timeline, or the follows fields.
 */
public class User {

    public static final String ERROR_USERNAME_NULL_OR_EMPTY  = "Argument username cannot be null or empty";

    public static final String ERROR_USERNAME_CONTAINS_SPACE = "Argument username cannot contain whitespace";

    private String             username;
    private List<Post>         timeline;
    private Set<User>          follows;

    /**
     * Create a new user
     * 
     * @param username
     */
    public User(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException(ERROR_USERNAME_NULL_OR_EMPTY);
        }
        if (username.matches(".*\\s+.*")) {
            throw new IllegalArgumentException(ERROR_USERNAME_CONTAINS_SPACE);
        }
        this.username = username;
        this.timeline = new ArrayList<>();
        this.follows = new HashSet<>();
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the users timeline
     */
    public List<Post> getTimeline() {
        return timeline;
    }

    /**
     * @return the Users this User follows
     */
    public Set<User> getFollows() {
        return follows;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof User)) {
            return false;
        }
        User otherUser = (User) other;
        return this.getUsername().equals(otherUser.getUsername());
    }
}
