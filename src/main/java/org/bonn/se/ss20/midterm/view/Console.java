package org.bonn.se.ss20.midterm.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class Console {

    private static BufferedReader bufferedInput = null;
    private static InputStreamReader isrInput = null;

    public Console() {
    }

    private static void init() {
        if (Console.bufferedInput == null & Console.isrInput == null) {
            Console.isrInput = new InputStreamReader(System.in);
            Console.bufferedInput = new BufferedReader(Console.isrInput);
        }
    }

    public static String readConsole() {

        Console.init();
        System.out.print("> ");

        try {
            return bufferedInput.readLine();
        } catch (IOException ex) {
            System.out.println("Something went horribly wrong in the Console!");
            ex.printStackTrace();
        }

        return null;
    }

}
