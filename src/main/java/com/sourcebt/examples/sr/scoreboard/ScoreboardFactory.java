package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.dao.repository.MatchRepositoryMemoryImpl;

public class ScoreboardFactory {

    /**
     * Creates a scoreboard instance initialized with in memory match repository implementation
     *
     * @return
     */
    public static Scoreboard createInMemoryFactory() {
        Scoreboard scoreboard = new Scoreboard();
        // injecting the in memory match repository implementation
        scoreboard.setMatchRepository(new MatchRepositoryMemoryImpl());
        return scoreboard;
    }
}
