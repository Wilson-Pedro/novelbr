package com.novelsbr.backend.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@CrossOrigin(origins = "${cors.allowed.origins}")
public class UploadController implements UploadAPI {
	
	@Autowired
	UploadService uploadService;

	@Operation(
			summary = "Upload de imagem"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Upload realizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao realizar upload"),
			@ApiResponse(responseCode = "500", description = "Error ao realizar upload"),
	})
	@PostMapping("/image")
	public ResponseEntity<Void> uploadImage(@RequestParam("file") MultipartFile file) {
		uploadService.upload(file);
		return ResponseEntity.ok().build();
	}
}
