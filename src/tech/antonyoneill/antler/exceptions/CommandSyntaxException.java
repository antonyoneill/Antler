package tech.antonyoneill.antler.exceptions;

/**
 * Thrown when the input for a command is invalid.
 * 
 * @author @antonyoneill
 *
 */
public class CommandSyntaxException extends CommandException {

    private static final long serialVersionUID = -5850583146053623894L;

    public CommandSyntaxException(String input) {
        super(String.format("Syntax not valid [%s] for command", input));
    }
}
