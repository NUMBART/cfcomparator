package com.pandey.cfcomparator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pandey.cfcomparator.RatingChangeObject.RatingChange;
import com.pandey.cfcomparator.RatingChangeObject.Result;
import com.pandey.cfcomparator.StandingsObject.Problems;
import com.pandey.cfcomparator.StandingsObject.Rows;
import com.pandey.cfcomparator.StandingsObject.Standings;
import com.pandey.cfcomparator.SubmissionListObject.SubmissionList;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class StandingsService {
    private final RestTemplate restTemplate;
    private Standings standings;
    private RatingChange ratingChange;
    private SubmissionList userSubmissions;
    private class CleanData{
        public ArrayList<Integer> x;
        public ArrayList<Double> y;
        public CleanData(){
            this.x = new ArrayList<>();
            this.y = new ArrayList<>();
        }
    }

    public StandingsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.standings = null;
        this.ratingChange = null;
        this.userSubmissions = null;
    }

    //takes mean of standard deviation of data(roughly 70%) for every d
    //data points in array a maps that to middle value and stores in json
    private CleanData getCleanMeans(ArrayList<Integer> a, int d){
        CleanData cleanData = new CleanData();

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

            cleanData.x.add(i+(dn+1)/2);
            cleanData.y.add(avg);
            i = i+d;
        }
        return cleanData;
    }

    //gets codeforces leaderboard of corresponding contestId
    public Standings getStandings(int contestId){
        if(standings != null);
        else{
            String url = "https://codeforces.com/api/contest.standings?contestId=" + contestId + "&from=1&count=50000&showUnofficial=false";
            String temp = this.restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            try { standings = objectMapper.readValue(temp, Standings.class);}
            catch (JsonProcessingException e) { e.printStackTrace();}
        }
        return standings;
    }
    //gets contest ratings of contestants both before and after contest
    public RatingChange getRatingChange(int contestId) {
        if(ratingChange == null){
            String url = "https://codeforces.com/api/contest.ratingChanges?contestId=" + contestId;
            String temp = this.restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            try { ratingChange = objectMapper.readValue(temp, RatingChange.class);}
            catch (JsonProcessingException e) { e.printStackTrace();}
        }
        return ratingChange;
    }

    //returns integer index for a problem given problemId
    private int getProblemIndex(String problemId, List<Problems> problemList){
        for(int i = 0; i < problemList.size(); ++i){
            if(problemId.equals(problemList.get(i).getIndex()))
                return i;
        }
        return problemList.size();
    }

    //Calculates number of contestants to submit a correct answer
    //at every point of time for a problem in a contest
    public String getTimevsFreq(int contestId, String problemId){
        //initializing freq
        if(standings == null) this.getStandings(contestId);
        int contestTime = standings.getResult().getContest().getDurationSeconds() + 1;
        ArrayList<Integer> freq = new ArrayList<>(contestTime);
        for(int i = 0; i < contestTime; ++i) freq.add(0);

        //accessing submission time by rank from JavaObject
        List<Rows> rows = standings.getResult().getRows();
        int pId = getProblemIndex(problemId, standings.getResult().getProblems());
        for (Rows row : rows) {
            int time = row.getProblemResults().get(pId).getBestSubmissionTimeSeconds();
            if(time != 0) freq.set(time, freq.get(time) + 1);
        }
        JsonArray x = new JsonArray();
        JsonArray y = new JsonArray();
        JsonObject json = new JsonObject();
        CleanData cleanData = getCleanMeans(freq, 1);
        for(int i = 0; i < cleanData.x.size(); ++i){
            x.add(cleanData.x.get(i));
            y.add(cleanData.y.get(i));
        }
        json.add("x", x);
        json.add("y", y);
        return json.toString();
    }

    //gets average time a contestant at a given rank submits a problem in
    public String getRankvsTime(int contestId) {
        if(standings == null) this.getStandings(contestId);

        List<Rows> rows = standings.getResult().getRows();

        ArrayList<ArrayList<Integer>> submissionTime = new ArrayList<>();
        JsonArray problemNames = new JsonArray();
        for(int i = 0; i < rows.get(0).getProblemResults().size(); ++i) {
            ArrayList<Integer> temp = new ArrayList<>();
            String name = standings.getResult().getProblems().get(i).getIndex() + " : " +
                    standings.getResult().getProblems().get(i).getName();
            problemNames.add(name);
            for (Rows row : rows) {
                int time = row.getProblemResults().get(i).getBestSubmissionTimeSeconds();
                temp.add(time);
            }
            submissionTime.add(temp);
        }
        JsonObject json = new JsonObject();
        JsonArray rank = new JsonArray(); boolean initialized = false;
        for(int i = 0; i < rows.get(0).getProblemResults().size(); ++i) {
            CleanData cleanData = getCleanMeans(submissionTime.get(i), 50);
            if(!initialized){
                for(int j = 0; j < cleanData.x.size(); ++j)
                    rank.add(cleanData.x.get(j));
                json.add("rank", rank);
                initialized = true;
            }
            JsonArray y = new JsonArray();
            for(int j = 0; j < cleanData.y.size(); ++j)
                y.add(cleanData.y.get(j));
            String name = "p" + (i+1);
            json.add(name, y);
        }
        json.add("names", problemNames);
        return json.toString();
        
    }

    //calculates most probable rank for a participant with given rating
    //in a contest against a list of competing contestants with known ratings
    private double getRank(int rating, List<Result> results) {
        double rank = 1.0;
        for (Result result : results) {
            int opponentRating = result.getOldRating(); //trash value
            double exponent = ((double) (rating - opponentRating))/ 400.0;
            double lossProbability = 1.0 / (1.0 + Math.pow(10.0, exponent));
            rank += lossProbability;
        }
        return rank;
    }

    //calculates rating for a particular problem in a contest. Can
    //find rating for problems in a recent contest whose ratings
    // have not been updated on site too
    public int getProblemRating(int contestId, String problemId) {
        if(standings == null) this.getStandings(contestId);
        getRatingChange(contestId);
        if(ratingChange.getStatus().equals("OK") && ratingChange.getResult().size() > 0){
            int pId = getProblemIndex(problemId, standings.getResult().getProblems());
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
            System.out.println(predictedRating);
            int temp = predictedRating != 800 ? predictedRating+100 : 800;
            int rounded = (temp/100)*100;
            return (temp >= (rounded + 50)) ? rounded + 100 : rounded;
        }
        else if(ratingChange.getStatus().equals("OK")){
            //later
            return 0;//to be removed
        }
        else{
            return 0;
        }

    }

    //fetches all submissions of a user on codeforces
    private SubmissionList getUserSubmissions(String user){
        if(userSubmissions == null){
            String url = "https://codeforces.com/api/user.status?handle=" + user + "&from=1&count=10000";
            String temp = this.restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            try { userSubmissions = objectMapper.readValue(temp, SubmissionList.class);}
            catch (JsonProcessingException e) { e.printStackTrace();}
        }
        return userSubmissions;
    }

    //gets number of problems solved in practice, in virtual round,
    //in contest(in competition), in contest(out of competition)
    public String getSolvedByRating(String user) {
        SubmissionList submissions = getUserSubmissions(user);
        List<com.pandey.cfcomparator.SubmissionListObject.Result> results = submissions.getResult();

        ArrayList<Integer> practiceFreq = new ArrayList<>();
        ArrayList<Integer> contestFreq = new ArrayList<>();
        ArrayList<Integer> outofcompFreq = new ArrayList<>();
        ArrayList<Integer> virtualFreq = new ArrayList<>();
        ArrayList<String> participationType = new ArrayList<>(Arrays.asList(
                "CONTESTANT", "PRACTICE", "VIRTUAL", "OUT_OF_COMPETITION"
        ));
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>(Arrays.asList(
                contestFreq, practiceFreq, virtualFreq, outofcompFreq
        ));
        for(int i = 0; i < 36; i++) {
            for(int j = 0; j < 4; ++j)
                arrays.get(j).add(0);
        }

        HashSet<String> problemSet = new HashSet<>();
        for (com.pandey.cfcomparator.SubmissionListObject.Result result : results) {
            String problemName = result.getProblem().getName();
            boolean onlyauthor = result.getAuthor().getMembers().size() == 1;
            String verdict = result.getVerdict();
            if (onlyauthor && (verdict != null && verdict.equals("OK")) && !problemSet.contains(problemName)) {
                problemSet.add(problemName);
                int index = result.getProblem().getRating() / 100;
                String participantType = result.getAuthor().getParticipantType();
                ArrayList<Integer> freq;
                for (int j = 0; j < 4; ++j)
                    if (participationType.get(j).equals(participantType)){
                        freq = arrays.get(j);
                        freq.set(index, freq.get(index) + 1);
                    }
            }
        }

        JsonArray rating = new JsonArray();
        JsonArray practice = new JsonArray();
        JsonArray virtual = new JsonArray();
        JsonArray contest = new JsonArray();
        JsonArray outofcomp = new JsonArray();
        JsonObject json = new JsonObject();

        int cnt = 0;
        for(int i = 8; i < practiceFreq.size(); ++i){
            rating.add(i*100);
            practice.add(practiceFreq.get(i));
            virtual.add(virtualFreq.get(i));
            contest.add(contestFreq.get(i));
            outofcomp.add(outofcompFreq.get(i));
        }
        System.out.println(cnt);
        json.add("rating", rating);
        json.add("practice", practice);
        json.add("virtual", virtual);
        json.add("contest", contest);
        json.add("outofcomp", outofcomp);
        return json.toString();
    }
}
