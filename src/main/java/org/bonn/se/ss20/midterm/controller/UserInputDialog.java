package org.bonn.se.ss20.midterm.controller;

import org.bonn.se.ss20.midterm.command.*;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.view.Console;

import java.util.Arrays;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class UserInputDialog {

    private static final double VERSION = 1.2;

    public UserInputDialog() {
        setUpCommands();
    }

    private void setUpCommands() {
        Container.getInstance().addCommand("enter", new EnterCommand());
        Container.getInstance().addCommand("dump", new DumpCommand());
        Container.getInstance().addCommand("load", new LoadCommand());
        Container.getInstance().addCommand("store", new StoreCommand());
        Container.getInstance().addCommand("analyze", new AnalyzeCommand());
        Container.getInstance().addCommand("help", new HelpCommand());
        Container.getInstance().addCommand("undo", new UndoCommand());
        Container.getInstance().addCommand("exit", new ExitCommand());
        Container.getInstance().addCommand("addElement", new AddElementCommand());
        Container.getInstance().addCommand("actors", new ActorCommand());
        Container.getInstance().addCommand("status", new StatusCommand());
        Container.getInstance().addCommand("empty", new EmptyCommand());
    }

    public void start() {
        System.out.println("User Story creation and management tool - Version " + VERSION);
        System.out.println("Copyright (C) Henry Weckermann & Anton Drees");
        System.out.println("License: Open Source");
        System.out.println("Current user: " + System.getProperty("user.name"));
        System.out.println("\nTo display the help text enter \"help\" below\n" +
                "or for a specific command \"help <command>\"");

        String userInput = null;

        while (true) {
            try {
                userInput = Console.readConsole();
            } catch (Exception ex) {
                System.out.println("Something has gone horribly wrong");
                ex.printStackTrace();
            }

            String[] commands;
            if (userInput != null) {
                commands = userInput.split(" ");

                if (commands.length > 0) {
                    Container.getInstance().getCommand(commands[0]).execute(Arrays.copyOfRange(commands, 1, commands.length));
                }

            }
        }
    }
}
