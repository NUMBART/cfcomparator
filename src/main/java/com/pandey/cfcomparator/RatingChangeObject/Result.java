package com.pandey.cfcomparator.RatingChangeObject;

public class Result {
    int contestId;
    String contestName;
    String handle;
    int rank;
    long ratingUpdateTimeSeconds;
    int oldRating;
    int newRating;

    public Result(int contestId, String contestName, String handle, int rank, int ratingUpdateTimeSeconds, int oldRating, int newRating) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.handle = handle;
        this.rank = rank;
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
        this.oldRating = oldRating;
        this.newRating = newRating;
    }

    public Result(){

    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getRatingUpdateTimeSeconds() {
        return ratingUpdateTimeSeconds;
    }

    public void setRatingUpdateTimeSeconds(long ratingUpdateTimeSeconds) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public int getOldRating() {
        return oldRating;
    }

    public void setOldRating(int oldRating) {
        this.oldRating = oldRating;
    }

    public int getNewRating() {
        return newRating;
    }

    public void setNewRating(int newRating) {
        this.newRating = newRating;
    }
}
