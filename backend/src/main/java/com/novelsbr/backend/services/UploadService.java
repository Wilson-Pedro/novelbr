package com.novelsbr.backend.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	String upload(MultipartFile file);
}
