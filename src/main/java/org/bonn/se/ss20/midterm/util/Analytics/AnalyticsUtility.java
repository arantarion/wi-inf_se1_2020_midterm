package org.bonn.se.ss20.midterm.util.Analytics;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.model.Container;

import java.util.List;

public class AnalyticsUtility {

    private static int verdict(UserStory userStory) {
        return 1000 - determineQuality(userStory);
    }

    private static int determineQuality(UserStory userStory) {
        return analyzeShortcommings(userStory) + analyseSentenceStructure(userStory);
    }

    private static int analyzeShortcommings(UserStory userStory) {
        int count = 0;
        //alles was wir so haben wollen. titel, beschreibung, details etc
        return count;
    }

    private static int analyseSentenceStructure(UserStory userStory) {
        int count = 0;
        //nebensaetze etc.
        return count;
    }

    private static int wc(String s) {
        return s.split(" ").length;
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

        if (analyseText(userStory.getTitle(), "missing title", 3, "Hint text")
                && analyseText(userStory.getDescription(), "", 30, "") /*TODO more*/) {
            System.out.println("Everything seems fine. Great work");
        }

    }

    private static void printHints(Integer id) {
        UserStory userStory = Container.getInstance().getUserStory(id);
        System.out.println("\nDetails: ");

        analyseText(userStory.getTitle(), "warning", 3, "hint");
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
}


