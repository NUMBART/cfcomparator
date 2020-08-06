package com.pandey.cfcomparator.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pandey.cfcomparator.JSONObject.Rows;
import com.pandey.cfcomparator.JSONObject.Standings;
import com.pandey.cfcomparator.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StandingsController {

    @Autowired
    StandingsService standingsService;

    @RequestMapping("/standings/{contestId}")
    public Standings getPostPlainJSON(@PathVariable int contestId){
        return standingsService.getStandings(contestId);
    }

    @RequestMapping("/problemgraph?problem={problemId}&contest={contestId}")
    @ResponseBody
    public String getProblemTimeVsFreq(@PathVariable int contestId, @PathVariable char problemId) throws IOException {
        Standings standings = standingsService.getStandings(contestId);
        int contestTime = standings.getResult().getContest().getDurationSeconds() + 1;
        ArrayList<Integer> freq = new ArrayList<Integer>(contestTime);
        for(int i = 0; i < contestTime; ++i) freq.add(0);
        List<Rows> rows = standings.getResult().getRows();
        int pId = Character.getNumericValue(problemId) - Character.getNumericValue('A');
        for (int i = 0; i < rows.size(); ++i) {
            int time = rows.get(i).getProblemResults().get(pId).getBestSubmissionTimeSeconds();
            freq.set(time, freq.get(time) + 1);
        }
        JsonArray jsonTime = new JsonArray();
        JsonArray jsonFreq = new JsonArray();
        JsonObject json = new JsonObject();
        for(int i = 0; i < freq.size(); ++i) {
            jsonTime.add(i);
            jsonFreq.add(freq.get(i));
        }
        json.add("bestSubmissionTime" +
                "Seconds", jsonTime);
        json.add("countOfUsersWithSameTime", jsonFreq);
        return json.toString();
    }
}
