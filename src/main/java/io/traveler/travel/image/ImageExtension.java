package io.traveler.travel.image;

import java.util.Arrays;

public enum ImageExtension {
    JPEG("image/jpeg", ".jpg"),
    PNG("image/png", ".png"),
    GIF("image/gif", ".gif");

    private final String mimeType;
    private final String extension;

    ImageExtension(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }

    public static String getExtension(String mimeType) {
        return Arrays.stream(values())
                .filter(e -> e.mimeType.equals(mimeType))
                .findFirst()
                .map(e -> e.extension)
                .orElseThrow(() -> new IllegalArgumentException("지원되지 않는 파일 확장자: " + mimeType));
    }
}
