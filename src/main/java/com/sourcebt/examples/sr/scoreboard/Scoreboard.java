package com.sourcebt.examples.sr.scoreboard;


import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    List<Match> matches = new ArrayList<>();

    public void startNewMatch(String teamA, String teamB) throws TeamAlreadyPlayingMatchException {
        for (Match match : matches) {
            if (teamA.equals(match.getHomeTeamName()) || teamB.equals(match.getHomeTeamName()) || teamA.equals(match.getAwayTeamName()) || teamB.equals(match.getAwayTeamName())
            ) {
                throw new TeamAlreadyPlayingMatchException();
            }
        }
        matches.add(new Match(teamA, teamB));
    }

    public Match getMatch(int i) {
        return matches.get(i);
    }
}
