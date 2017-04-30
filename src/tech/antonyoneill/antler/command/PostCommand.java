package tech.antonyoneill.antler.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.User;
import tech.antonyoneill.antler.exceptions.CommandSyntaxException;
import tech.antonyoneill.antler.exceptions.UnableToFindUserException;

public class PostCommand implements Command {

    private AntlerApplication app;
    private Pattern           pattern = Pattern.compile("([^\\s]+) -> (.+$)");

    public PostCommand(AntlerApplication app) {
        this.app = app;
    }

    @Override
    public boolean isInputValid(String input) {
        return pattern.matcher(input).matches();
    }

    @Override
    public void execute(String input) throws CommandSyntaxException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new CommandSyntaxException(input);
        }

        String username = matcher.group(1);
        User user;
        try {
            user = app.getUserManager().getUser(username);
        } catch (UnableToFindUserException e) {
            // It's OK if the user wasn't found.
            user = app.getUserManager().addUser(username);
        }

        String message = matcher.group(2);

        try {
            app.getUserManager().post(user, message);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

}
