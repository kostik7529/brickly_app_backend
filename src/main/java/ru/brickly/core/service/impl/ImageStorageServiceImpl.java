package ru.brickly.core.service.impl;

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

    @Value("${app.upload.base-dir:uploads}")
    private String baseUploadDir;

    @Override
    public String saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ImageUploadException("Image file is empty!");
        }

        try {
            // Папка для сохранения
            Path uploadPath = Paths.get(baseUploadDir, "meetings/previews");
            Files.createDirectories(uploadPath);

            // Генерация имени файла
            String originalName = file.getOriginalFilename();
            String extension = originalName != null && originalName.contains(".")
                    ? originalName.substring(originalName.lastIndexOf("."))
                    : ".jpg";

            String newFileName = UUID.randomUUID() + extension.toLowerCase();
            Path filePath = uploadPath.resolve(newFileName);

            // Сохраняем файл
            file.transferTo(filePath.toFile());

            System.out.println("✅ Image saved: " + filePath);

            // Возвращаем URL-путь (для фронта)
            return "/uploads/meetings/previews/" + newFileName;

        } catch (IOException e) {
            throw new ImageUploadException("Failed to save image: " + e.getMessage());
        }
    }
}