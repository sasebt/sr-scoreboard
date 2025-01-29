package com.sourcebt.examples.sr.scoreboard.dao.model;

import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;

public class Match  implements Comparable<Match> {
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    /**
     * Creates a new Match instance with score 0:0
     * @param homeTeamName
     * @param awayTeamName
     */
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

    public void setHomeTeamScore(int score) throws InvalidScoreValueException {
        if (score < 0) {
            throw new InvalidScoreValueException("setting home score to " + score + " for match " + getHomeTeamName() + " " + getAwayTeamName());
        }
        this.homeTeamScore = score;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int score) throws InvalidScoreValueException {
        if (score < 0) {
            throw new InvalidScoreValueException("setting home score to " + score + " for match " + getHomeTeamName() + " " + getAwayTeamName());
        }
        this.awayTeamScore = score;
    }

    /**
     * Calculates the sum of the scores in the match (used for sorting)
     *
     * @return the sum of the scores of the home and the away team
     */
    public int getSumScores() {
        return getHomeTeamScore() + getAwayTeamScore();
    }

    @Override
    public int compareTo(Match scoreBoardItem) {
        return Integer.compare(getSumScores(), scoreBoardItem.getSumScores());
    }

    @Override
    public String toString() {
        return getHomeTeamName() + " " + getHomeTeamScore() + " : " + getAwayTeamScore() + " " + getAwayTeamName();
    }
}
