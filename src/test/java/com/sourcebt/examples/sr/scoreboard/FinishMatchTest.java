package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;
import com.sourcebt.examples.sr.scoreboard.exceptions.NoMatchFoundException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for feature:
 * <p>
 * Finish match currently in progress. This removes a match from the scoreboard.
 */
class FinishMatchTest extends BaseTest {

    @org.junit.jupiter.api.Test
    void test_shouldRemoveMatch_whenMatchFinished() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException, InvalidScoreValueException, NoMatchFoundException {
        Match match = scoreboard.startNewMatch("team A", "team B");
        scoreboard.updateScore(match.getHomeTeamName(), 5, match.getAwayTeamName(), 10);
        scoreboard.finishMatch(match.getHomeTeamName(), match.getAwayTeamName());
        assertThrows(NoMatchFoundException.class, () -> scoreboard.updateScore(match.getHomeTeamName(), 5, match.getAwayTeamName(), 10));
    }

}