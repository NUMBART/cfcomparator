package com.pandey.cfcomparator.JSONObject;

public class Members {
    Member[] member;

    public Members(Member[] member) {
        this.member = member;
    }
    public Members(){

    }

    public Member[] getMember() {
        return member;
    }

    public void setMember(Member[] member) {
        this.member = member;
    }
}
