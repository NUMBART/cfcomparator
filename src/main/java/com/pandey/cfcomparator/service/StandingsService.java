package com.pandey.cfcomparator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pandey.cfcomparator.RatingChangeObject.RatingChange;
import com.pandey.cfcomparator.RatingChangeObject.Result;
import com.pandey.cfcomparator.StandingsObject.Rows;
import com.pandey.cfcomparator.StandingsObject.Standings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandingsService {
    private final RestTemplate restTemplate;
    private Standings standings;
    private RatingChange ratingChange;

    public StandingsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.standings = null;
        this.ratingChange = null;
    }

    private String getCleanMeans(ArrayList<Integer> a, int d){
        JsonArray x = new JsonArray();
        JsonArray y = new JsonArray();
        JsonObject json = new JsonObject();

        for(int i = 0; i < a.size();){
            double mean = 0.0; int dn = 0;
            for(int j = i; j-i < d && j < a.size(); ++j){
                mean += a.get(j); dn++;
            }
            mean /= dn;

            double sd = 0.0; int n = 0;
            for(int j = i; j-i < d && j < a.size(); ++j){
                sd += (a.get(j) - mean)*(a.get(j) - mean);
                n++;
            }
            sd /= n; sd = Math.sqrt(sd);

            double avg = 0.0; n = 0;
            for(int j = i; j-i < d && j < a.size(); ++j){
                if(a.get(j) <= mean+sd){
                    avg += a.get(j); n++;
                }
            }
            avg /= n;

            x.add(i+(dn+1)/2);
            y.add(avg);
            i = i+d;
        }
        json.add("x", x);
        json.add("y", y);
        return json.toString();
    }

    public Standings getStandings(int contestId){
        if(standings != null)
            return standings;
        else {
            String url = "https://codeforces.com/api/contest.standings?contestId=" + contestId + "&from=1&count=50000&showUnofficial=false";
            String temp = this.restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            try { standings = objectMapper.readValue(temp, Standings.class);}
            catch (JsonProcessingException e) { e.printStackTrace();}
            return standings;
        }
    }

    public RatingChange getRatingChange(int contestId) {
        if(ratingChange != null)
            return ratingChange;
        else {
            String url = "https://codeforces.com/api/contest.ratingChanges?contestId=" + contestId;
            String temp = this.restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            try { ratingChange = objectMapper.readValue(temp, RatingChange.class);}
            catch (JsonProcessingException e) { e.printStackTrace();}
            return ratingChange;
        }
    }

    public String getTimevsFreq(int contestId, char problemId){
        //initializing freq
        if(standings == null) this.getStandings(contestId);
        int contestTime = standings.getResult().getContest().getDurationSeconds() + 1;
        ArrayList<Integer> freq = new ArrayList<>(contestTime);
        for(int i = 0; i < contestTime; ++i) freq.add(0);

        //accessing submission time by rank from JavaObject
        List<Rows> rows = standings.getResult().getRows();
        int pId = Character.getNumericValue(problemId) - Character.getNumericValue('A');
        for (Rows row : rows) {
            int time = row.getProblemResults().get(pId).getBestSubmissionTimeSeconds();
            if(time != 0) freq.set(time, freq.get(time) + 1);
        }
        return getCleanMeans(freq, 1);
    }

    public String getRankvsTime(int contestId, char problemId) {
        if(standings == null) this.getStandings(contestId);

        List<Rows> rows = standings.getResult().getRows();
        int pId = Character.getNumericValue(problemId) - Character.getNumericValue('A');

        ArrayList<Integer> subtime = new ArrayList<>(rows.size());
        for (Rows row : rows) {
            int time = row.getProblemResults().get(pId).getBestSubmissionTimeSeconds();
            subtime.add(time);
        }
        return getCleanMeans(subtime, 50);
    }

    private double getRank(int rating, List<Result> results) {
        double rank = 1.0;
        int j = 0;
        for (Result result : results) {
            int opponentRating = result.getOldRating(); //trash value
            double exponent = ((double) (rating - opponentRating))/ 400.0;
            double lossProbability = 1.0 / (1.0 + Math.pow(10.0, exponent));
            rank += lossProbability;
        }
        return rank;
    }
    public int getProblemRating(int contestId, char problemId) {
        if(standings == null) this.getStandings(contestId);
        if(ratingChange == null) this.getRatingChange(contestId);
        if(ratingChange.getStatus().equals("OK") && ratingChange.getResult().size() > 0){
            int pId = Character.getNumericValue(problemId) - Character.getNumericValue('A');
            List<Rows> rows = standings.getResult().getRows();
            List<Result> results = ratingChange.getResult();
            int problemRank = 1;
            for (Rows row : rows) {
                int points = row.getProblemResults().get(pId).getPoints();
                if (points != 0) problemRank++;
            }
            int b = 800, e = 3500;
            int predictedRating = 3500;
            while(b <= e){
                int m = (b+e)/2;
                if(getRank(m, results) > problemRank)
                    b = m+1;
                else{
                    e = m-1;
                    predictedRating = m;
                }
            }
            return predictedRating;//to be removed
        }
        else if(ratingChange.getStatus().equals("OK")){
            //later
            return 0;//to be removed
        }
        else{
            return 0;
        }

    }
}
