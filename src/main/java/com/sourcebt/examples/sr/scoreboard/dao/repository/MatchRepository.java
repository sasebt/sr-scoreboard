package com.sourcebt.examples.sr.scoreboard.dao.repository;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;

import java.util.Collection;
import java.util.List;

public interface MatchRepository {
    void create(Match match);

    List<Match> getMatches();

    Match getMatch(int i);
}
