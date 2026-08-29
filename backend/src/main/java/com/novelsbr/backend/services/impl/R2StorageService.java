package com.novelsbr.backend.services.impl;

import com.novelsbr.backend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class R2StorageService implements StorageService {

    @Autowired
    private S3Client s3Client;

    @Value("${cloudflare.r2.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile file, String folder) {
        try {
            String extension = getExtension(file.getOriginalFilename());

            String fileName = UUID.randomUUID() + extension;

            String key = folder + "/" + fileName;

            byte[] bytes = file.getBytes();
            Long bytesLength = (long) bytes.length;

            PutObjectRequest request =
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType(file.getContentType())
                            .contentLength(bytesLength)
                            .build();


            s3Client.putObject(
                    request,
                    RequestBody.fromBytes(bytes)
            );

            return key;
        } catch (IOException e) {
            throw new RuntimeException("Error ao fazer upload de imagem: ", e);
        }
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public byte[] download(String key) {
        return new byte[0];
    }

    private String getExtension(String fileName) {

        if (fileName == null | !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(
                fileName.lastIndexOf(".")
        );
    }
}
