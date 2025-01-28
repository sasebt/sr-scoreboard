package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for feature:
 * <p>
 * Finish match currently in progress. This removes a match from the scoreboard.
 */
class UpdateScoreTest {
    private Scoreboard scoreboard;


    @BeforeEach
    public void setup() {
        scoreboard = ScoreboardFactory.createInMemoryFactory();
    }

    @Test
    void test_shouldUpdateScore_whenMatchStarted() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException {

        scoreboard.startNewMatch("team A", "team B");

        Match match = scoreboard.getMatch(0);

        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getHomeTeamScore());

        scoreboard.updateScore("team A", 2, "team B", 3);

        assertEquals(2, match.getHomeTeamScore());
        assertEquals(3, match.getAwayTeamScore());
    }

}