package com.pulselive.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Log4j2
public class PulseLiveJavaActivityApplication implements CommandLineRunner {

    private final List<String> leagueSeasonsIds = List.of(
            "la-liga-0910", "la-liga-1011", "la-liga-1112", "la-liga-1213", "la-liga-1314",
            "la-liga-1415", "la-liga-1516", "la-liga-1617", "la-liga-1718", "la-liga-1819",
            "premier-league-0910", "premier-league-1011", "premier-league-1112", "premier-league-1213", "premier-league-1314",
            "premier-league-1415", "premier-league-1516", "premier-league-1617", "premier-league-1718", "premier-league-1819"
    );

    public static void main(String[] args) {
        SpringApplication.run(PulseLiveJavaActivityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LeagueTableController leagueTableController = new LeagueTableController(
                new MatchRepositoryDataHubSportFileSystemImpl(),
                new LeagueTableSystemOutPresenterImpl()
        );
        for (String leagueSeasonId : leagueSeasonsIds) {
            leagueTableController.getTableEntries(leagueSeasonId);
        }
    }
}
