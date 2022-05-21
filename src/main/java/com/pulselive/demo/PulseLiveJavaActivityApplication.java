package com.pulselive.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class PulseLiveJavaActivityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PulseLiveJavaActivityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LeagueTableController leagueTableController = new LeagueTableController(
                new MatchRepositoryDataHubSportDataImpl(),
                new LeagueTableView()
        );
        leagueTableController.getTableEntries("la-liga-0910");
        leagueTableController.getTableEntries("la-liga-1011");
        leagueTableController.getTableEntries("la-liga-1112");
        leagueTableController.getTableEntries("la-liga-1213");
        leagueTableController.getTableEntries("la-liga-1314");
        leagueTableController.getTableEntries("la-liga-1415");
        leagueTableController.getTableEntries("la-liga-1516");
        leagueTableController.getTableEntries("la-liga-1617");
        leagueTableController.getTableEntries("la-liga-1718");
        leagueTableController.getTableEntries("la-liga-1819");
    }
}
