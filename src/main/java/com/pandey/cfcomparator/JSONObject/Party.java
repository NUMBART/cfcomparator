package com.pandey.cfcomparator.JSONObject;

public class Party {
    int contestId;
    Members members;
    String participantType;
    boolean ghost;
    long startTimeSeconds;

    public Party(int contestId, Members members, String participantType, boolean ghost, long startTimeSeconds) {
        this.contestId = contestId;
        this.members = members;
        this.participantType = participantType;
        this.ghost = ghost;
        this.startTimeSeconds = startTimeSeconds;
    }

    public Party(){

    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    public boolean isGhost() {
        return ghost;
    }

    public void setGhost(boolean ghost) {
        this.ghost = ghost;
    }

    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(long startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }
}
