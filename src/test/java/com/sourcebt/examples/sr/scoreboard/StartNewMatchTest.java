package com.sourcebt.examples.sr.scoreboard;

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
class StartNewMatchTest {

    @Test
    void test_shouldSetScoreToZeroZero_whenStartNewMatch() throws TeamAlreadyPlayingMatchException {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startNewMatch("team A", "team B");

        assertEquals(0, scoreboard.getMatch(0).getHomeTeamScore());
        assertEquals(0, scoreboard.getMatch(0).getAwayTeamScore());
    }

    @Test
    void test_shouldThrowDuplicateException_whenStartNewMatch() throws TeamAlreadyPlayingMatchException {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startNewMatch("team A", "team B");
        scoreboard.startNewMatch("team C", "team D");
        scoreboard.startNewMatch("team E", "team F");
        assertThrows(TeamAlreadyPlayingMatchException.class, () -> scoreboard.startNewMatch("team C", "team D"));
        assertThrows(TeamAlreadyPlayingMatchException.class, () -> scoreboard.startNewMatch("team B", "team X"));
        assertThrows(TeamAlreadyPlayingMatchException.class, () -> scoreboard.startNewMatch("team E", "team X"));
    }

}