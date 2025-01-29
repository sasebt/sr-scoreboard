package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;
import com.sourcebt.examples.sr.scoreboard.exceptions.NoMatchFoundException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for feature:
 * <p>
 * Finish match currently in progress. This removes a match from the scoreboard.
 */
class FinishMatchTest extends BaseTest {

    @Test
    void test_shouldRemoveMatch_whenMatchFinished() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException, InvalidScoreValueException, NoMatchFoundException {
        Match match = scoreboard.startNewMatch("team A", "team B");
        scoreboard.updateScore(match.getHomeTeamName(), 5, match.getAwayTeamName(), 10);
        Match deletedMatch = scoreboard.finishMatch(match.getHomeTeamName(), match.getAwayTeamName());
        assertThrows(NoMatchFoundException.class, () -> scoreboard.updateScore(deletedMatch.getHomeTeamName(), 5, deletedMatch.getAwayTeamName(), 10));
    }

    @Test
    void test_shouldThrowException_whenNonexistingMatchFinished() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException, NoMatchFoundException {
        Match match = scoreboard.startNewMatch("team A", "team B");
        assertThrows(NoMatchFoundException.class, () -> scoreboard.finishMatch(match.getHomeTeamName() + "invalid", match.getAwayTeamName()));
        assertThrows(NoMatchFoundException.class, () -> scoreboard.finishMatch(match.getHomeTeamName(), match.getAwayTeamName() + "invalid"));
    }

    @Test
    void test_shouldThrowException_whenInvalidTeamName() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException, NoMatchFoundException {
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.finishMatch(null, "valid name"));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.finishMatch("", "valid name"));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.finishMatch("    ", "valid name"));

        assertThrows(TeamNameInvalidException.class, () -> scoreboard.finishMatch("valid name", null));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.finishMatch( "valid name", ""));
        assertThrows(TeamNameInvalidException.class, () -> scoreboard.finishMatch("valid name", "    "));
    }

}