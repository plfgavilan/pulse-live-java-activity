package com.pulselive.demo;

import java.util.List;

public class LeagueTableController {

    private final MatchRepository matchRepository;
    private final LeagueTableView leagueTableView;

    public LeagueTableController(MatchRepository matchRepository, LeagueTableView leagueTableView) {
        this.matchRepository = matchRepository;
        this.leagueTableView = leagueTableView;
    }

    public void getTableEntries(String identifier) {
        List<Match> matches = matchRepository.readAll(identifier);
        LeagueTable leagueTable = new LeagueTable(matches);
        List<LeagueTableEntry> result = leagueTable.getTableEntries();
        leagueTableView.present(identifier, result);
    }
}
