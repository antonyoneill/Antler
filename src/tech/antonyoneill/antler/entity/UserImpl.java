/**
 * 
 */
package tech.antonyoneill.antler.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a user that has a collection of Posts on their timeline, and has a list of people they follow.
 * 
 * @author @antonyoneill
 */
/*
 * Since I know that this application will only be available to users using the same terminal I have not introduced
 * any thread safe data structures on the timeline, or the follows fields.
 */
public class UserImpl implements User {

	public static final String ERROR_USERNAME_NULL_OR_EMPTY = "Argument username cannot be null or empty";

	public static final String ERROR_USERNAME_CONTAINS_SPACE = "Argument username cannot contain whitespace";
	
	private String username;
	private List<Post> timeline;
	private Set<User> follows;
	
	/**
	 * Create a new user
	 * 
	 * @param username
	 */
	public UserImpl(String username) {
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
	
	/* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.User#getUsername()
     */
	@Override
    public String getUsername() {
		return username;
	}
	
	/* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.User#getTimeline()
     */
	@Override
    public List<Post> getTimeline() {
		return timeline;
	}
	
	/* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.User#getFollows()
     */
	@Override
    public Set<User> getFollows() {
		return follows;
	}
	
	/* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.User#follow(tech.antonyoneill.antler.entity.UserImpl)
     */
	@Override
    public void follow(User user) {
	    if (user.equals(this)) {
	        return;
	    }
		this.follows.add(user);
	}
	
	/* (non-Javadoc)
     * @see tech.antonyoneill.antler.entity.User#post(tech.antonyoneill.antler.entity.PostImpl)
     */
	@Override
    public void post(Post post) {
		this.timeline.add(post);
	}
	
	/* (non-Javadoc)
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
