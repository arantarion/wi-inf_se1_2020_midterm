package org.bonn.se.ss20.midterm.dto;

/**
 *
 * @author Henry Weckermann, Anton Drees
 *
 */

public class UserStoryDTO {
    private String title;
    private double priority;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return this.getId() + ": " + this.getTitle()
                + ", Priority: " + this.getPriority();
    }
}
