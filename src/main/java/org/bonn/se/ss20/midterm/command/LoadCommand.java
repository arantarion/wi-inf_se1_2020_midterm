package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.model.PersistenceManager;

import java.io.FileNotFoundException;

public class LoadCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
            PersistenceManager.getInstance().load();
    }

    @Override
    public void undo() {
        Container.getInstance().removeAll();
    }

    @Override
    public CommandInterface clone() {
        return null;
    }

    @Override
    public String usage() {
        return "Command\n" +
                "\tload - load user stories from a local file\n\n" +
                "Usage\n" +
                "\tload -> then specify a path or filename\n\n" +
                "See also\n" +
                "\tstore - save all user stories locally to your machine";
    }
}
