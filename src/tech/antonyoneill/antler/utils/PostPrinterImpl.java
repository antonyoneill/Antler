package tech.antonyoneill.antler.utils;

import java.util.Collection;

import tech.antonyoneill.antler.entity.Post;

/**
 * The class responsible for printing {@link Post}s out to the console.
 * 
 * @author @antonyoneill
 *
 */
public class PostPrinterImpl implements PostPrinter {

    private static final String PRINT_PATTERN = "%s - %s (%s)";

    private TimeUtil            timeUtil;

    public PostPrinterImpl() {
        timeUtil = new TimeUtil();
    }

    public PostPrinterImpl(TimeUtil timeUtil) {
        this.timeUtil = timeUtil;
    }

    /*
     * (non-Javadoc)
     * 
     * @see tech.antonyoneill.antler.utils.PostPrinter#printPosts(java.util.
     * Collection)
     */
    @Override
    public void printPosts(Collection<Post> posts) {
        posts.forEach((post) -> {
            System.out.println(String.format(PRINT_PATTERN,
                    post.getAuthor().getUsername(),
                    post.getMessage(),
                    timeUtil.timeDifference(post.getCreatedInstant())));
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see tech.antonyoneill.antler.utils.PostPrinter#printException(java.lang.
     * Exception)
     */
    @Override
    public void printException(Exception exception) {
        System.err.println(exception.getMessage());
    }
}
