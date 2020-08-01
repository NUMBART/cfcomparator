package com.pandey.cfcomparator.JSONObject;

public class ProblemResults {
    ProblemResult[] problemResult;

    public ProblemResults(ProblemResult[] problemResult) {
        this.problemResult = problemResult;
    }

    public ProblemResults(){

    }

    public ProblemResult[] getProblemResult() {
        return problemResult;
    }

    public void setProblemResult(ProblemResult[] problemResult) {
        this.problemResult = problemResult;
    }
}
