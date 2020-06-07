package org.bonn.se.ss20.midterm.dto;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.util.HelperFunctions;

/**
 *
 * @author Henry Weckermann, Anton Drees
 *
 */

public class UserStoryDTO implements Comparable<UserStoryDTO> {
    private int id;
    private String title;
    private double priority;


    public UserStoryDTO(UserStory userStory) {
        this.id = userStory.getId();
        this.title = userStory.getTitle();
        this.priority = HelperFunctions.calculatePriority(
                userStory.getMehrwert(),
                userStory.getStrafe(),
                userStory.getAufwand(),
                userStory.getRisiko());
    }

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

    @Override
    public String toString() {
        return "User Story: " + id + "\n" +
                "Title: " + title + "\n" +
                "Priority: " + priority;
    }

    @Override
    public int compareTo(UserStoryDTO o) {
        if (priority == o.priority) {
            return 0;
        } else if (priority < o.priority) {
            return 1;
        }
        return -1;
    }
}
