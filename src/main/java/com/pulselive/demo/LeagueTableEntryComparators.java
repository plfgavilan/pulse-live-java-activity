package com.pulselive.demo;

import java.util.Comparator;

@FunctionalInterface
public interface LeagueTableEntryComparators extends Comparator<LeagueTableEntry> {
    Comparator<LeagueTableEntry> byPoints = (o1, o2) -> o2.getPoints() - o1.getPoints();
    Comparator<LeagueTableEntry> byDifference = (o1, o2) -> o2.getGoalDifference() - o1.getGoalDifference();
    Comparator<LeagueTableEntry> byGoalScored = (o1, o2) -> o2.getGoalsFor() - o1.getGoalsFor();
    Comparator<LeagueTableEntry> byTeamName = Comparator.comparing(LeagueTableEntry::getTeamName);
}
