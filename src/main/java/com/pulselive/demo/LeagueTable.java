package com.pulselive.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pulselive.demo.Constants.ONE;
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
        homeTeam.setPlayed(homeTeam.getPlayed() + 1);
        awayTeam.addGoalsFor(match.getAwayScore());
        awayTeam.addGoalsAgainst(match.getHomeScore());
        awayTeam.setPlayed(awayTeam.getPlayed() + 1);
        if (match.hasNoWinner()) {
            setDraw(homeTeam, awayTeam);
        }
        if (isHomeTeamWinner(match)) {
            setWinner(homeTeam);
            setLooser(awayTeam);
        }
        if (isAwayTeamWinner(match)) {
            setWinner(awayTeam);
            setLooser(homeTeam);
        }
    }

    private boolean isAwayTeamWinner(Match match) {
        return match.getAwayScore() > match.getHomeScore();
    }

    private boolean isHomeTeamWinner(Match match) {
        return match.getHomeScore() > match.getAwayScore();
    }

    private LeagueTableEntry getLeagueTableEntry(String teamName) {
        if (!this.teamList.containsKey(teamName)) {
            this.teamList.put(teamName, new LeagueTableEntry(teamName));
        }
        return this.teamList.get(teamName);
    }

    private void setDraw(LeagueTableEntry homeTeam, LeagueTableEntry awayTeam) {
        this.setDraw(homeTeam);
        this.setDraw(awayTeam);
    }

    private void setDraw(LeagueTableEntry team) {
        team.addDrawn(ONE);
        team.addPoints(ONE);
    }

    private void setLooser(LeagueTableEntry team) {
        team.addLost(ONE);
    }

    private void setWinner(LeagueTableEntry team) {
        team.addWin(ONE);
        team.addPoints(Constants.WINNER_POINTS);
    }
}
