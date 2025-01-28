package com.sourcebt.examples.sr.scoreboard;


import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.dao.repository.MatchRepository;
import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;

public class Scoreboard {
    MatchRepository matchRepository;

    public void setMatchRepository(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void startNewMatch(String homeTeamName, String awayTeamName) throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {
        if (isTeamNameInvalid(homeTeamName) || isTeamNameInvalid(awayTeamName)) {
            throw new TeamNameInvalidException();
        }

        if (isTeamPlaying(homeTeamName) || isTeamPlaying(awayTeamName)) {
            throw new TeamAlreadyPlayingMatchException();
        }
        matchRepository.create(new Match(homeTeamName, awayTeamName));
    }

    private boolean isTeamNameInvalid (String teamName) {
        return teamName == null || teamName.isBlank();
    }

    private boolean isTeamPlaying(String teamName) {
        for (Match match: matchRepository.getMatches ()) {
            if (teamName.equals(match.getHomeTeamName()) || teamName.equals(match.getAwayTeamName())) {
                return true;
            }
        }
        return false;
    }

    public Match getMatch(int i) {
        return matchRepository.getMatch(i);
    }

    public void updateScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) throws InvalidScoreValueException {
        if (homeTeamScore<0 || awayTeamScore<0) {
            throw new InvalidScoreValueException();
        }
        Match match = matchRepository.findMatch (homeTeamName, awayTeamName);
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
        matchRepository.update (match);
    }
}
