package tech.antonyoneill.antler;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.antonyoneill.antler.command.Command;
import tech.antonyoneill.antler.command.FollowCommand;
import tech.antonyoneill.antler.command.PostCommand;
import tech.antonyoneill.antler.command.ReadCommand;
import tech.antonyoneill.antler.command.WallCommand;
import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.entity.UserImpl;
import tech.antonyoneill.antler.utils.TimeUtil;

/**
 * The main application which provides a Twitter style messaging board.
 * 
 * It assumes that the users all use the same console, and that once the
 * application has been closed the users will not want to access the messages
 * again. That is to say, there's no persistence, concurrency protection, or
 * networking.
 * 
 * @author @antonyoneill
 *
 */
public class AntlerApplication {

    private Console           console;
    private Map<String, User> users;
    private List<Command>     commands;

    /**
     * Create the application and initialise the fields
     * 
     * @param console
     */
    public AntlerApplication(Console console) {
        this.users = new HashMap<>();
        this.console = console;
        commands = new ArrayList<>();
        commands.add(new ReadCommand(this));
        commands.add(new PostCommand(this));
        commands.add(new FollowCommand(this));
        commands.add(new WallCommand(this));
    }

    /**
     * Run the application! Commands are parsed and executed until an ^C is
     * passed into the input
     */
    public void run() {
        while (true) {
            String input = console.readLine("> ");

            for (Command command : commands) {
                if (command.isInputValid(input)) {
                    command.execute(input);
                }
            }
        }
    }

    /**
     * Outputs the list of posts to System.out
     * @param posts
     */
    public void printPosts(Collection<Post> posts) {
        posts.forEach((post) -> {
            System.out.println(String.format("%s - %s (%s)",
                    post.getAuthor().getUsername(),
                    post.getMessage(),
                    TimeUtil.timeDifference(post.getCreatedInstant())));
        });
    }

    /**
     * Get a user from the system. Optionally create it if it doesn't exist
     * @param username
     * @param createIfNull Whether to create the user or not if it doesn't exist
     * @return The user, or null if doesn't exist and not configured to create
     */
    public User getUser(String username, boolean createIfNull) {
        User user = users.get(username);
        if (user == null) {
            if (createIfNull) {
                user = addUser(username);
            } else {
                System.err.println(String.format("The user [%s] doesn't exist.", username));
            }
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
}
