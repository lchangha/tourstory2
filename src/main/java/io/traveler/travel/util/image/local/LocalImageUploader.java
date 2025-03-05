package io.traveler.travel.util.image.local;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class LocalImageUploader implements ImageUploader{

    private final FolderNameGenerator folderNameGenerator;
    private final FileNameGenerator imageFileNameGenerator;
    private final StorageHandler storageHandler;
    private final ImageValidator imageValidator;

    public LocalImageUploader(FolderNameGenerator folderNameGenerator, FileNameGenerator imageFileNameGenerator,
            StorageHandler storageHandler, ImageValidator imageValidator) {
        this.folderNameGenerator = folderNameGenerator;
        this.imageFileNameGenerator = imageFileNameGenerator;
        this.storageHandler = storageHandler;
        this.imageValidator = imageValidator;
    }

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

