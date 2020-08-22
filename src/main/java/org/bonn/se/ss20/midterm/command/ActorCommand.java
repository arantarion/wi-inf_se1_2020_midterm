package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.util.HelperFunctions;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class ActorCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        HelperFunctions.listAllActors();
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
                "\tactors - List all available actors\n\n" +
                "Usage\n" +
                "\tactors\n\n" +
                "See also\n" +
                "\taddElement - add new actors to the system";
    }

}
