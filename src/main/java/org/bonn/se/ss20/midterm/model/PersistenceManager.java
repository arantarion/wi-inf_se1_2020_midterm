package org.bonn.se.ss20.midterm.model;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.util.HelperFunctions;
import org.bonn.se.ss20.midterm.util.LookAheadObjectInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersistenceManager {

    private static PersistenceManager singlePersistenceManagerInstance = null;

    private PersistenceManager() {
    }

    public static synchronized PersistenceManager getInstance() {
        if (singlePersistenceManagerInstance == null) {
            singlePersistenceManagerInstance = new PersistenceManager();
        }
        return singlePersistenceManagerInstance;
    }

    public void store() {
        ObjectOutputStream storeFile;
        String fileExtension = ".data";
        String fileName = HelperFunctions.readText("Please specify a filename or path (no quotes). You do not need an file-extension: ") + fileExtension;

        try {

            storeFile = new ObjectOutputStream(new FileOutputStream(fileName));

            try {
                storeFile.writeObject(Container.getInstance().getUserStories(false));
                System.out.println(Container.getInstance().size() + " user stories saved successfully to " + fileName);

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    storeFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
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

}
