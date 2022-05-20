package com.pulselive.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LeagueTableTest {
    private LeagueTable sut;

    @Test
    void getTableEntries() {
        // Arrangement
        List<Match> matches = List.of();

        // Action
        sut = new LeagueTable(matches);
        List<LeagueTableEntry> result = sut.getTableEntries();

        // Asserts
        assertThat(result).isEmpty();
    }

    @Test
    void getTableEntries_havingListOfMatches_ThenGetTheTableLeageSortedByScore() {
        // Arrangement
        List<Match> matches = List.of(
                new Match("TeamA", "TeamB", 2, 1),
                new Match("TeamB", "TeamA", 1, 2)
        );

        // Action
        sut = new LeagueTable(matches);
        List<LeagueTableEntry> result = sut.getTableEntries();

        // Asserts
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPlayed()).isEqualTo(2);
        assertThat(result.get(0).getWon()).isEqualTo(2);
        assertThat(result.get(0).getDrawn()).isZero();
        assertThat(result.get(0).getLost()).isZero();
        assertThat(result.get(0).getGoalsFor()).isEqualTo(4);
        assertThat(result.get(0).getGoalsAgainst()).isEqualTo(2);
        assertThat(result.get(0).getPoints()).isEqualTo(6);

        assertThat(result.get(1).getPlayed()).isEqualTo(2);
        assertThat(result.get(1).getWon()).isZero();
        assertThat(result.get(1).getDrawn()).isZero();
        assertThat(result.get(1).getLost()).isEqualTo(2);
        assertThat(result.get(1).getGoalsFor()).isEqualTo(2);
        assertThat(result.get(1).getGoalsAgainst()).isEqualTo(4);
        assertThat(result.get(1).getPoints()).isZero();
    }
}
