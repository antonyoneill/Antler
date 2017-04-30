package tech.antonyoneill.antler;

import java.io.Console;

/**
 * This is the entry point for Antler.
 * 
 * @author @antonyoneill
 */
public class Antler {

    public static void main(String[] args) {
        Console console = System.console();

        if (console == null) {
            System.err.println("No console available");
            System.exit(1);
        }

        AntlerApplication app = new AntlerApplication(console);
        app.run();
    }

}
