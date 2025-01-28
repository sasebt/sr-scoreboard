package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for feature:
 * <p>
 * Finish match currently in progress. This removes a match from the scoreboard.
 */
class UpdateScoreTest extends BaseTest {

    @Test
    void test_shouldUpdateScore_whenMatchStarted() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException, InvalidScoreValueException {

        scoreboard.startNewMatch("team A", "team B");

        Match match = scoreboard.getMatch(0);

        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getHomeTeamScore());

        scoreboard.updateScore("team A", 2, "team B", 3);

        assertEquals(2, match.getHomeTeamScore());
        assertEquals(3, match.getAwayTeamScore());
    }

    @Test
    void test_shouldNotUpdateScore_whenNegativeScore() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {
        scoreboard.startNewMatch("team A", "team B");

        assertThrows(InvalidScoreValueException.class, () -> scoreboard.updateScore("team A", -2, "team B", 3));
        assertThrows(InvalidScoreValueException.class, () -> scoreboard.updateScore("team A", 2, "team B", -3));
        assertThrows(InvalidScoreValueException.class, () -> scoreboard.updateScore("team A", -2, "team B", -3));
    }

    @Test
    void test_shouldThrowException_whenNoMatch() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {
        scoreboard.startNewMatch("team A", "team B");

        assertThrows(TeamNameInvalidException.class, () -> scoreboard.updateScore("team", -2, "team B", 3));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.updateScore("", -2, "team B", 3));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.updateScore(null, -2, "team B", 3));

        assertThrows(TeamNameInvalidException.class, () -> scoreboard.updateScore("team A", -2, "team", 3));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.updateScore("team A", -2, "", 3));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.updateScore("team A", -2, null, 3));
    }

}