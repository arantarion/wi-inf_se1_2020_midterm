package org.bonn.se.ss20.midterm.controller;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;
import org.bonn.se.ss20.midterm.view.Console;
import org.bonn.se.ss20.midterm.view.UserOutputDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Henry Weckermann, Anton Drees
 *
 */

public class UserInputDialog {

    private static final double VERSION = 1.0;

    public void start() {
        System.out.println("User Story creation and management tool - Version " + VERSION);
        System.out.println("Copyright (C) Henry Weckermann & Anton Drees");
        System.out.println("License: WeissNix GmbH");
        System.out.println("Current user: " + System.getProperty("user.name"));
        System.out.println("\nTo display the help text enter \"help\" below");


        String commandLine;
        BufferedReader console = new BufferedReader ( new InputStreamReader(System.in) );
        Container userStoryContainer = Container.getInstance();

        while(true) {
            System.out.print("> ");

            try {
                commandLine = console.readLine().trim();
                String[] commandsParam = commandLine.split(" ");

                if (commandLine.equalsIgnoreCase("help")) {

                    help();

                } else if (commandLine.equalsIgnoreCase("enter")) {

                    UserStory myStory = enter();
                    userStoryContainer.addUserStory(myStory);

                } else if (commandLine.equalsIgnoreCase("store")) {

                    userStoryContainer.store();

                } else if (commandLine.equalsIgnoreCase("load")) {

                    userStoryContainer.load();

                } else if (commandLine.equalsIgnoreCase("dump")) {

                    startOutput();

                } else if (commandsParam[0].equalsIgnoreCase("completed")
                        && HelperFunctions.checkPosInteger(commandsParam[1], true)) {

                    System.out.println(userStoryContainer.markUserStory( Integer.parseInt(commandsParam[1])) );

                } else if (commandLine.equalsIgnoreCase("exit")) {

                    System.out.println("Goodbye");
                    System.exit(0);

                } else if (commandLine.equalsIgnoreCase("createTests")){

                        for (int id = 1; id <= 25; id++) {
                            String title = "This is the " + id + ". user story";
                            int overvalue = (int)((Math.random()) * 5 + 1);
                            int penalty = (int)((Math.random()) * 5 + 1);
                            int effort =(int)((Math.random()) * 30 + 1);
                            int risk = (int)((Math.random()) * 5 + 1);
                            UserStory mystory = new UserStory(id, title, overvalue, penalty, effort, risk);
                            userStoryContainer.addUserStory(mystory);
                        }

                } else  {

                    System.out.println("Please enter a valid command. For a list of commands enter \"help\" ");

                }

            } catch (Exception e)  {
                System.out.println( e.getMessage() );
            }
        }
    }

    private void startOutput() {
        UserOutputDialog userOutDialog = new UserOutputDialog();
        userOutDialog.display( Container.getInstance().getUserStoriesAsListOfDTOs() );
    }

    // Create a UserStory object with user inputted data
    private static UserStory enter() throws IOException {

        Console myConsole = new Console();
        int id = Integer.parseInt(myConsole.inputInteger("Please input an ID: "));
        String title =  myConsole.inputText("Please input a title for your user story: ");
        int overvalue = Integer.parseInt(myConsole.inputInteger("Please input overvalue: "));
        int penalty = Integer.parseInt(myConsole.inputInteger("Please input penalty: "));
        int effort = Integer.parseInt(myConsole.inputInteger("Please input effort: "));
        int risk = Integer.parseInt(myConsole.inputInteger("Please input risk: "));

        System.out.println("Successfully created user story: " + title);
        return new UserStory(id, title, overvalue, penalty, effort, risk);

    }

    // print the HELP Text
    private static void help() {
        System.out.println("ENTER - Enter a User Story");
        System.out.println("\tFormat: Follow the instructions on screen");
        System.out.println("\tCaution: only use positive integer values for Gloger's formula");
        System.out.println("\nSTORE - Store User Stories locally on your hard drive");
        System.out.println("\tFormat: Follow the instructions on screen");
        System.out.println("\tInformation: This can also be a full filepath to the user story file (no file extension)");
        System.out.println("\nLOAD - Loads user stories from a saved file on your hard-drive");
        System.out.println("\tFormat: Follow the instructions on screen");
        System.out.println("\tInformation: This can also be a full filepath to the user story file (no file extension)");
        System.out.println("\nDUMP - Prints all user stories");
        System.out.println("\nCOMPLETED - Mark a user story as completed. It won't be shown in the list anymore");
        System.out.println("\tUsage: completed [ID] -> mark user story with the ID as completed");
        System.out.println("\nEXIT - Quits the program");
        System.out.println("\nHELP  - Displays this help function");
    }
}
