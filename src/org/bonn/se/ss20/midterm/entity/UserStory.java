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
    private Integer overvalue;
    private Integer penalty;
    private Integer effort;
    private Integer risk;
    private double priority;
    private boolean completed;

    public UserStory(int id, String title, int overvalue, int penalty, int effort, int risk) {

        setId( id );
        setTitle( title );
        setOvervalue( overvalue );
        setPenalty( penalty );
        setEffort( effort );
        setRisk( risk );
        setCompleted( false );
        setPriority(HelperFunctions.glogerFormular(overvalue, penalty, effort, risk));

    }

    public Integer getOvervalue() {
        return overvalue;
    }

    public void setOvervalue(Integer overvalue) {
        this.overvalue = overvalue;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
        this.risk = risk;
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
                + ", Overvalue: " + this.getOvervalue()
                + ", Penalty: " + this.getPenalty()
                + ", Risk: " + this.getRisk()
                + ", Effort: " + this.getEffort()
                + ", Priority: " + this.getPriority();
    }
}
