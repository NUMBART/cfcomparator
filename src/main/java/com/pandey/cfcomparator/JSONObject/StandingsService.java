package com.pandey.cfcomparator.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class StandingsService {
    private final RestTemplate restTemplate;

    public StandingsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Standings getStandings(int contestId) {
        Standings standings = null;
        try {
            String url = "https://codeforces.com/api/contest.standings?contestId=" + contestId + "&from=1&count=10&showUnofficial=false";
            String temp = this.restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            standings = objectMapper.readValue(temp, Standings.class);
            return standings;
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        return standings;
    }
}
