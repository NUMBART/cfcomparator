package com.pandey.cfcomparator.StandingsObject;

import java.util.List;

public class Rows {
    Party party;
    int rank;
    int points;
    int penalty;
    int successfulHackCount;
    int unsuccessfulHackCount;
    List<ProblemResults> problemResults = null;

    public Rows(Party party, int rank, int points, int penalty, int successfulHackCount, int unsuccessfulHackCount, List<ProblemResults> problemResults) {
        this.party = party;
        this.rank = rank;
        this.points = points;
        this.penalty = penalty;
        this.successfulHackCount = successfulHackCount;
        this.unsuccessfulHackCount = unsuccessfulHackCount;
        this.problemResults = problemResults;
    }

    public Rows(){

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

    public List<ProblemResults> getProblemResults() {
        return problemResults;
    }

    public void setProblemResults(List<ProblemResults> problemResults) {
        this.problemResults = problemResults;
    }
}
