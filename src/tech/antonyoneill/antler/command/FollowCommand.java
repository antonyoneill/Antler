package tech.antonyoneill.antler.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.User;

public class FollowCommand implements Command {

    private AntlerApplication app;
    private Pattern pattern = Pattern.compile("([^\\s]+) follows ([^\\s]+)");
    
    public FollowCommand(AntlerApplication app) {
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
        
        String leftUsername = matcher.group(1);
        String rightUsername = matcher.group(2);

        User leftUser = app.getUser(leftUsername, false);
        User rightUser = app.getUser(rightUsername, false);

        if (leftUser == null || rightUser == null) {
            return;
        }

        leftUser.follow(rightUser);
    }

}
