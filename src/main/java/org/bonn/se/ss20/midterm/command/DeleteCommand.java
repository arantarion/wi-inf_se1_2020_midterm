package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class DeleteCommand implements CommandInterface {
    @Override
    public void execute(String[] params) {

        if (params == null || params.length == 0) {
            System.out.println("Invalid syntax. Please see 'help delete'");
            return;
        }

        if (params[0].equals("-userstory")
                && params.length == 2
                && HelperFunctions.isNumeric(params[1])
                && Container.getInstance().containsUserStory(Integer.parseInt(params[1]))) {
            Container.getInstance().removeUserStory(Integer.parseInt(params[1]));
            System.out.println("Removed user story with ID " + params[1]);
        } else if (params[0].equals("-actor")
                && Container.getInstance().containsActor(params[1])
                && params.length == 2) {
            Container.getInstance().removeActor(params[1]);
            System.out.println("Successfully removed actor: " + params[1]);
        } else if (params[0].equals("-all")) {
            Container.getInstance().removeAll();
            System.out.println("Successfully removed all user stories.");
        } else {
            System.out.println("Invalid syntax. Please see 'help delete'");
        }
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
                "\tdelete - delete user stories or actors\n\n" +
                "Usage\n" +
                "\tdelete -userstory <ID> -> deletes the user story with the specified <ID>\n" +
                "\tdelete -actor <actor> -> deletes the specified actor\n" +
                "\tdelete -all -> deletes all user stories\n\n" +
                "See also\n" +
                "\tenter - add a user story to the system.";
    }
}
