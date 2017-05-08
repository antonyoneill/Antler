package tech.antonyoneill.antler.utils;

import java.util.Collection;

import tech.antonyoneill.antler.entity.Post;

/**
 * The class responsible for printing {@link Post}s out to the console.
 * 
 * @author @antonyoneill
 *
 */
public class PostPrinter {

    private static final String PRINT_PATTERN = "%s - %s (%s)";

    private TimeUtil            timeUtil;

    public PostPrinter() {
        timeUtil = new TimeUtil();
    }

    public PostPrinter(TimeUtil timeUtil) {
        this.timeUtil = timeUtil;
    }

    /**
     * Print the given posts to screen
     * 
     * @param posts
     */
    public void printPosts(Collection<Post> posts) {
        posts.forEach((post) -> {
            System.out.println(String.format(PRINT_PATTERN,
                    post.getAuthor().getUsername(),
                    post.getMessage(),
                    timeUtil.timeDifference(post.getCreatedInstant())));
        });
    }

    /**
     * Print the given exception to screen
     * 
     * @param exception
     */
    public void printException(Exception exception) {
        System.err.println(exception.getMessage());
    }
}
