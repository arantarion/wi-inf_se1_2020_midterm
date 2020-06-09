package org.bonn.se.ss20.midterm.command;

public interface CommandInterface {

    void execute(String[] params);

    void undo();

    CommandInterface clone();

    String usage();

}


