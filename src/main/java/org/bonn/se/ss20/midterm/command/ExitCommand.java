package org.bonn.se.ss20.midterm.command;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class ExitCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        System.out.println("Exiting");
        System.exit(0);
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
                "\texit - quit the program\n\n" +
                "Usage\n" +
                "\texit";
    }
}
