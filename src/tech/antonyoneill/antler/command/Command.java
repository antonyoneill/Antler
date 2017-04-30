package tech.antonyoneill.antler.command;

import tech.antonyoneill.antler.exceptions.CommandException;

public interface Command {

    public boolean isInputValid(String input);

    public void execute(String input) throws CommandException;

}
