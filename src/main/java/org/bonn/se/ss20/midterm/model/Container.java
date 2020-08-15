package org.bonn.se.ss20.midterm.model;

import org.bonn.se.ss20.midterm.command.CommandInterface;
import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.exception.ContainerException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class Container {

    private static Container singleContainerInstance = null;
    private final Stack<CommandInterface> history = new Stack<>();
    private final List<String> actors = new ArrayList<>();
    private final HashMap<String, CommandInterface> commands = new HashMap<>();
    private List<UserStory> myStories = new ArrayList<>();

    private Container() {
    }

    //Singleton
    public static synchronized Container getInstance() {
        if (singleContainerInstance == null) {
            singleContainerInstance = new Container();
        }
        return singleContainerInstance;
    }


    /*
        UserStory Functions
     */
    public List<UserStory> getUserStories(boolean onlyUndone) {
        return myStories.stream().filter(us -> !onlyUndone || !us.getCompleted()).collect(Collectors.toList());
    }

    public UserStory getUserStory(Integer id) {
        try {
            return myStories.stream()
                    .filter(story -> story.getId() != null)
                    .filter(story -> story.getId().equals(id))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("User Story with ID " + id + " not found"));
        } catch (IllegalArgumentException ex) {
            System.out.println();
            return null;
        }
    }

    public void setMyStories(List<UserStory> stories) {
        myStories = stories;
    }

    public int getHighestId() {
        if (myStories.size() == 0) {
            return 0;
        }
        List<UserStory> stories = getUserStories(false);
        UserStory maxID;

        maxID = stories.stream().max(Comparator.comparing(UserStory::getId)).get();
        return maxID.getId();

    }

    public void addUserStory(UserStory userStory) throws ContainerException {

        for (UserStory u : myStories) {
            if (userStory.getId().equals(u.getId())) {
                throw new ContainerException("User story with ID  " + u.getId() + " already exists. Please choose a unique ID!");
            }
        }
        myStories.add(userStory);

    }

    public boolean containsUserStory(Integer id) {
        try {
            return getUserStory(id) != null;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Could not find this user story");
            return false;
        }
    }

    public void removeUserStory(Integer id) {
        int index = getIndexOf(id);
        try {
            myStories.remove(index);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("User Story with ID " + id + " can't be removed");
        }
    }

    public void removeAll() {
        myStories = new ArrayList<>();
    }

    private int getIndexOf(Integer id) {
        int pos = 0;

        for (UserStory story : myStories) {
            if (id.equals(story.getId())) {
                return pos;
            }
            pos++;
        }
        return -1;
    }


    /*
        Actor Functions
     */
    public String addActor(String actor) {
        actors.add(actor);
        return actor;
    }

    public List<String> getActors() {
        return this.actors;
    }

    public boolean containsActor(String actor) {
        for (String a : actors) {
            if (a.equals(actor)) {
                return true;
            }
        }
        return false;
    }

    public void removeActor(String name) {
        try {
            actors.remove(name);
        } catch (Exception ex) {
            System.out.println("Can't remove actor with the name: " + name);
        }
    }


    /*
        History Functions
     */
    public void addHistory(CommandInterface command) {
        history.add(command);
    }

    public void undoHistory() {
        if (history.isEmpty()) {
            System.out.println("There is nothing to undo");
        } else {
            history.pop().undo();
        }
    }


    /*
        Command Functions
     */

    //adds an command to the list of supported commands
    public void addCommand(String cmdName, CommandInterface command) {
        commands.put(cmdName, command);
    }

    public CommandInterface getCommand(String cmdName) {
        CommandInterface command = commands.get(cmdName);
        if (command == null) {
            System.out.println("The command " + cmdName + " is not supported");
            return new CommandInterface() {
                @Override
                public void execute(String[] params) {
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
                    return null;
                }
            };
        }
        return command;
    }

    //returns all commands
    public List<String> getAllCommands() {
        return commands.keySet().stream().sorted().collect(Collectors.toList());
    }


    /*
        Command Functions
     */
    //prints the complete help with the help texts of all commands
    public void getCompleteHelp() {
        System.out.print("\n");

        Map<String, CommandInterface> result = commands.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        for (CommandInterface command : result.values()) {
            System.out.println(command.usage());
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
    }

    //prints only the help text of a specified command
    public void getHelp(String command) {
        System.out.println(commands.get(command).usage());
    }


    /*
        Extra
     */
    public int size() {
        return myStories.size();
    }

}