package org.bonn.se.ss20.midterm.entity;

import org.bonn.se.ss20.midterm.util.HelperFunctions;

import java.text.DecimalFormat;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class UserStory implements java.io.Serializable, Comparable<UserStory> {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title, description, details, akzeptanz, epic, actor;
    private Integer mehrwert;
    private Integer strafe;
    private Integer aufwand;
    private Integer risiko;
    private double priority;
    private boolean completed;
    private String status;

    public UserStory(int id, String title, int mehrwert, int strafe, int auswand, int risiko) {
        setId(id);
        setTitle(title);
        setMehrwert(mehrwert);
        setStrafe(strafe);
        setAufwand(auswand);
        setRisiko(risiko);
        setCompleted(false);
        setPriority(HelperFunctions.calculatePriority(mehrwert, strafe, auswand, risiko));
        setStatus("todo");
    }

    public UserStory() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAkzeptanz() {
        return akzeptanz;
    }

    public void setAkzeptanz(String akzeptanz) {
        this.akzeptanz = akzeptanz;
    }

    public String getEpic() {
        return epic;
    }

    public void setEpic(String epic) {
        this.epic = epic;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Integer getMehrwert() {
        return mehrwert;
    }

    public void setMehrwert(Integer mehrwert) {
        this.mehrwert = mehrwert;
    }

    public Integer getStrafe() {
        return strafe;
    }

    public void setStrafe(Integer strafe) {
        this.strafe = strafe;
    }

    public Integer getAufwand() {
        return aufwand;
    }

    public void setAufwand(Integer auswand) {
        this.aufwand = auswand;
    }

    public Integer getRisiko() {
        return risiko;
    }

    public void setRisiko(Integer risiko) {
        this.risiko = risiko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return new DecimalFormat("##.##").format(this.priority);
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public double getPriorityDouble() {
        return this.priority;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean status) {
        this.completed = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.setCompleted(status.equals("done"));
    }

    @Override
    public int compareTo(UserStory o) {
        if (getPriorityDouble() == o.getPriorityDouble()) {
            return 0;
        } else if (getPriorityDouble() < o.getPriorityDouble()) {
            return 1;
        }
        return -1;
    }

    public String toString() {
        return "User Story ID: " + this.getId() + "\n" +
                "Title: " + this.getTitle() + "\n" +
                "Priority: " + this.getPriority() + "\n" +
                "Status: " + this.getStatus();
    }

    public void toStringDetail() {
        System.out.println("User Story ID: " + id + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Details: " + details + "\n" +
                "Aceptance Criteria: " + akzeptanz + "\n" +
                "Epic: " + epic + "\n" +
                "Actor: " + actor + "\n" +
                "Priority: " + getPriority() + "\n" +
                "Status: " + getStatus() +
                "\n---------------------------------");
    }

    /*
    private Integer id;
    private String title, description, details, akzeptanz, epic, actor;
    private Integer mehrwert;
    private Integer strafe;
    private Integer aufwand;
    private Integer risiko;
    private double priority;
    private boolean completed;
    private String status;
     */

}
