package tech.antonyoneill.antler.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.User;

public class ReadCommand implements Command {

    private AntlerApplication app;
    private Pattern pattern = Pattern.compile("([^\\s]+)");
    
    public ReadCommand(AntlerApplication app) {
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
        
        String username = matcher.group(0);
        User user = app.getUser(username, false);
        if (user == null) {
            return;
        }

        app.printPosts(user.getTimeline());
    }

}
