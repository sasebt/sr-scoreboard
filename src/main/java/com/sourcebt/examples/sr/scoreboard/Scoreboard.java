package com.sourcebt.examples.sr.scoreboard;


import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.dao.repository.MatchRepository;
import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;
import com.sourcebt.examples.sr.scoreboard.exceptions.NoMatchFoundException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that supports the following functionalities:
 *
 * 1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
 *    This should capture following parameters:
 *    a. Home team
 *    b. Away team
 * 2. Update score. This should receive a pair of absolute scores: home team score and away
 *    team score.
 * 3. Finish match currently in progress. This removes a match from the scoreboard.
 * 4. Get a summary of matches in progress ordered by their total score. The matches with the
 *    same total score will be returned ordered by the most recently started match in the
 *    scoreboard.
 */
public class Scoreboard {
    MatchRepository matchRepository;

    //used for DI
    public void setMatchRepository(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    /**
     * Creates a new match and adds it to the scoreboard.
     *
     * @param homeTeamName
     * @param awayTeamName
     * @return the new Match with score 0:0
     * @throws TeamAlreadyPlayingMatchException
     * @throws TeamNameInvalidException
     */
    public Match startNewMatch(String homeTeamName, String awayTeamName) throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {
        if (isTeamNameInvalid(homeTeamName) || isTeamNameInvalid(awayTeamName)) {
            throw new TeamNameInvalidException("for match: " + homeTeamName + " " + awayTeamName);
        }

        if (isTeamPlaying(homeTeamName) || isTeamPlaying(awayTeamName)) {
            throw new TeamAlreadyPlayingMatchException("for match: " + homeTeamName + " " + awayTeamName);
        }
        return matchRepository.create(new Match(homeTeamName, awayTeamName));
    }

    private boolean isTeamNameInvalid(String teamName) {
        return teamName == null || teamName.isBlank();
    }

    private boolean isTeamPlaying(String teamName) {
        for (Match match : matchRepository.getMatches()) {
            if (teamName.equals(match.getHomeTeamName()) || teamName.equals(match.getAwayTeamName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the score of an already started match
     *
     * @param homeTeamName
     * @param homeTeamScore
     * @param awayTeamName
     * @param awayTeamScore
     * @return the match that was updated
     * @throws InvalidScoreValueException
     * @throws TeamNameInvalidException
     * @throws NoMatchFoundException
     */
    public Match updateScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) throws InvalidScoreValueException, TeamNameInvalidException, NoMatchFoundException {
        if (isTeamNameInvalid(homeTeamName) || isTeamNameInvalid(awayTeamName)) {
            throw new TeamNameInvalidException("for match: " + homeTeamName + " " + awayTeamName);
        }
        Match match = matchRepository.findMatch(homeTeamName, awayTeamName);
        if (match == null) {
            throw new NoMatchFoundException("for match: " + homeTeamName + " " + awayTeamName);
        }
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
        matchRepository.update(match);
        return match;
    }

    /**
     * Removes the match from the scoreboard
     *
     * @param homeTeamName
     * @param awayTeamName
     * @return the removed match
     * @throws NoMatchFoundException
     * @throws TeamNameInvalidException
     */
    public Match finishMatch(String homeTeamName, String awayTeamName) throws NoMatchFoundException, TeamNameInvalidException {
        if (isTeamNameInvalid(homeTeamName) || isTeamNameInvalid(awayTeamName)) {
            throw new TeamNameInvalidException("for match: " + homeTeamName + " " + awayTeamName);
        }

        Match match = matchRepository.findMatch(homeTeamName, awayTeamName);
        if (match == null) {
            throw new NoMatchFoundException("for match: " + homeTeamName + " " + awayTeamName);
        }
        return matchRepository.delete (match);
    }

    /**
     * Sorts the matches in the scoreboard.
     *
     * @return a sorted list of active matches
     */
    public List<Match> getScoreboardSummary() {
        List<Match> matches = matchRepository.getMatches();
        List<Match> scoreboard = new ArrayList<>(matches);
        Collections.sort(scoreboard);
        Collections.reverse(scoreboard);
        return scoreboard;
    }
}
