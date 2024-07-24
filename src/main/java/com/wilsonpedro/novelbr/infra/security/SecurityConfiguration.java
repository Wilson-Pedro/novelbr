package com.wilsonpedro.novelbr.infra.security;

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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
						// USER
						.requestMatchers(HttpMethod.POST, "/users/").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/users/pages").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/users/{id}").hasAnyRole("ADMIN", "AUTHOR", "READER")
						.requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/users/{id}/toAuthor").hasRole("READER")
						// NOVEL
						.requestMatchers(HttpMethod.POST, "/novels/").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/novels").hasRole("READER")
						.requestMatchers(HttpMethod.GET, "/novels/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/novels/pages").hasRole("READER")
						.requestMatchers(HttpMethod.PUT, "/novels/{id}").hasAnyRole("ADMIN", "AUTHOR", "READER")
						.requestMatchers(HttpMethod.DELETE, "/novels/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/novels/deleteAllByAuthor").hasRole("ADMIN")
						// CHAPTER
						.requestMatchers(HttpMethod.POST, "/chapters/").hasRole("AUTHOR")
						.requestMatchers(HttpMethod.GET, "/chapters").hasRole("READER")
						.requestMatchers(HttpMethod.GET, "/chapters/{id}").hasRole("READER")
						.requestMatchers(HttpMethod.GET, "/chapters/pages").hasRole("READER")
						.requestMatchers(HttpMethod.PUT, "/chapters/{id}").hasAnyRole("ADMIN", "AUTHOR", "READER")
						.requestMatchers(HttpMethod.DELETE, "/chapters/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/chapters/deleteAllByAuthor").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
