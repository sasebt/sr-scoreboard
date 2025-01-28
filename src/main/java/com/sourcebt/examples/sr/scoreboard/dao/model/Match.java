package com.sourcebt.examples.sr.scoreboard.dao.model;

import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;

public class Match {
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    public Match(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) throws InvalidScoreValueException {
        if (homeTeamScore < 0) {
            throw new InvalidScoreValueException();
        }
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) throws InvalidScoreValueException {
        if (awayTeamScore < 0) {
            throw new InvalidScoreValueException();
        }
        this.awayTeamScore = awayTeamScore;
    }

    public int getSumScores() {
        return getHomeTeamScore() + getAwayTeamScore();
    }
}
