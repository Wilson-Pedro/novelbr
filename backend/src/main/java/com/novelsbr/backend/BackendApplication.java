package com.novelsbr.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.novelsbr.backend.domain.entities.User;
import com.novelsbr.backend.repositories.UserRepository;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	
//	@Autowired
//	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		User user = new User(null, "João", "AllStar", "joao@gmail.com", "1234");
//		userRepository.save(user);
	}

}
