package tech.antonyoneill.antler.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.CommandSyntaxException;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;
import tech.antonyoneill.antler.utils.UserManager;

public class FollowCommand implements Command {

    private AntlerApplication app;
    private Pattern           pattern = Pattern.compile("([^\\s]+) follows ([^\\s]+)");

    public FollowCommand(AntlerApplication app) {
        this.app = app;
    }

    @Override
    public boolean isInputValid(String input) {
        return pattern.matcher(input).matches();
    }

    @Override
    public void execute(String input) throws UnableToFindUserException, CommandSyntaxException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new CommandSyntaxException(input);
        }

        String leftUsername = matcher.group(1);
        String rightUsername = matcher.group(2);

        UserManager userManager = app.getUserManager();

        User leftUser = userManager.getUser(leftUsername);
        User rightUser = userManager.getUser(rightUsername);

        userManager.follow(leftUser, rightUser);
    }

}
