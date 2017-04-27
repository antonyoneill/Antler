package tech.antonyoneill.antler.command;

public interface Command {

    public boolean isInputValid(String input);
    
    public void execute(String input);
    
}
