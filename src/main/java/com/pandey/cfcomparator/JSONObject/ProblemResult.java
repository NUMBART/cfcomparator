package com.pandey.cfcomparator.JSONObject;

public class ProblemResult {
    int points;
    int rejectedAttemptCount;
    String type;
    int bestSubmissionTimeSeconds;

    public ProblemResult(int points, int rejectedAttemptCount, String type, int bestSubmissionTimeSeconds) {
        this.points = points;
        this.rejectedAttemptCount = rejectedAttemptCount;
        this.type = type;
        this.bestSubmissionTimeSeconds = bestSubmissionTimeSeconds;
    }

    public ProblemResult(){

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRejectedAttemptCount() {
        return rejectedAttemptCount;
    }

    public void setRejectedAttemptCount(int rejectedAttemptCount) {
        this.rejectedAttemptCount = rejectedAttemptCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBestSubmissionTimeSeconds() {
        return bestSubmissionTimeSeconds;
    }

    public void setBestSubmissionTimeSeconds(int bestSubmissionTimeSeconds) {
        this.bestSubmissionTimeSeconds = bestSubmissionTimeSeconds;
    }
}
