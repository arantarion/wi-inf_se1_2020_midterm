package org.bonn.se.ss20.midterm.command;

public class StatusCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        //TODO
    }

    @Override
    public void undo() {

    }

    @Override
    public CommandInterface clone() {
        return null;
    }

    @Override
    public String usage() {
        return "Command\n" +
                "\tstatus - list just undone user stories\n\n" +
                "Usage\n" +
                "\tdumpUndone\n\n" +
                "See also\n" +
                "\tdump - Lists all user stories";
    }
}
