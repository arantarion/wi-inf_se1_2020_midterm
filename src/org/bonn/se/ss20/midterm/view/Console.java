package org.bonn.se.ss20.midterm.view;

import org.bonn.se.ss20.midterm.util.HelperFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Henry Weckermann, Anton Drees
 *
 */

public class Console {

    public Console() {
    }

    // Used for user input of integer values and checks if they meet requirements
    public String inputInteger (String prompt) throws IOException {

        String input;
        BufferedReader  console_input = new BufferedReader(new InputStreamReader(System.in));
        boolean arbitrary = prompt.contains("effort") ^ prompt.contains("ID");

        while (true) {
            System.out.print(prompt);
            input = console_input.readLine();
            if(arbitrary) {
                if (!HelperFunctions.checkPosInteger(input, true)) {
                    System.out.println("Please use a positive integer value here!");
                    continue;
                }
                return input;
            }

            if ( !HelperFunctions.checkPosInteger(input, false) ) {
                System.out.println("Please use a positive integer value between 1 and 5 here!");
                continue;
            }
            return input;
        }
    }

    // Used for user input of strings
    public String inputText (String prompt) throws IOException {

        String input;
        BufferedReader console_input = new BufferedReader ( new InputStreamReader(System.in) );

        System.out.print(prompt);
        input = console_input.readLine();
        return input;
    }

}
