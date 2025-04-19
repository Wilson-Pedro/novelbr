package com.novelsbr.backend.enums;

public enum UserRole {

	AUTHOR("author"),
	USER("user");
	
	private String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
}
