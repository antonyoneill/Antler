package tech.antonyoneill.antler.exceptions;

public class UnableToFindUserException extends CommandException {

    private static final long serialVersionUID = -2203446061788292333L;
    private String            username;

    public UnableToFindUserException(String username) {
        this.username = username;
    }

    public String toString() {
        return String.format("Unable to find user [%s]", username);
    }

}
