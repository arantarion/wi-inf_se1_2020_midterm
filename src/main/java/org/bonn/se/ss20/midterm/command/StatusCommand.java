package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;

public class StatusCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        if (params.length != 3) {
            System.out.println("invalid use of command. please see \"help analyze\"");
        }

        if (!HelperFunctions.isNumeric(params[1])) {
            System.out.println("Please specify a valid user story ID");
        } else if (!Container.getInstance().containsUserStory(Integer.parseInt(params[1]))) {
            System.out.println("There is no user story with the id: " + params[1]);
        }

        if (!HelperFunctions.isValidStatus(params[2])) {
            System.out.println("Please use 'done', 'progress' or 'todo' as status");
        }

        int userStoryID = Integer.parseInt(params[1]);
        UserStory userStory = Container.getInstance().getUserStory(userStoryID);
        userStory.setStatus(params[2]);

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
                "possible states: done, progress, todo\n\n" +
                "Usage\n" +
                "\tstatus <user story id> <status>\n\n" +
                "See also\n" +
                "\tdump - Lists all user stories";
    }
}
