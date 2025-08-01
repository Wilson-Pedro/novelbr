package com.novelsbr.backend.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	SecurityFilter securityFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.cors(cors -> {})
				.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frame -> frame.disable()))
				.sessionManagement(session -> 
							session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/novels/").hasRole("AUTHOR")
						.requestMatchers(HttpMethod.GET, "/novels").permitAll()
						.requestMatchers(HttpMethod.GET, "/novels/search/{novelName}").permitAll()
						.requestMatchers(HttpMethod.GET, "/novels/novelCards/**").permitAll()	
						.requestMatchers(HttpMethod.POST, "/authors/").permitAll()
						.requestMatchers(HttpMethod.GET, "/authors").hasRole("AUTHOR")
						.requestMatchers(HttpMethod.GET, "/authors/username/{username}").hasRole("AUTHOR")
						.requestMatchers(HttpMethod.GET, "/genders").hasRole("AUTHOR")
						.requestMatchers(HttpMethod.GET, "/genders/novel/{novelId}").permitAll()
						.requestMatchers(HttpMethod.POST, "/chapters/").hasRole("AUTHOR")
						.requestMatchers(HttpMethod.GET, "/chapters/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/comments/").permitAll()
						.requestMatchers(HttpMethod.PUT, "/comments/{id}").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/comments/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/comments/**").permitAll()
						.requestMatchers("/upload/**").hasRole("AUTHOR")
						//.requestMatchers(HttpMethod.GET, "/chapters/novelsTile/author/**").permitAll()
						.anyRequest().authenticated()
				)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("*")
				.allowCredentials(true);
			}
		};
	}
}
