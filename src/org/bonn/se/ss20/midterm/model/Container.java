package org.bonn.se.ss20.midterm.model;

import org.bonn.se.ss20.midterm.command.CommandInterface;
import org.bonn.se.ss20.midterm.dto.UserStoryDTO;
import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.exception.ContainerException;
import org.bonn.se.ss20.midterm.util.LookAheadObjectInputStream;
import org.bonn.se.ss20.midterm.view.Console;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class Container {

    private List<UserStory> myStories = new ArrayList<>();
    private final Stack<CommandInterface> history = new Stack<>();
    private final List<String> actors = new ArrayList<>();
    private final HashMap<String, CommandInterface> commands = new HashMap<>();
    private static Container singleContainerInstance = null;
    private final String fileExtension = ".data";

    private Container() {
    }

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
        return myStories.stream()
                .filter(story -> story.getId() != null)
                .filter(story -> story.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("User Story with ID: " + id + " not found"));
    }

    public void addUserStory(UserStory userStory) throws ContainerException {
        if (getUserStory(userStory.getId()) != null) {
            myStories.add(userStory);
        } else {
            throw new ContainerException("User story with ID  " + userStory.getId() + " already exists. Please choose a unique ID!");
        }

        //legacy version
//        for (UserStory u : myStories) {
//            if (userStory.getId().intValue() == u.getId().intValue()) {
//                throw new ContainerException("User story with ID  " + u.getId() + " already exists. Please choose a unique ID!");
//            }
//        }
//        myStories.add(userStory);
    }

    public boolean containsUserStory(Integer id) {
        return getUserStory(id) != null;
    }

    public void removeUserStroy(Integer id) {
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
        // add(E e) does not throw an exception suggesting that it can't fail
        actors.add(actor);
        return actor;
    }

    public List<String> getActors() {
        return this.actors;
    }

    public boolean containsActor(String actor) {
        return actors.stream().noneMatch(tmp -> tmp.equals(actor));
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

    public void undoHistory() throws ContainerException {
        if (history.isEmpty()) {
            throw new ContainerException("There is nothing to undo");
        } else {
            history.pop().undo();
        }
    }


    /*
        Command Functions
     */
    public void addCommand(String cmdName, CommandInterface command) {
        commands.put(cmdName, command);
    }

    public CommandInterface getCommand(String cmdName) {
        CommandInterface command = commands.get(cmdName);
        if (command == null) {
            System.out.println("The command " + cmdName + " is not supported");
            return commands.get("help");
        }
        return command;
    }

    public Set<String> getAllCommands() {
        return commands.keySet();
    }


    /*
        Extra
     */
    private int size() {
        return myStories.size();
    }



    /*
        Legacy Functions
     */

    /*
    public List<UserStoryDTO> getUserStoriesAsListOfDTOs() {
        List<UserStoryDTO> userStoryDTOList = new ArrayList<>();

        List<UserStory> filteredUS = myStories.stream()
                .filter(item -> !item.getCompleted())
                .sorted(Comparator.comparing(UserStory::getId))
                .collect(Collectors.toList());

        for (UserStory us : filteredUS) {
            UserStoryDTO dto = new UserStoryDTO();
            dto.setTitle(us.getTitle());
            dto.setId(us.getId());
            dto.setPriority(us.getPriorityDouble());
            userStoryDTOList.add(dto);
        }
        return userStoryDTOList;

    }

    public String markUserStory(int id) {

        for (UserStory u : myStories) {
            if (u.getId() == id) {
                u.setCompleted(true);
                return "User story with ID: " + id + " marked as completed and will not be shown again.";
            }
        }
        return "Error: could not find user story with that ID!";

    }


    // Writes the UserStoryContainer to a file with all its contents
    // can take just a filename (stored in dir where program is started) or a path + filename
    public void store() throws Exception {

        ObjectOutputStream storeFile = null;
        Console userConsole = new Console();
        String fileName = userConsole.inputText("Please specify a filename or path (no quotes): ") + fileExtension;

        try {

            storeFile = new ObjectOutputStream(new FileOutputStream(fileName));

            try {
                storeFile.writeObject(myStories);
                System.out.println(size() + " user stories saved successfully to " + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (storeFile != null) {
                try {
                    storeFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // Loads a UserStoryContainer from a file with all its contents
    // can take just a filename (stored in dir where program is started) or a path + filename
    public void load() throws Exception {

        LookAheadObjectInputStream loadFile = null;
        Console userConsole = new Console();
        String fileName = userConsole.inputText("Please specify a filename or path (no quotes): ") + fileExtension;

        try {
            Set whitelist = new HashSet<>(Arrays.asList("java.util.ArrayList", "org.bonn.se.ws19.uebung8.entity.UserStory",
                    "java.lang.Integer", "java.lang.Number", "java.util.LinkedList"));
            loadFile = new LookAheadObjectInputStream(new FileInputStream(fileName), whitelist);

            Object tmp = loadFile.readObject();

            if (tmp instanceof List<?>) {
                this.myStories = (List<UserStory>) tmp;
            }
            System.out.println(fileName + " loaded successfully! It contains " + this.myStories.size() + " user stories");

        } catch (IOException e) {

            System.out.println(e.getMessage());

        } finally {
            if (loadFile != null) {
                try {
                    loadFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
   */

}