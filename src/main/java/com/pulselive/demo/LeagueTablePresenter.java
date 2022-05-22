package com.pulselive.demo;

import java.util.List;

public interface LeagueTablePresenter {
    void present(String identifier, List<LeagueTableEntry> result);
}
