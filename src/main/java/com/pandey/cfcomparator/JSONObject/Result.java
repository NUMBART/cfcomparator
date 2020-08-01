package com.pandey.cfcomparator.JSONObject;

import java.util.List;

public class Result {
    Contest contest;
    List<Problems> problems = null;
    List<Rows> rows = null;

    public Result(Contest contest, List<Problems> problems, List<Rows> rows) {
        this.contest = contest;
        this.problems = problems;
        this.rows = rows;
    }

    public Result(){

    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<Problems> getProblems() {
        return problems;
    }

    public void setProblems(List<Problems> problems) {
        this.problems = problems;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }
}
