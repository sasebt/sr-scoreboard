package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for feature:
 * <p>
 * Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
 * This should capture following parameters:
 * a. Home team
 * b. Away team
 */
class StartNewMatchTest extends BaseTest {



    @Test
    void test_shouldSetScoreToZeroZero_whenStartNewMatch() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {

        scoreboard.startNewMatch("team A", "team B");

        assertEquals(0, scoreboard.getMatch(0).getHomeTeamScore());
        assertEquals(0, scoreboard.getMatch(0).getAwayTeamScore());
    }

    @Test
    void test_shouldThrowDuplicateException_whenStartNewMatch() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {
        scoreboard.startNewMatch("team A", "team B");
        scoreboard.startNewMatch("team C", "team D");
        scoreboard.startNewMatch("team E", "team F");
        assertThrows(TeamAlreadyPlayingMatchException.class, () -> scoreboard.startNewMatch("team C", "team D"));
        assertThrows(TeamAlreadyPlayingMatchException.class, () -> scoreboard.startNewMatch("team B", "team X"));
        assertThrows(TeamAlreadyPlayingMatchException.class, () -> scoreboard.startNewMatch("team E", "team X"));
    }

    @Test()
    void test_shouldThrowException_whenStartNewMatchTeamIsEmptyOrNull() {
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.startNewMatch(null, "team B"));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.startNewMatch("", "team B"));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.startNewMatch("    \t\n", "team B"));

        assertThrows(TeamNameInvalidException.class, () -> scoreboard.startNewMatch("team A", null));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.startNewMatch("team A", ""));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.startNewMatch("team A", "    \t\n"));
    }
}