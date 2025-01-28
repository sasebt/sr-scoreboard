package com.sourcebt.examples.sr.scoreboard;

import com.sourcebt.examples.sr.scoreboard.dao.repository.MatchRepositoryMemoryImpl;

public class ScoreboardFactory {
    public static Scoreboard createInMemoryFactory() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setMatchRepository(new MatchRepositoryMemoryImpl());
        return scoreboard;
    }
}
