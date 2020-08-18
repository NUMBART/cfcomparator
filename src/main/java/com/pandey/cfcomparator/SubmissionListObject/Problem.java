package com.pandey.cfcomparator.SubmissionListObject;

import java.util.List;

public class Problem {
    int contestId;
    String problemsetName;
    String index;
    String name;
    String type;
    double points;
    int rating;
    List<Tags> tags;

    public Problem(int contestId, String problemsetName, String index, String name, String type, double points, int rating, List<Tags> tags) {
        this.contestId = contestId;
        this.problemsetName = problemsetName;
        this.index = index;
        this.name = name;
        this.type = type;
        this.points = points;
        this.rating = rating;
        this.tags = tags;
    }

    public Problem(){}

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getProblemsetName() {
        return problemsetName;
    }

    public void setProblemsetName(String problemsetName) {
        this.problemsetName = problemsetName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}
