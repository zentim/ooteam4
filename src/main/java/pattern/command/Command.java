package main.java.pattern.command;

/**
 * 
 * Command Pattern - Command
 *
 */
public interface Command {
    public void execute();

    public void unExecute();

    public Command clone() throws CloneNotSupportedException;
}
