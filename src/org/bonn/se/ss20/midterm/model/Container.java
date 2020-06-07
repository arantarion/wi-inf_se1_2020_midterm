package org.bonn.se.ss20.midterm.model;

import org.bonn.se.ss20.midterm.command.CommandInterface;
import org.bonn.se.ss20.midterm.dto.UserStoryDTO;
import org.bonn.se.ss20.midterm.util.LookAheadObjectInputStream;
import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.exception.ContainerException;
import org.bonn.se.ss20.midterm.view.Console;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Henry Weckermann, Anton Drees
 *
 */

public class Container {

    private List<UserStory> myStories = new ArrayList<>();
    private final Stack<CommandInterface> history = new Stack<>();
    private final List<String> actors = new ArrayList<>();
    private final HashMap<String, CommandInterface> commands = new HashMap<>();
    private static Container singleContainerInstance = null;
    private final String fileExtension = ".data";

    private Container() {}

    public static synchronized Container getInstance() {
        if(singleContainerInstance == null) {
            singleContainerInstance = new Container();
        }
        return singleContainerInstance;
    }


    // Adds a user story and throws Exception when an ID is reused
    public void addUserStory(UserStory userStory) throws ContainerException {

        for(UserStory u: myStories) {
            if( userStory.getId().intValue() == u.getId().intValue() ) {
                throw new ContainerException( "User story with ID  " + u.getId() + " already exists. Please choose a unique ID!");
            }
        }
        myStories.add(userStory);

    }

    public List<UserStoryDTO> getUserStoriesAsListOfDTOs() {
        List<UserStoryDTO> userStoryDTOList = new ArrayList<>();

        List<UserStory> filteredUS = myStories.stream()
                .filter(item -> !item.getCompleted())
                .sorted(Comparator.comparing(UserStory::getId))
                .collect(Collectors.toList());

        for ( UserStory us : filteredUS ) {
            UserStoryDTO dto = new UserStoryDTO();
            dto.setTitle( us.getTitle()  );
            dto.setId( us.getId() );
            dto.setPriority( us.getPriorityDouble() );
            userStoryDTOList.add(dto);
        }
        return userStoryDTOList;

    }

    public String markUserStory (int id) {

        for(UserStory u: myStories) {
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

            storeFile = new ObjectOutputStream( new FileOutputStream( fileName ) );

            try {
                storeFile.writeObject( myStories );
                System.out.println( size() + " user stories saved successfully to " + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( storeFile != null) {
                try { 
                    storeFile.close();
                } catch (IOException e) {
                    e.printStackTrace();}
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
            Set whitelist = new HashSet<> ( Arrays.asList("java.util.ArrayList", "org.bonn.se.ws19.uebung8.entity.UserStory",
                    "java.lang.Integer", "java.lang.Number", "java.util.LinkedList") );
            loadFile = new LookAheadObjectInputStream( new FileInputStream( fileName ), whitelist );

            Object tmp = loadFile.readObject();

            if (tmp instanceof List<?>) {
                this.myStories = (List<UserStory>) tmp;
            }
            System.out.println(fileName + " loaded successfully! It contains " + this.myStories.size() + " user stories");

        } catch (IOException e) {

            System.out.println( e.getMessage() );

        } finally {
            if ( loadFile != null) {
                try { 
                    loadFile.close();
                } catch (IOException e) {
                    e.printStackTrace();}
            }
        }
    }

    private int size() {
        return myStories.size();
    }

}