package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.dto.UserStoryDTO;
import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;
import org.bonn.se.ss20.midterm.view.UserOutputDialog;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DumpCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {

        //dump –status done

        if (params.length == 0) {
            new UserOutputDialog().display(Container.getInstance().getUserStories(false)
                    .stream()
                    .map(UserStoryDTO::new)
                    .sorted(Comparator.comparing(UserStoryDTO::getId))
                    .collect(Collectors.toList()));
            return;
        }




        if (params.length != 2 || !params[0].equals("-status") || !HelperFunctions.isValidStatus(params[1])) {
            System.out.println("invalid use of command. please see \"help dump\"");
            return;
        }

        switch(params[1]) {
            case "done":
                List<UserStory> doneUS = Container.getInstance().getUserStories(false).stream()
                        .filter(item -> item.getStatus().equals("done"))
                        .sorted(Comparator.comparing(UserStory::getId))
                        .collect(Collectors.toList());
                new UserOutputDialog().displayUS(doneUS);
                return;
            case "progress":
                List<UserStory> progressUS = Container.getInstance().getUserStories(true).stream()
                        .filter(item -> item.getStatus().equals("progress"))
                        .sorted(Comparator.comparing(UserStory::getId))
                        .collect(Collectors.toList());
                new UserOutputDialog().displayUS(progressUS);
                return;
            case "todo":
                List<UserStory> todoUS = Container.getInstance().getUserStories(true).stream()
                        .filter(item -> item.getStatus().equals("todo"))
                        .sorted(Comparator.comparing(UserStory::getId))
                        .collect(Collectors.toList());
                new UserOutputDialog().displayUS(todoUS);
                return;
            default:
                System.out.println("How could this happen?!?");
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
                "\tdump - Lists all user stories\n\n" +
                "Usage\n" +
                "\tdump\n\n" +
                "See also\n" +
                "\tdumpUndone - list just undone user stories";
    }
}
