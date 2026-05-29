package ru.brickly.core.service.impl;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.brickly.core.exception.ImageUploadException;
import ru.brickly.core.service.ImageStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Value("${app.upload.base-dir}")
    private String baseUploadDir;

    @Override
    public String saveMeetingImage(MultipartFile file) {
        return saveAndCompressImage(file, "meetings", 1000, 0.8f);
    }

    @Override
    public String saveListingImage(MultipartFile file) {
        return saveAndCompressImage(file, "listings", 1200, 0.85f);
    }

    @Override
    public void deleteImage(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return;
        }

        try {
            String relativePath = imagePath.startsWith("/") ? imagePath.substring(1) : imagePath;
            Path fileToDelete = Paths.get(baseUploadDir, relativePath);

            if (Files.exists(fileToDelete)) {
                Files.delete(fileToDelete);
            }

        } catch (IOException e) {
            System.err.println("Failed to delete file: " + imagePath);
            e.printStackTrace();
        }
    }

    private String saveAndCompressImage(MultipartFile file, String folder, int maxWidth, float quality) {
        if (file.isEmpty()) {
            throw new ImageUploadException("Image file is empty!");
        }

        try {
            Path uploadPath = Paths.get(baseUploadDir, folder);
            Files.createDirectories(uploadPath);

            String originalName = file.getOriginalFilename();
            String extension = originalName != null && originalName.contains(".")
                    ? originalName.substring(originalName.lastIndexOf("."))
                    : ".jpg";

            String newFileName = UUID.randomUUID() + extension.toLowerCase();
            Path filePath = uploadPath.resolve(newFileName);

            Thumbnails.of(file.getInputStream())
                    .width(maxWidth)
                    .outputQuality(quality)
                    .toFile(filePath.toFile());

            return "/uploads/" + folder + "/" + newFileName;

        } catch (IOException e) {
            throw new ImageUploadException("Failed to save image: " + e.getMessage());
        }
    }
}