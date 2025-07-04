package com.novelsbr.backend.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.novelsbr.backend.services.UploadService;
import com.novelsbr.backend.web.api.UploadAPI;

@RestController
@CrossOrigin("http://localhost:3000")
public class UploadController implements UploadAPI {
	
	@Autowired
	UploadService uploadService;
	
	private final String folder = "C:\\spring-react\\novelbr\\front\\public\\imagens";

	@PostMapping("/image")
	public ResponseEntity<Void> uploadImage(@RequestParam("file") MultipartFile file) {
		uploadService.upload(folder, file);
		return ResponseEntity.ok().build();
	}
}
