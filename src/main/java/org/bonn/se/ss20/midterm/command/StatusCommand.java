package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;

public class StatusCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {

        if (params.length != 2) {
            System.out.println("invalid use of command. please see \"help analyze\"");
            return;
        }

        if (!HelperFunctions.isNumeric(params[0])) {
            System.out.println("Please specify a valid user story ID");
            return;
        } else if (!Container.getInstance().containsUserStory(Integer.parseInt(params[0]))) {
            System.out.println("There is no user story with the id: " + params[0]);
            return;
        }

        if (!HelperFunctions.isValidStatus(params[1])) {
            System.out.println("Please use 'done', 'progress' or 'todo' as status");
            return;
        }

        int userStoryID = Integer.parseInt(params[0]);
        UserStory userStory = Container.getInstance().getUserStory(userStoryID);
        userStory.setStatus(params[1]);
        System.out.println("Successfully set the status of user story with ID " + userStoryID + " to '" + params[1] + "'");

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
                "\tstatus - change the status of an user story\n" +
                "\t\t\tpossible states: done, progress, todo\n\n" +
                "Usage\n" +
                "\tstatus <user story id> <status> -> sets the status of the specified user story\n\n" +
                "See also\n" +
                "\tdump - Lists all user stories";
    }
}
