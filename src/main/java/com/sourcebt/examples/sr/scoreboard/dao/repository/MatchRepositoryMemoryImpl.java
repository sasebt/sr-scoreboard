package com.sourcebt.examples.sr.scoreboard.dao.repository;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryMemoryImpl implements MatchRepository {
    List<Match> matches = new ArrayList<>();

    @Override
    public void create(Match match) {
        matches.add(match);
    }

    @Override
    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public Match getMatch(int i) {
        return matches.get(i);
    }

    @Override
    public Match findMatch(String homeTeamName, String awayTeamName) {
        for (Match match: matches) {
            if (homeTeamName.equals(match.getHomeTeamName()) && awayTeamName.equals(match.getAwayTeamName())) {
                return match;
            }
        }
        return null;
    }

    @Override
    public void update(Match match) {

    }
}
