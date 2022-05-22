package com.pulselive.demo;

import java.util.List;

public interface MatchRepository {
    List<Match> findAllByLeagueSeason(String id);
}
