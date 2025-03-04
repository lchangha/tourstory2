package io.traveler.travel.util.image.local;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@AllArgsConstructor
public class ImageUploadManager {

    private final FolderNameGenerator folderNameGenerator;
    private final FileNameGenerator imageFileNameGenerator;
    private final StorageHandler storageHandler;
    private final ImageValidator imageValidator;

    public String handleUpload(byte[] data) {
        try {
            imageValidator.validateImage(data);
            String mimeType = imageValidator.detectMimeType(data);
            imageValidator.validateMimeType(mimeType);
            String fileName = imageFileNameGenerator.generateFileName(mimeType);
            String folderPath = folderNameGenerator.generateFolderName();

            return storageHandler.store(data, folderPath, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

