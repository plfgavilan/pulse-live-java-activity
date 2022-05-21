package com.pulselive.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Log4j2
public class PulseLiveJavaActivityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PulseLiveJavaActivityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        MatchRepository matchRepository = new MatchRepositoryDataHubSportDataImpl();
        List<Match> matches = matchRepository.readAll("/home/pedro/workspace/pulse-live-java-activity/data/la-liga-0910.csv");
        LeagueTable leagueTable = new LeagueTable(matches);
        List<LeagueTableEntry> result = leagueTable.getTableEntries();
        LeagueTableView leagueTableView = new LeagueTableView();
        leagueTableView.present(result);
    }
}
