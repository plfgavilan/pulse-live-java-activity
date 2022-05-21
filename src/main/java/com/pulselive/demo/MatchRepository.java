package com.pulselive.demo;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public interface MatchRepository {
    List<Match> readAll(String id);
}
