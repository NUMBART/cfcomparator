package com.pandey.cfcomparator.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandingsController {

    @Autowired
    StandingsService standingsService;

    @RequestMapping("/standings/{contestId}")
    public Standings getPostPlainJSON(@PathVariable int contestId){ return standingsService.getStandings(contestId); }
}
