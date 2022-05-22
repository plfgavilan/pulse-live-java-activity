package com.pulselive.demo;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SystemOutLeagueTablePresenterImpl implements LeagueTablePresenter {
    private List<String> columns = List.of("Pos", "Team", "Points");
    private Integer delimiterLength;

    @Override
    public void present(String identifier, List<LeagueTableEntry> result) {
        String tittle = identifier.replace("-", " ").toUpperCase();
        System.out.println("=======================");
        System.out.println(" " + tittle + "   ");
        System.out.println("=======================");
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
