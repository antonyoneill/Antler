package tech.antonyoneill.antler.utils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.PostImpl;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.entity.UserImpl;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;

/**
 * This class is responsible for keeping a list of users.
 * 
 * @author @antonyoneill
 *
 */
public class UserManager {
    
    private Map<String, User> users = new HashMap<>();
    
    /**
     * Return a user if we have a record of it, or throw an exception otherwise
     * 
     * @param username
     * 
     * @return {@link User} If the user exists
     * @throws UnableToFindUserException If the user doesn't exist
     */
    public User getUser(String username) throws UnableToFindUserException {
        User user = users.get(username);
        if (user == null) {
            throw new UnableToFindUserException(username);
        }
        return user;
    }

    /**
     * Add a new user to the application
     * 
     * @param username
     * @return The new user
     */
    public User addUser(String username) {
        User user = new UserImpl(username);
        users.put(username, user);
        return user;
    }
    
    public void follow(User follower, User user) {
        if (follower.equals(user)) {
            return;
        }
        follower.getFollows().add(user);
    }
    
    public void post(User user, String message) {
        Post post = new PostImpl(Instant.now(), user, message);
        user.getTimeline().add(post);
    }
}
