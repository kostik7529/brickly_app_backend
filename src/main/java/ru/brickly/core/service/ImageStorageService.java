package ru.brickly.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String saveMeetingImage(MultipartFile file);

    String saveListingImage(MultipartFile file);

    void deleteImage(String imagePath);
}
