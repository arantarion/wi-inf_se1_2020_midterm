package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.exception.ContainerException;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;
import org.bonn.se.ss20.midterm.view.Console;

public class EnterCommand implements CommandInterface {

    private int id;

    public static Integer enter() {
        UserStory us = new UserStory();

        us.setId(Container.getInstance().getHighestId() + 1);
        us.setTitle(HelperFunctions.readText("Enter a name for your user story: "));
        us.setActor(HelperFunctions.readText("Specify an actor: "));
        us.setDescription(HelperFunctions.readText("Enter a description for the user story: "));
        us.setDetails(HelperFunctions.readText("Enter the details of the user story: "));
        us.setAkzeptanz(HelperFunctions.readText("Enter acceptance criteria: "));
        us.setEpic(HelperFunctions.readText("Specify an epic for the user story"));
        us.setMehrwert(HelperFunctions.readInt("Overvalue: ", true));
        us.setStrafe(HelperFunctions.readInt("Penalty: ", true));
        us.setRisiko(HelperFunctions.readInt("Risk: ", true));
        us.setAufwand(HelperFunctions.readInt("Effort: ", false));
        us.setStatus("");

        try {
            Container.getInstance().addUserStory(us);
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        return us.getId();
    }

    public static void again() {
        System.out.println("Would you like to add another user story? (y|N)");

        if ("y".equals(Console.readConsole())) {
            Container.getInstance().getCommand("enter").execute(null);

        }
        System.out.println("Awaiting your commands. Try \"Help\" for a list of all commands");
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void execute(String[] params) {

        id = enter();
        Container.getInstance().addHistory(clone());
        again();

    }

    @Override
    public void undo() {
        Container.getInstance().removeUserStory(id);
    }

    @Override
    public CommandInterface clone() {
        EnterCommand enterCommand = new EnterCommand();
        enterCommand.setId(id);
        return enterCommand;
    }

    @Override
    public String usage() {
        return "Command\n" +
                "\tenter - enter a user story\n\n" +
                "Usage\n" +
                "\tenter -> then follow the instructions on screen\n\n" +
                "See also\n" +
                "\tstore - save all user stories locally to your machine\n" +
                "\tload - load user stories from a local file";
    }

}
