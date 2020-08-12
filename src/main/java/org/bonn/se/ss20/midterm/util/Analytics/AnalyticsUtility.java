package org.bonn.se.ss20.midterm.util.Analytics;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.model.Container;

import java.util.List;

public class AnalyticsUtility {


    public void analyze(String[] params) {
        if (params == null) {
            params = new String[0];
        }

        if (params.length != 1 && params.length != 3 && params.length != 5) {
            if (params[1].equals("-")) {
                System.out.println("command not found. please see \"help analyze\"");
            }
        }

        switch (params.length) {
            case 1:
                try {
                    AnalyticsUtility.analyze(Integer.parseInt(params[0]));
                } catch (NumberFormatException ex) {
                    System.out.println("please use a valid id");
                }
            case 2:
                if (params[0].equals("-") && params[1].equals("all")) {
                    analyzeAll();
                }
                break;
            case 3:
                analyze(Integer.parseInt(params[0])); //TODO catch
                if (params[2].equals("details")) {
                    printDetails(Integer.parseInt(params[0]));
                }
                break;
            case 5:
                analyze(Integer.parseInt(params[0])); //TODO catch
                if (params[2].equals("details")) {
                    printDetails(Integer.parseInt(params[0]));

                    if (params[3].equals("-") && params[4].equals("hints")) {
                        printHints(Integer.parseInt(params[0]));
                    }
                }
                break;
        }
    }


    private static int analyzeShortcommings(UserStory us) {
        int count = 0;

        //Titel
        if (us.getTitle().equals("") | wc(us.getTitle()) > 3) {
            count += 5;
        }

        //Beschreibung
        if (us.getDescription().equals("")) {
            count += 10;
        } else if (wc(us.getDescription()) > 50) {
            count += 5;
        }

        //Details
        if (us.getDetails().equals("")) {
            count += 15;
        } else if (wc(us.getDetails()) > 30) {
            count += 5;
        }

        //Akzeptanzkriterien
        if (us.getAkzeptanz().equals("")) {
            count += 15;
        } else if (wc(us.getAkzeptanz()) > 30) {
            count += 5;
        }

        //Epic
        if (us.getEpic().equals("") || wc(us.getEpic()) > 3) {
            count += 5;
        }

        //Actor
        if (us.getActor().equals("") || Container.getInstance().containsActor(us.getActor())) {
            count += 10;
        }

        return count;
    }


    private static int analyseSentenceStructure(UserStory us) {
        int count = 0;

        if (us.getDescription() != null &&
                (us.getDescription().length() < 1 ||
                        analyseSentence(us.getDescription(), ',') > (2 * analyseSentence(us.getDescription(), '.')))) {
            count += 5;
        }


        if (us.getDetails() != null &&
                (us.getDetails().length() < 1 ||
                        analyseSentence(us.getDetails(), ',') > (2 * analyseSentence(us.getDetails(), '.')))) {
            count += 5;
        }

        return count;
    }


    private static void analyze(Integer id) {
        if (Container.getInstance().containsUserStory(id)) {
            int points = AnalyticsUtility.verdict(Container.getInstance().getUserStory(id));
            System.out.println("Quality of user story with ID " + id + "is : " + points);
            System.out.println("It yields the following grade: " + getGrade(points));
        } else {
            System.out.println("Can'd find user story with id: " + id);
        }
    }


    private static void analyzeAll() {
        List<UserStory> list = Container.getInstance().getUserStories(false);
        int points = list.stream().mapToInt(AnalyticsUtility::verdict).sum();

        System.out.println("Average quality of your user stories: " + points / list.size());
        System.out.println("Grade: " + getGrade(points / list.size()));

    }


    private static void printDetails(Integer id) {
        UserStory userStory = Container.getInstance().getUserStory(id);
        System.out.println("\nDetails: ");

        if (analyseText(userStory.getTitle(), "The user story is missing a title (-5)", 3, "The title contains to many words (-5)")
                && analyseText(userStory.getDescription(), "The user story is missing a description (-10)", 50, "The description contains to many words (-5)")
                && analyseText(userStory.getDetails(), "The user story is missing details (-15)", 30, "The details are to long (-5)")
                && analyseText(userStory.getAkzeptanz(), "The user story is missing acceptance criteria (-15)", 30, "Acceptance criteria is too long (-5)")
                && analyseText(userStory.getEpic(), "The user story is missing an epic (-5)", 3, "The epic is too long (-5)")
                && analyzeActor(userStory.getActor(), "The user story is missing an actor (-10)", "The actor: %s is not registered in the system (-10)")) {
            System.out.println("Everything seems fine. Great work");
        }

    }


    private static void printHints(Integer id) {
        UserStory userStory = Container.getInstance().getUserStory(id);
        System.out.println("\nHints: ");

        analyseText(userStory.getTitle(), "Every user story needs a title", 3, "The title should not be longer than three words");
        analyseText(userStory.getDescription(), "A user story needs a good description", 50, "The description should not exceed 50 words");
        analyseText(userStory.getDetails(), "Fill a user story with details", 30, "Do not give too many details");
        analyseText(userStory.getAkzeptanz(), "Acceptance criteria is crucial for every user story", 30, "Do not make the acceptance criteria too long");
        analyseText(userStory.getEpic(), "Specify an epic for every user story", 3, "The epic should not be longer than three words");
        analyzeActor(userStory.getActor(), "Specify an actor for your user story", "Add the actor %s to the list \n or change the actor (for more info see 'help'");
    }


    private static boolean analyseText(String type, String warning, int maxWords, String hint) {
        if (type.equals("")) {
            System.out.println(warning);
            return false;
        } else if (type.split(" ").length > maxWords) {
            System.out.println(hint);
            return false;
        }
        return true;
    }


    private static int verdict(UserStory userStory) {
        return 1000 - determineQuality(userStory);
    }


    private static int determineQuality(UserStory userStory) {
        return analyzeShortcommings(userStory) + analyseSentenceStructure(userStory);
    }


    private static String getGrade(Integer points) {
        if (points == 100) {
            return "1.0 - Excellent";
        } else if (points >= 75) {
            return "2.0 - Very good";
        } else if (points >= 50) {
            return "3.0 - Good";
        } else if (points >= 25) {
            return "4.0 - Mediocre";
        } else {
            return "5.0 - Abysmal";
        }
    }


    private static int wc(String s) {
        return s.split(" ").length;
    }


    private static int analyseSentence(String sentence, char sign) {
        return (int) sentence.chars().filter(ch -> ch == sign).count();
    }


    private static boolean analyzeActor(String actor, String warning, String warning2) {
        if (actor.equals("")) {
            System.out.println(warning);
            return false;
        } else if (!Container.getInstance().getActors().contains(actor)) {
            System.out.printf((warning2) + "%n", actor);
            return false;
        }
        return true;
    }
}


