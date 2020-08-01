package com.pandey.cfcomparator.JSONObject;

public class Contest {
    int id;
    String name;
    String type;
    String phase;
    boolean frozen;
    int durationSeconds;
    int startTimeSeconds;
    int relativeTimeSeconds;

    public Contest(int id, String name, String type, String phase, boolean frozen, int durationSeconds, int startTimeSeconds, int relativeTimeSeconds) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phase = phase;
        this.frozen = frozen;
        this.durationSeconds = durationSeconds;
        this.startTimeSeconds = startTimeSeconds;
        this.relativeTimeSeconds = relativeTimeSeconds;
    }

    public Contest(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public int getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(int startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }

    public int getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }

    public void setRelativeTimeSeconds(int relativeTimeSeconds) {
        this.relativeTimeSeconds = relativeTimeSeconds;
    }
}
