package com.pulselive.demo;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class LeagueTableView {
    private List<String> columns = List.of("Pos", "Team", "Points");
    private Integer delimiterLength;

    public void present(List<LeagueTableEntry> result) {
        this.delimiterLength = result.stream().map(LeagueTableEntry::getTeamName)
                .mapToInt(String::length)
                .max().getAsInt();
        printColumns();
        int position = 1;
        for (LeagueTableEntry leagueTableEntry : result) {
            System.out.printf(getRowTemplate(), position++, leagueTableEntry.getTeamName(), leagueTableEntry.getPoints());
        }
    }

    private void printColumns() {
        System.out.printf(getRowTemplate(), columns.toArray());
        System.out.printf(getRowTemplate(), columns.stream().map(column -> "-".repeat(delimiterLength)).toArray());
    }

    private String getRowTemplate() {
        String template = columns.stream()
                .map(s -> "%" + delimiterLength + "s")
                .collect(Collectors.joining(" | "));
        return "| " + template + " |\n";
    }
}
