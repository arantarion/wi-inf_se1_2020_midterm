package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;

public class AddElementCommand implements CommandInterface {

    private String name;

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(String[] params) {
        setName(HelperFunctions.addActor(params));

        if (name != null) {
            Container.getInstance().addHistory(clone());
        } else {
            System.out.println("could not find this command. Please see \"HELP\"");
        }

    }

    @Override
    public void undo() {
        Container.getInstance().removeActor(name);
    }

    @Override
    public CommandInterface clone() {
        AddElementCommand addElementCommand = new AddElementCommand();
        addElementCommand.setName(name);
        return addElementCommand;
    }

    @Override
    public String usage() {
        return "Command\n" +
                "\taddElement - Add an actor to the system\n\n" +
                "Usage\n" +
                "\taddElement <actor>\n\n" +
                "See also\n" +
                "\tactors - list all available actors";
    }
}
