package tech.antonyoneill.antler.command;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.entity.PostImpl;
import tech.antonyoneill.antler.entity.User;

public class PostCommand implements Command {

    private AntlerApplication app;
    private Pattern pattern = Pattern.compile("([^\\s]+) -> (.+$)");
    
    public PostCommand(AntlerApplication app) {
        this.app = app;
    }

    @Override
    public boolean isInputValid(String input) {
        return pattern.matcher(input).matches();
    }

    @Override
    public void execute(String input) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            return;
        }
        
        String username = matcher.group(1);
        User user = app.getUser(username, true);

        System.out.println("user created" + user.getUsername());
        String message = matcher.group(2);

        Post post = new PostImpl(Instant.now(), user, message);
        user.post(post);
    }

}
