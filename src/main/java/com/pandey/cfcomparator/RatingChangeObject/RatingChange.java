package com.pandey.cfcomparator.RatingChangeObject;

import java.util.List;

public class RatingChange {
    String status;
    List<Result> result;

    public RatingChange(String status, List<Result> result) {
        this.status = status;
        this.result = result;
    }

    public RatingChange(){

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
