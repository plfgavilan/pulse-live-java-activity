package com.pulselive.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueTable {
    private Map<String, LeagueTableEntry> teamList = new HashMap<>();

    public LeagueTable(final List<Match> matches) {
        matches.forEach(this::updateClassification);
    }

    /**
     * Get the ordered list of league table entries for this league table.
     *
     * @return
     */
    public List<LeagueTableEntry> getTableEntries() {
        return this.teamList.values().stream()
                .sorted((o1, o2) -> o2.getPoints() - o1.getPoints())
                .toList();
    }

    private void updateClassification(Match match) {
        if (!this.teamList.containsKey(match.getHomeTeam())) {
            this.teamList.put(match.getHomeTeam(), new LeagueTableEntry(match.getHomeTeam()));
        }
        if (!this.teamList.containsKey(match.getAwayTeam())) {
            this.teamList.put(match.getAwayTeam(), new LeagueTableEntry(match.getAwayTeam()));
        }
        LeagueTableEntry homeTeam = this.teamList.get(match.getHomeTeam());
        LeagueTableEntry awayTeam = this.teamList.get(match.getAwayTeam());

        homeTeam.setGoalsFor(homeTeam.getGoalsFor() + match.getHomeScore());
        homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() + match.getAwayScore());

        awayTeam.setGoalsFor(awayTeam.getGoalsFor() + match.getAwayScore());
        awayTeam.setGoalsAgainst(awayTeam.getGoalsAgainst() + match.getHomeScore());

        if (match.getHomeScore() > match.getAwayScore()) {
            setWinner(homeTeam);
            setLooser(awayTeam);
        }
        if (match.getAwayScore() > match.getHomeScore()) {
            setWinner(awayTeam);
            setLooser(homeTeam);
        }
        if (match.getHomeTeam().equals(match.getAwayTeam())) {
            setDraw(homeTeam);
            setDraw(awayTeam);
        }
    }

    private void setDraw(LeagueTableEntry team) {
        team.setPlayed(team.getPlayed() + 1);
        team.setDrawn(team.getDrawn() + 1);
        team.setPoints(team.getPoints() + 1);
    }

    private void setLooser(LeagueTableEntry team) {
        team.setPlayed(team.getPlayed() + 1);
        team.setLost(team.getLost() + 1);
    }

    private void setWinner(LeagueTableEntry team) {
        team.setPlayed(team.getPlayed() + 1);
        team.setWon(team.getWon() + 1);
        team.setPoints(team.getPoints() + 3);
    }
}
