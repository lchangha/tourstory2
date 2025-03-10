package io.traveler.travel.image;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;


import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class ImageValidator {
    private Tika tika = new Tika();
    private long maxSizeBytes = 50 * 1024 * 1024;

    public void validateImage(byte[] data) {
        validateNotEmpty(data);
        validateSize(data);
        validateImageFormat(data);
        validateMimeType(detectMimeType(data));
    }

    private void validateNotEmpty(byte[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("파일이 비어 있습니다.");
        }
    }

    private void validateSize(byte[] data) {
        if (data.length > maxSizeBytes) {
            throw new IllegalArgumentException("파일 크기가 너무 큽니다.");
        }
    }

    public String detectMimeType(byte[] data) {
        return tika.detect(data);
    }

    public void validateMimeType(String mimeType) {
        ImageExtension.getExtension(mimeType);
    }

    private void validateImageFormat(byte[] data) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
            if (ImageIO.read(inputStream) == null) {
                throw new IllegalArgumentException("손상된 이미지 파일입니다.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 파일을 읽을 수 없습니다.", e);
        }
    }
}
