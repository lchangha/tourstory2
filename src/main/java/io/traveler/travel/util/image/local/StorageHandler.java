package io.traveler.travel.util.image.local;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StorageHandler {

    private final String baseDirectory = "uploads";

    public String store(byte[] data, String folderName, String fileName) throws IOException {
            Path folderPath = Paths.get(baseDirectory, folderName);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            Path filePath = folderPath.resolve(fileName);

            Files.write(filePath, data);
            return filePath.toString();

    }
}
