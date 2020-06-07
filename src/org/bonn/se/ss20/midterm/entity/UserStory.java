package org.bonn.se.ss20.midterm.entity;

import org.bonn.se.ss20.midterm.util.HelperFunctions;
import java.text.DecimalFormat;

/**
 *
 * @author Henry Weckermann, Anton Drees
 *
 */

public class UserStory implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private Integer mehrwert;
    private Integer strafe;
    private Integer aufwand;
    private Integer risiko;
    private double priority;
    private boolean completed;

    public UserStory(int id, String title, int mehrwert, int strafe, int auswand, int risiko) {

        setId( id );
        setTitle( title );
        setMehrwert( mehrwert );
        setStrafe( strafe );
        setAufwand( auswand );
        setRisiko( risiko );
        setCompleted( false );
        setPriority(HelperFunctions.glogerFormular(mehrwert, strafe, auswand, risiko));

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

    public double getPriorityDouble() { return this.priority; }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean status) { this.completed = status; }

    public boolean getCompleted() { return this.completed; }

    public String toString() {
        return this.getId() + ": " + this.getTitle()
                + ", Mehrwert: " + this.getMehrwert()
                + ", Strafe: " + this.getStrafe()
                + ", Risiko: " + this.getRisiko()
                + ", Aufwand: " + this.getAufwand()
                + ", Priority: " + this.getPriority();
    }
}
