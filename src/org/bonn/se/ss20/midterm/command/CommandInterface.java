package org.bonn.se.ss20.midterm.command;

public interface CommandInterface {

    void execute(String[] params);
    void undo();
    //Command clone();

}


