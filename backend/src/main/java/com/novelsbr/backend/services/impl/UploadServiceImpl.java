package com.novelsbr.backend.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.novelsbr.backend.services.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	
	public String upload(String folder, MultipartFile file) {
		String fileName = "";
		
		try {
			
			fileName = file.getOriginalFilename();
			Path filePath = Paths.get(folder, fileName);
			
			Files.createDirectories(filePath.getParent());
			Files.write(filePath, file.getBytes());
			
			return fileName;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
