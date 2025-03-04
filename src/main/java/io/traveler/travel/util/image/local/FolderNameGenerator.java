package io.traveler.travel.util.image.local;

import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FolderNameGenerator {

    public String generateFolderName() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return Paths.get(LocalDate.now().format(format)).toString();
    }
}
