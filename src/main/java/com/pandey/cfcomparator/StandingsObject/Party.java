package com.pandey.cfcomparator.StandingsObject;

import java.util.List;

public class Party {
    int contestId;
    List<Members> members;
    String participantType;
    int teamId;
    String teamName;
    boolean ghost;
    int room;
    long startTimeSeconds;

    public Party(int contestId, List<Members> members, String participantType, int teamId, String teamName, boolean ghost, int room, long startTimeSeconds) {
        this.contestId = contestId;
        this.members = members;
        this.participantType = participantType;
        this.teamId = teamId;
        this.teamName = teamName;
        this.ghost = ghost;
        this.room = room;
        this.startTimeSeconds = startTimeSeconds;
    }

    public Party(){

    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(long startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }
}
