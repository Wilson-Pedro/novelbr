package com.novelsbr.backend.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/upload")
public interface UploadAPI {
	
	@PostMapping("/image")
	public ResponseEntity<Void> uploadImage(@RequestParam("file") MultipartFile file);
}
