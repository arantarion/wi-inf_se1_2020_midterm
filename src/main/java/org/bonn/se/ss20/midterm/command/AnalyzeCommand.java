package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.util.Analytics.AnalyticsUtility;
import org.bonn.se.ss20.midterm.util.Analytics.Strategy;

public class AnalyzeCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        Strategy strategy = new Strategy();
        strategy.setStrategy(new AnalyticsUtility());
        strategy.analyze(params);
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
                "\tanalyze - analyzes the quality of your user stories\n\n" +
                "Usage\n" +
                "\tanalyze <id> \t\t\t-> Analyzes the quality of the user story with the given id\n" +
                "\tanalyze <id> -details \t\t-> Analyzes the quality and returns a detailed explanation\n" +
                "\tanalyze <id> -details -hints \t-> Analyzes, prints detailed explanation and shows hints to improve the quality\n" +
                "\tanalyze -all \t\t\t-> Analyzes all user stories and calculates the average quality\n" +
                "Example\n" +
                "\tanalyze 3 -details -hints\n" +
                "\tanalyze 2 -details\n" +
                "\tanalyze -all\n" +
                "See also\n" +
                "\tTo add an actor use the \"addElement\" command";
    }
}
