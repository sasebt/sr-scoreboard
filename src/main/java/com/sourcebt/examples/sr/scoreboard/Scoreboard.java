package com.sourcebt.examples.sr.scoreboard;


import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.dao.repository.MatchRepository;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;

public class Scoreboard {
    MatchRepository matchRepository;

    public void setMatchRepository(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void startNewMatch(String teamA, String teamB) throws TeamAlreadyPlayingMatchException {
        if (isTeamPlaying(teamA) || isTeamPlaying(teamB)) {
            throw new TeamAlreadyPlayingMatchException();
        }
        matchRepository.create(new Match(teamA, teamB));
    }

    private boolean isTeamPlaying(String team) {
        for (Match match: matchRepository.getMatches ()) {
            if (team.equals(match.getHomeTeamName()) || team.equals(match.getAwayTeamName())) {
                return true;
            }
        }
        return false;
    }

    public Match getMatch(int i) {
        return matchRepository.getMatch(i);
    }
}
