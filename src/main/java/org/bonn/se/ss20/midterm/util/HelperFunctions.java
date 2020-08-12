package org.bonn.se.ss20.midterm.util;

import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.view.Console;

import java.util.Arrays;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class HelperFunctions {

    public static String readText(String msg) {

        if (!msg.equals("")) {
            System.out.println(msg);
        }
        return Console.readConsole();
    }

    public static int readInt(String msg, boolean withBoundary) {

        if (!msg.equals("")) {
            System.out.println(msg);
        }

        String userInput = Console.readConsole();

        if (HelperFunctions.checkInteger(userInput, withBoundary)) {
            return Integer.parseInt(userInput);
        } else {
            System.out.println("Please enter a valid integer.\n" +
                    "Everything except effort has to be between 1 and 5\n" +
                    "Effort has to be a positive integer");
            HelperFunctions.readInt(msg, withBoundary);
        }
        return -1;
    }

    // determines if a String represents a positive integer value above zero
    private static boolean checkInteger(String param, boolean withBoundary) {

        int value;

        try {
            value = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return false;
        }

        if (withBoundary) {
            return (value > 0 && value <= 5);
        } else {
            return value > 0;
        }
    }

    // Calculate priority based on Gloger's formula
    public static double calculatePriority(int overvalue, int penalty, int effort, int risk) {
        return ((double) overvalue + (double) penalty) / ((double) effort + (double) risk);
    }

    public static String addActor(String[] params) {
        if (params.length == 3) {
            if (params[0].equals("-") && params[1].equals("actor")) {
                if (Container.getInstance().containsActor(params[2])) {
                    System.out.println("Registered the actor: " + params[2]);
                    return Container.getInstance().addActor(params[2]);
                }
            }
        }
        return null;
    }

    public static void listAllActors() {
        System.out.println("Registered actors: ");

        for (String actor : Container.getInstance().getActors()) {
            System.out.println(actor);
        }
    }

    //var regex = /^([+-]?[1-9]\d*|0)$/

    public static boolean isNumeric(String str) {
        return str.matches("^([+-]?[1-9]\\d*|0)$");
    }

    public static boolean isValidStatus(String str) {
        String[] status = {"done", "todo", "progress"};
        return Arrays.asList(status).contains(str);
    }

}
