package com.pandey.cfcomparator.JSONObject;

import java.util.List;

public class Row {
    Party party;
    int rank;
    int points;
    int penalty;
    int successfulHackCount;
    int unsuccessfulHackCount;
    List<ProblemResult> problemResult = null;

    public Row(Party party, int rank, int points, int penalty, int successfulHackCount, int unsuccessfulHackCount, List<ProblemResult> problemResult) {
        this.party = party;
        this.rank = rank;
        this.points = points;
        this.penalty = penalty;
        this.successfulHackCount = successfulHackCount;
        this.unsuccessfulHackCount = unsuccessfulHackCount;
        this.problemResult = problemResult;
    }

    public Row(){

    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getSuccessfulHackCount() {
        return successfulHackCount;
    }

    public void setSuccessfulHackCount(int successfulHackCount) {
        this.successfulHackCount = successfulHackCount;
    }

    public int getUnsuccessfulHackCount() {
        return unsuccessfulHackCount;
    }

    public void setUnsuccessfulHackCount(int unsuccessfulHackCount) {
        this.unsuccessfulHackCount = unsuccessfulHackCount;
    }

    public List<ProblemResult> getProblemResult() {
        return problemResult;
    }

    public void setProblemResult(List<ProblemResult> problemResult) {
        this.problemResult = problemResult;
    }
}
