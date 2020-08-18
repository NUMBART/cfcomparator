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

    @RequestMapping("/problemgraph/timevsfreq")
    @ResponseBody
    public String getProblemTimeVsFreq(@RequestParam("contest") int contestId, @RequestParam("problem") String problemId) {
        return standingsService.getTimevsFreq(contestId, problemId);
    }

    @RequestMapping("/problemgraph/rankvstime")
    @ResponseBody
    public String getRankVsTime(@RequestParam("contest") int contestId, @RequestParam("problem") String problemId){
        return standingsService.getRankvsTime(contestId, problemId);
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
