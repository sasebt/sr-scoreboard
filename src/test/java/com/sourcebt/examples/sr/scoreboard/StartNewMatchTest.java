package com.sourcebt.examples.sr.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for feature:
 *
 * Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
 *    This should capture following parameters:
 *    a. Home team
 *    b. Away team
 */
class StartNewMatchTest {

    @Test
    void test_shouldSetScoreToZeroZero_whenStartNewMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startNewMatch("team A", "team B");

        assertEquals(0, scoreboard.getMatch(0).getHomeTeamScore());
        assertEquals(0, scoreboard.getMatch(0).getAwayTeamScore());
    }
}