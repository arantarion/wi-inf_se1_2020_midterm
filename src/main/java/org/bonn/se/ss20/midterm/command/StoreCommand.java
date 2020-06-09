package org.bonn.se.ss20.midterm.command;

import org.bonn.se.ss20.midterm.model.Container;
import org.bonn.se.ss20.midterm.util.HelperFunctions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StoreCommand implements CommandInterface {

    @Override
    public void execute(String[] params) {

        ObjectOutputStream storeFile;
        String fileExtension = ".data";
        String fileName = HelperFunctions.readText("Please specify a filename or path (no quotes): ") + fileExtension;

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

    @Override
    public void undo() {

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
