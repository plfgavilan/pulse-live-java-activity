package com.pulselive.demo;

import java.util.List;

public class LeagueTableController {

    private final MatchRepository matchRepository;
    private final LeagueTablePresenter leagueTableView;

    public LeagueTableController(MatchRepository matchRepository, SystemOutLeagueTablePresenterImpl leagueTableView) {
        this.matchRepository = matchRepository;
        this.leagueTableView = leagueTableView;
    }

    public void getTableEntries(String leagueSeason) {
        List<Match> matches = matchRepository.findAllByLeagueSeason(leagueSeason);
        LeagueTable leagueTable = new LeagueTable(matches);
        List<LeagueTableEntry> result = leagueTable.getTableEntries();
        leagueTableView.present(leagueSeason, result);
    }
}
