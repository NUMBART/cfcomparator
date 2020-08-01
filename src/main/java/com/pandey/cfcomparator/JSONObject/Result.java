package com.pandey.cfcomparator.JSONObject;

import java.util.List;

public class Result {
    Contest contest;
    List<Problem> problem = null;
    List<Row> row = null;

    public Result(Contest contest, List<Problem> problem, List<Row> row) {
        this.contest = contest;
        this.problem = problem;
        this.row = row;
    }

    public Result(){

    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<Problem> getProblem() {
        return problem;
    }

    public void setProblem(List<Problem> problem) {
        this.problem = problem;
    }

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }
}
