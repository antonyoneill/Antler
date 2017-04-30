package tech.antonyoneill.antler.exceptions;

/**
 * This is used when a command is unable to find a particular user
 * 
 * @author @antonyoneill
 *
 */
public class UnableToFindUserException extends CommandException {

    private static final long serialVersionUID = -2203446061788292333L;

    public UnableToFindUserException(String username) {
        super(String.format("Unable to find user [%s]", username));
    }
}
