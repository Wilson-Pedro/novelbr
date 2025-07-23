package com.novelsbr.backend.enums;

import java.util.stream.Stream;

public enum CommentType {

	COMMENT(1, "comentário"),
	ANSWER(2, "resposta");
	
	private Integer code;
	
	private String type;
	
	CommentType(int code, String type) {
		this.code = code;
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public String getType() {
		return type;
	}
	
	public static CommentType toEnum(String type) {
		return Stream.of(CommentType.values())
				.filter(genderType -> genderType.getType().equals(type))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Tipo de comentário inválido: " + type));
	}
	
	public static CommentType toEnum(Integer code) {
		return Stream.of(CommentType.values())
				.filter(genderType -> genderType.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Id comentário inválido: " + code));
	}
}
