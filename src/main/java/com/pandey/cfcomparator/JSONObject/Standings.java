package com.pandey.cfcomparator.JSONObject;

public class Standings {
    String status;
    Result result;

    public Standings(String status, Result result) {
        this.status = status;
        this.result = result;
    }

    public Standings(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
