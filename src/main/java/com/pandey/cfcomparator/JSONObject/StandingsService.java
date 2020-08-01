


package com.pandey.cfcomparator.JSONObject;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StandingsService {
    private final RestTemplate restTemplate;

    public StandingsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Standings getStandings(int contestId) {
        String url = "https://codeforces.com/api/contest.standings?contestId=" + Integer.toString(contestId) + "&from=1&count=10&showUnofficial=false";
        return this.restTemplate.getForObject(url, Standings.class);
    }
}
