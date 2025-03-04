package io.traveler.travel.util.image.local;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FileNameGenerator {

    public String generateFileName(String mimeType) {
        String extension = ImageExtension.getExtension(mimeType);
        return UUID.randomUUID() + extension;
    }

}
