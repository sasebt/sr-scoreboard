package com.sourcebt.examples.sr.scoreboard;

import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    Scoreboard scoreboard;

    @BeforeEach
    public void setup() {
        scoreboard = ScoreboardFactory.createInMemoryFactory();
    }
}
