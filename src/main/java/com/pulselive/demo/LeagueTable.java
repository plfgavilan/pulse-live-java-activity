package com.pulselive.demo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Comparator<LeagueTableEntry> byPoints = (o1, o2) -> o2.getPoints() - o1.getPoints();
        Comparator<LeagueTableEntry> byDifference = (o1, o2) -> o2.getGoalDifference() - o1.getGoalDifference();
        Comparator<LeagueTableEntry> byGoalScored = (o1, o2) -> o2.getGoalsFor() - o1.getGoalsFor();
        Comparator<LeagueTableEntry> byTeamName = Comparator.comparing(LeagueTableEntry::getTeamName);
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

        if (match.getHomeScore() > match.getAwayScore()) {
            setWinner(homeTeam);
            setLooser(awayTeam);
        }
        if (match.getAwayScore() > match.getHomeScore()) {
            setWinner(awayTeam);
            setLooser(homeTeam);
        }
        if (match.getHomeScore() == match.getAwayScore()) {
            setDraw(homeTeam);
            setDraw(awayTeam);
        }
    }

    private LeagueTableEntry getLeagueTableEntry(String teamName) {
        if (!this.teamList.containsKey(teamName)) {
            this.teamList.put(teamName, new LeagueTableEntry(teamName));
        }
        return this.teamList.get(teamName);
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
