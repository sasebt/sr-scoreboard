package com.sourcebt.examples.sr.scoreboard;


import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.dao.repository.MatchRepository;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;

public class Scoreboard {
    MatchRepository matchRepository;

    public void setMatchRepository(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void startNewMatch(String teamA, String teamB) throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {
        if (isTeamNameInvalid(teamA) || isTeamNameInvalid(teamB)) {
            throw new TeamNameInvalidException();
        }

        if (isTeamPlaying(teamA) || isTeamPlaying(teamB)) {
            throw new TeamAlreadyPlayingMatchException();
        }
        matchRepository.create(new Match(teamA, teamB));
    }

    private boolean isTeamNameInvalid (String teamName) {
        return teamName == null || teamName.isBlank();
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

    public void updateScore(String teamA, int teamAScore, String teamB, int teamBScore) {
        Match match = matchRepository.findMatch (teamA, teamB);
        match.setHomeTeamScore(teamAScore);
        match.setAwayTeamScore(teamBScore);
        matchRepository.update (match);
    }
}
