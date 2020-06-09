package org.bonn.se.ss20.midterm.command;

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
        return null;//TODO
    }
}
