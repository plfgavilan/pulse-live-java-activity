package com.pulselive.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class MatchRepositoryDataHubSportFileSystemImpl implements MatchRepository {
    @Override
    public List<Match> findAllByLeagueSeason(String fileName) {
        String absolutePath = new FileSystemResource("").getFile().getAbsolutePath();
        log.info("Base path csv(s): {}", absolutePath);
        Path path = Paths.get(absolutePath + "/data/" + fileName + ".csv");
        Stream<String> stream = null;
        try {
            stream = Files.lines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stream
                .skip(1)
                .map(fromFile -> {
                    String[] split = fromFile.split(",");
                    return new Match(split[2], split[3], Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                })
                .collect(Collectors.toList());
    }
}
