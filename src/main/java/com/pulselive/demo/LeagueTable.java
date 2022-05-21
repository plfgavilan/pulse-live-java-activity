package com.pulselive.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pulselive.demo.LeagueTableEntryComparators.*;

public class LeagueTable {
    private final Map<String, LeagueTableEntry> teamList = new HashMap<>();

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
                .sorted(byPoints
                        .thenComparing(byDifference)
                        .thenComparing(byGoalScored)
                        .thenComparing(byTeamName))
                .toList();
    }

    private void updateClassification(Match match) {
        LeagueTableEntry homeTeam = getLeagueTableEntry(match.getHomeTeam());
        LeagueTableEntry awayTeam = getLeagueTableEntry(match.getAwayTeam());
        homeTeam.addGoalsFor(match.getHomeScore());
        homeTeam.addGoalsAgainst(match.getAwayScore());
        awayTeam.addGoalsFor(match.getAwayScore());
        awayTeam.addGoalsAgainst(match.getHomeScore());
        if (match.hasNoWinner()) {
            homeTeam.addDrawn();
            awayTeam.addDrawn();
        }
        if (match.isHomeTeamWinner()) {
            homeTeam.addWin();
            awayTeam.addLost();
        }
        if (match.isAwayTeamWinner()) {
            awayTeam.addWin();
            homeTeam.addLost();
        }
    }

    private LeagueTableEntry getLeagueTableEntry(String teamName) {
        if (!this.teamList.containsKey(teamName)) {
            this.teamList.put(teamName, new LeagueTableEntry(teamName));
        }
        return this.teamList.get(teamName);
    }

}
