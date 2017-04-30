package tech.antonyoneill.antler.entity;

import java.util.List;
import java.util.Set;

/**
 * Defines the interface for a social user
 * 
 * @author @antonyoneill
 *
 */
public interface User {

    /**
     * @return the username
     */
    public String getUsername();

    /**
     * @return the users timeline
     */
    public List<Post> getTimeline();

    /**
     * @return the Users this User follows
     */
    public Set<User> getFollows();

}