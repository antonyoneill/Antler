package tech.antonyoneill.antler.exceptions;

/**
 * A super exception for any exceptions thrown by commands
 * 
 * @author @antonyoneill
 *
 */
public class CommandException extends Exception {

    private static final long serialVersionUID = -1490830351688182913L;

    protected CommandException(String message) {
        super(message);
    }
}
