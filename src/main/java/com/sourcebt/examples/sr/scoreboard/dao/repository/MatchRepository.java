package com.sourcebt.examples.sr.scoreboard.dao.repository;

import com.sourcebt.examples.sr.scoreboard.dao.model.Match;

import java.util.Collection;
import java.util.List;

public interface MatchRepository {
    Match create(Match match);

    List<Match> getMatches();

    Match getMatch(int i);

    Match findMatch(String homeTeamName, String awayTeamName);

    Match update(Match match);

    Match delete(Match match);
}
