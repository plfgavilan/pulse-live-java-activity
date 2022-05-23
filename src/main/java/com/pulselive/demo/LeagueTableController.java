package com.pulselive.demo;

import java.util.List;

public class LeagueTableController {

    private final MatchRepository matchRepository;
    private final LeagueTablePresenter leagueTablePresenter;

    public LeagueTableController(MatchRepository matchRepository, LeagueTablePresenter leagueTablePresenter) {
        this.matchRepository = matchRepository;
        this.leagueTablePresenter = leagueTablePresenter;
    }

    public void getTableEntries(String leagueSeason) {
        List<Match> matches = matchRepository.findAllByLeagueSeason(leagueSeason);
        LeagueTable leagueTable = new LeagueTable(matches);
        List<LeagueTableEntry> result = leagueTable.getTableEntries();
        leagueTablePresenter.present(leagueSeason, result);
    }
}
