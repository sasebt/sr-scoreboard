package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;
import com.sourcebt.examples.sr.scoreboard.exceptions.InvalidScoreValueException;
import com.sourcebt.examples.sr.scoreboard.exceptions.NoMatchFoundException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamAlreadyPlayingMatchException;
import com.sourcebt.examples.sr.scoreboard.exceptions.TeamNameInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for feature:
 * <p>
 * Get a summary of matches in progress ordered by their total score. The matches with the
 * same total score will be returned ordered by the most recently started match in the
 * scoreboard.
 */
class SummaryTest extends BaseTest {

    @Test
    void test_shouldSortMatches_whenSummarized() throws TeamAlreadyPlayingMatchException, TeamNameInvalidException, InvalidScoreValueException, NoMatchFoundException {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Spain", "Brazil");
        scoreboard.startNewMatch("Germany", "France");
        scoreboard.startNewMatch("Uruguay", "Italy");
        scoreboard.startNewMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", 0, "Canada", 5);
        scoreboard.updateScore("Spain", 10, "Brazil", 2);
        scoreboard.updateScore("Germany", 2, "France", 2);
        scoreboard.updateScore("Uruguay", 6, "Italy", 6);
        scoreboard.updateScore("Argentina", 3, "Australia", 1);

        List<Match> summaryBoard = scoreboard.getScoreboardSummary();
        String[] expectedResults = {
                "Uruguay 6 : 6 Italy",
                "Spain 10 : 2 Brazil",
                "Mexico 0 : 5 Canada",
                "Argentina 3 : 1 Australia",
                "Germany 2 : 2 France"
        };

        for (int i=0; i<expectedResults.length; i++) {
            assertEquals(expectedResults[i], summaryBoard.get(i).toString());
        }

        scoreboard.updateScore("Argentina", 10, "Australia", 20);
        summaryBoard = scoreboard.getScoreboardSummary();
        String[] newExpectedResults = {
                "Argentina 10 : 20 Australia",
                "Uruguay 6 : 6 Italy",
                "Spain 10 : 2 Brazil",
                "Mexico 0 : 5 Canada",
                "Germany 2 : 2 France"
        };

        for (int i=0; i<expectedResults.length; i++) {
            assertEquals(newExpectedResults[i], summaryBoard.get(i).toString());
        }
    }
}