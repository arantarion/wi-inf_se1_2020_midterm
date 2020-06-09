package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.dto.UserStoryDTO;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.view.UserOutputDialog;

import java.util.stream.Collectors;

public class DumpUndoneCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        new UserOutputDialog().display(Container.getInstance().getUserStories(true)
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
        return null;//TODO
    }
}
