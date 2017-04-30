package tech.antonyoneill.antler.command;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;
import tech.antonyoneill.antler.utils.ReverseInstantComparator;

public class WallCommand implements Command {

    private AntlerApplication app;
    private Pattern pattern = Pattern.compile("([^\\s]+) wall");
    
    public WallCommand(AntlerApplication app) {
        this.app = app;
    }
    
    @Override
    public boolean isInputValid(String input) {
        return pattern.matcher(input).matches();
    }

    @Override
    public void execute(String input) throws UnableToFindUserException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            return;
        }
        
        String username = matcher.group(1);
        User user = app.getUserManager().getUser(username);
        
        SortedSet<Post> sortedPosts = new TreeSet<>(new ReverseInstantComparator());
        sortedPosts.addAll(user.getTimeline());
        user.getFollows().forEach((innerUser) -> {
            sortedPosts.addAll(innerUser.getTimeline());
        });
        app.printPosts(sortedPosts);
    }


}
