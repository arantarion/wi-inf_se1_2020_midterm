package org.bonn.se.ss20.midterm.command;

public class ClearConsoleCommand implements CommandInterface {
    @Override
    public void execute(String[] params) {
        //System.out.print("\033[H\033[2J");
        System.out.flush();
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
        return "Command\n" +
                "\tclear - Clears the console window";
    }
}
