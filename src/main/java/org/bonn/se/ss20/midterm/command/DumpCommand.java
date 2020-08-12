package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.dto.UserStoryDTO;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.view.UserOutputDialog;

import java.util.stream.Collectors;

public class DumpCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        new UserOutputDialog().display(Container.getInstance().getUserStories(false)
                .stream().map(UserStoryDTO::new).collect(Collectors.toList()));
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
