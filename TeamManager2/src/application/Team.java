package application;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Team implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient StringProperty teamName;
    private transient IntegerProperty wins;
    private transient IntegerProperty draws;
    private transient IntegerProperty losses;
    private transient IntegerProperty points;
    private transient IntegerProperty goalsScored;
    private transient IntegerProperty goalsConceded;
    private transient IntegerProperty goalDifference;

    public Team(String teamName) {
        this.teamName = new SimpleStringProperty(teamName);
        this.wins = new SimpleIntegerProperty(0);
        this.draws = new SimpleIntegerProperty(0);
        this.losses = new SimpleIntegerProperty(0);
        this.points = new SimpleIntegerProperty(0);
        this.goalsScored = new SimpleIntegerProperty(0);
        this.goalsConceded = new SimpleIntegerProperty(0);
        this.goalDifference = new SimpleIntegerProperty(0);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(teamName.get());
        out.writeInt(wins.get());
        out.writeInt(draws.get());
        out.writeInt(losses.get());
        out.writeInt(points.get());
        out.writeInt(goalsScored.get());
        out.writeInt(goalsConceded.get());
        out.writeInt(goalDifference.get());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.teamName = new SimpleStringProperty(in.readUTF());
        this.wins = new SimpleIntegerProperty(in.readInt());
        this.draws = new SimpleIntegerProperty(in.readInt());
        this.losses = new SimpleIntegerProperty(in.readInt());
        this.points = new SimpleIntegerProperty(in.readInt());
        this.goalsScored = new SimpleIntegerProperty(in.readInt());
        this.goalsConceded = new SimpleIntegerProperty(in.readInt());
        this.goalDifference = new SimpleIntegerProperty(in.readInt());
    }

    // Getter and Setter methods

    public String getTeamName() {
        return teamName.get();
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public int getWins() {
        return wins.get();
    }

    public void setWins(int wins) {
        this.wins.set(wins);
        calculateGoalDifference();
    }

    public IntegerProperty winsProperty() {
        return wins;
    }

    public int getDraws() {
        return draws.get();
    }

    public void setDraws(int draws) {
        this.draws.set(draws);
        calculateGoalDifference();
    }

    public IntegerProperty drawsProperty() {
        return draws;
    }

    public int getLosses() {
        return losses.get();
    }

    public void setLosses(int losses) {
        this.losses.set(losses);
        calculateGoalDifference();
    }

    public IntegerProperty lossesProperty() {
        return losses;
    }

    public int getPoints() {
        return points.get();
    }

    public void setPoints(int points) {
        this.points.set(points);
    }

    public IntegerProperty pointsProperty() {
        return points;
    }

    public int getGoalsScored() {
        return goalsScored.get();
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored.set(goalsScored);
        calculateGoalDifference();
    }

    public IntegerProperty goalsScoredProperty() {
        return goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded.get();
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded.set(goalsConceded);
        calculateGoalDifference();
    }

    public IntegerProperty goalsConcededProperty() {
        return goalsConceded;
    }

    public int getGoalDifference() {
        return goalDifference.get();
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference.set(goalDifference);
    }

    public IntegerProperty goalDifferenceProperty() {
        return goalDifference;
    }

    private void calculateGoalDifference() {
        int difference = getGoalsScored() - getGoalsConceded();
        setGoalDifference(difference);
    }
}
