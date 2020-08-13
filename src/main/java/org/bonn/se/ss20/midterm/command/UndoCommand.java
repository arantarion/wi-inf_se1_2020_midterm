package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.model.Container;

public class UndoCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        Container.getInstance().undoHistory();
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
                "\tundo - undo the last command\n\n" +
                "Usage\n" +
                "\tundo";
    }
}
