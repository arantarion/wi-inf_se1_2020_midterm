package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.model.PersistenceManager;

public class StoreCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        PersistenceManager.getInstance().store();
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
                "\tstore - save all user stories locally to your machine\n\n" +
                "Usage\n" +
                "\tstore -> then specify a path or filename\n\n" +
                "See also\n" +
                "\tload - load user stories from a local file";
    }
}
