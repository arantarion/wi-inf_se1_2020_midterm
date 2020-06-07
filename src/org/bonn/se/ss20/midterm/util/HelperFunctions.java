package org.bonn.se.ss20.midterm.util;

/**
 *
 * @author Henry Weckermann, Anton Drees
 *
 */

public class HelperFunctions {

    // determines if a String represents a positive integer value above zero
    public static boolean checkPosInteger(String param, boolean arbitrary) {

        int value;

        try {
            value = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return false;
        }

        if (!arbitrary) {
            return (value > 0 && value <= 5);
        } else {
            return value > 0;
        }
    }

    // Calculate priority based on Gloger's formula
    public static double glogerFormular(int overvalue, int penalty, int effort, int risk) {
        return ((double)overvalue + (double)penalty) / ((double)effort + (double)risk);
    }

}
