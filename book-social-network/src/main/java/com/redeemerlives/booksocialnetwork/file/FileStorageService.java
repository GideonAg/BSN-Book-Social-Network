package com.redeemerlives.booksocialnetwork.file;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Service
@Slf4j
public class FileStorageService {

    @Value("${application.file.upload.photos-output-path}")
    private String mainFolder;

    public String saveFile(
            @Nonnull MultipartFile file,
            @Nonnull Integer userId) {
        final String subFolder = "users" + separator + userId;
        return uploadFile(file, subFolder);
    }

    private String uploadFile(MultipartFile file, String subFolder) {
        final String uploadPath = mainFolder + separator + subFolder;
        File targetFolder = new File(uploadPath);

        if (!targetFolder.exists()) {
            boolean createdFolder = targetFolder.mkdirs();
            if (!createdFolder) {
                log.warn("Failed to create target folder");
                return null;
            }
        }

        final String finalFilePath =
                targetFolder + separator + System.currentTimeMillis() + getFileExtension(file.getOriginalFilename());
        Path targetPath = Paths.get(finalFilePath);

        try {
            Files.write(targetPath, file.getBytes());
            log.info("File saved to {}", finalFilePath);
            return finalFilePath;
        } catch(IOException e) {
            log.error("File save failed", e);
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty())
            return "";

        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1)
            return "";
        return fileName.substring(lastDotIndex).toLowerCase();
    }
}
