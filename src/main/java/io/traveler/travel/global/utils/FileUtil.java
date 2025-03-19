package io.traveler.travel.global.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtil {

    private FileUtil() {}

    public static byte[] transferImageToBytes(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new byte[0]; // 빈 파일 처리
        }
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("파일 변환 중 오류 발생", e);
        }
    }
}
