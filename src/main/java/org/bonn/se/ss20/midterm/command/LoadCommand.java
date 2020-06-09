package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;
import org.bonn.se.ss20.midterm.util.LookAheadObjectInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoadCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {
        LookAheadObjectInputStream loadFile = null;
        //Console userConsole = new Console();
        final String fileExtension = ".data";
        String fileName = HelperFunctions.readText("Please specify a filename or path (no quotes): ") + fileExtension;

        try {
            Set<String> whitelist = new HashSet<>(Arrays.asList("java.util.ArrayList", "org.bonn.se.ss20.midterm.entity.UserStory",
                    "java.lang.Integer", "java.lang.Number", "java.util.LinkedList"));
            loadFile = new LookAheadObjectInputStream(new FileInputStream(fileName), whitelist);

            Object tmp = loadFile.readObject();

            if (tmp instanceof List<?>) {
                Container.getInstance().setMyStories((List<UserStory>) tmp);
            }
            System.out.println(fileName + " loaded successfully! It contains " + Container.getInstance().size() + " user stories");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

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

    @Override
    public void undo() {
        Container.getInstance().removeAll();
    }

    @Override
    public CommandInterface clone() {
        return null;
    }

    @Override
    public String usage() {
        return null;//TODO
    }
}
