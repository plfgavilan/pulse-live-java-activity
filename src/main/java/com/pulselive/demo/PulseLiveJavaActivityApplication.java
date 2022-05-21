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
        leagueTableController.getTableEntries("/home/pedro/workspace/pulse-live-java-activity/data/la-liga-0910.csv");

    }
}
