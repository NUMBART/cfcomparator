package com.pandey.cfcomparator.controller;

import com.pandey.cfcomparator.StandingsObject.Standings;
import com.pandey.cfcomparator.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StandingsController {

    @Autowired
    StandingsService standingsService;

    @RequestMapping("/standings/{contestId}")
    public Standings getPostPlainJSON(@PathVariable int contestId){
        return standingsService.getStandings(contestId);
    }

    //charts are getting diplayed at - http://localhost:8080/

    //need to uncomment the corresponding js file(TimeVsFreq.js) in index.jsp while testing
    @RequestMapping("/problemgraph/timevsfreq")
    @ResponseBody
    public String getProblemTimeVsFreq(@RequestParam("contest") int contestId, @RequestParam("problem") String problemId) {
        return standingsService.getTimevsFreq(contestId, problemId);
    }

    //need to uncomment the corresponding js file(RankVsTime.js) in index.jsp while testing
    @RequestMapping("/problemgraph/rankvstime")
    @ResponseBody
    public String getRankVsTime(@RequestParam("contest") int contestId){
        return standingsService.getRankvsTime(contestId);
    }

    @RequestMapping("/predict-problemrating")
    @ResponseBody
    public String getProblemRating(@RequestParam("contest") int contestId, @RequestParam("problem") String problemId){
        return Integer.toString(standingsService.getProblemRating(contestId, problemId));
    }

    @RequestMapping("/solvesbyprbratings")
    @ResponseBody
    public String getSolvedByRating(@RequestParam("user") String userhandle){
        return standingsService.getSolvedByRating(userhandle);
    }

}
/*
        data:{
            'contest': contestId,
            'problem': problemId
        },
*/
