package org.bonn.se.ss20.midterm.command;

public class EmptyCommand implements CommandInterface {
    @Override
    public void execute(String[] params) {

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
        return "Nothing to see here";
    }
}
