package com.novelsbr.backend.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String upload(
            MultipartFile file,
            String folder
    );

    void delete(String key);

    byte[] download(String key);
}
