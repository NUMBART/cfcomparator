package com.pandey.cfcomparator.SubmissionListObject;

import java.util.List;

public class SubmissionList {
    String status;
    List<Result> result;

    public SubmissionList(String status, List<Result> result) {
        this.status = status;
        this.result = result;
    }

    public SubmissionList(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
