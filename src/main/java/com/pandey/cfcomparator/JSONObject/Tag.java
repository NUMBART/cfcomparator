package com.pandey.cfcomparator.JSONObject;

import java.util.List;

public class Tag {
    String tagname;

    public Tag(String tagname) {
        this.tagname = tagname;
    }

    public Tag(){

    }

    public String getTag() {
        return tagname;
    }

    public void setTag(String tagname) {
        this.tagname = tagname;
    }
}
