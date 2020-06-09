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
        return null;//TODO
    }
}
