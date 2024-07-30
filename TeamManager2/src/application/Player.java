package application;

import javafx.beans.property.*;

public class Player {
    private final StringProperty name;
    private final IntegerProperty goals;
    private final IntegerProperty assists;

    public Player(String name, int goals, int assists) {
        this.name = new SimpleStringProperty(name);
        this.goals = new SimpleIntegerProperty(goals);
        this.assists = new SimpleIntegerProperty(assists);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty goalsProperty() {
        return goals;
    }

    public IntegerProperty assistsProperty() {
        return assists;
    }

    public String getName() {
        return name.get();
    }

    public int getGoals() {
        return goals.get();
    }

    public void setGoals(int goals) {
        this.goals.set(goals);
    }

    public int getAssists() {
        return assists.get();
    }

    public void setAssists(int assists) {
        this.assists.set(assists);
    }
}
