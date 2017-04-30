package tech.antonyoneill.antler;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import tech.antonyoneill.antler.command.Command;
import tech.antonyoneill.antler.command.FollowCommand;
import tech.antonyoneill.antler.command.PostCommand;
import tech.antonyoneill.antler.command.ReadCommand;
import tech.antonyoneill.antler.command.WallCommand;
import tech.antonyoneill.antler.exceptions.CommandException;
import tech.antonyoneill.antler.utils.PostPrinter;
import tech.antonyoneill.antler.utils.PostPrinterImpl;
import tech.antonyoneill.antler.utils.UserManager;

/**
 * Antler is a simple Twitter style messaging board.
 * 
 * It assumes that the users all use the same console, and that once the
 * application has been closed the users will not want to access the messages
 * again. That is to say there's no persistence, concurrency protection, or
 * networking.
 * 
 * @author @antonyoneill
 *
 */
public class AntlerApplication {

    private Console       console;
    private List<Command> commands;
    private UserManager   userManager = new UserManager();
    private PostPrinter printer = new PostPrinterImpl();

    /**
     * Create the application and initialise the fields.
     * 
     * @param console
     *            The system console to read from
     */
    public AntlerApplication(Console console) {
        this.console = console;

        commands = new ArrayList<>();
        commands.add(new ReadCommand(this));
        commands.add(new PostCommand(this));
        commands.add(new FollowCommand(this));
        commands.add(new WallCommand(this));
    }
    
    public AntlerApplication(Console console, PostPrinter printer) {
        this(console);
        this.printer = printer;
    }

    /**
     * Run the application! Commands are parsed and executed until an ^C is
     * passed into the input.
     */
    public void run() {
        while (true) {
            String input = console.readLine("> ");

            for (Command command : commands) {
                if (command.isInputValid(input)) {
                    try {
                        command.execute(input);
                    } catch (CommandException e) {
                        getPrinter().printException(e);
                    }
                }
            }
        }
    }
    
    /**
     * @return {@link UserManager} Containing the users for this application
     */
    public UserManager getUserManager() {
        return userManager;
    }
    
    /**
     * @return {@link PostPrinterImpl} To print to the console
     */
    public PostPrinter getPrinter() {
        return printer;
    }
}
