package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.model.Container;

public class HelpCommand implements CommandInterface {
    @Override
    public void execute(String[] params) {

        if (params.length == 0) {
            Container.getInstance().getCompleteHelp();
        } else if (params.length == 1) {
            try {
                Container.getInstance().getHelp(params[0]);
            } catch (NullPointerException ex) {
                System.out.println("Please specify a valid command\n");
                System.out.println("For all available commands use 'help': ");
                //Container.getInstance().getAllCommands().forEach(System.out::println);
            }
        } else {
            System.out.println("Please specify only one command");
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
                "\thelp - Prints help texts for commands\n\n" +
                "Usage\n" +
                "\thelp \t\t-> prints all help texts for all commands\n" +
                "\thelp <command> \t-> prints the help text for a specific command\n\n" +
                "Example\n" +
                "\thelp actors -> prints the help for the actors command";
    }
}