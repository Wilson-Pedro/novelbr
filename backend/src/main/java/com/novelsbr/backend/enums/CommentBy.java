package com.novelsbr.backend.enums;

import java.util.stream.Stream;

public enum CommentBy {

	NOVEL(1, "novel"),
	CHAPTER(2, "chapter");
	
	private Integer code;
	
	private String type;
	
	CommentBy(int code, String type) {
		this.code = code;
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public String getType() {
		return type;
	}
	
	public static CommentBy toEnum(String type) {
		return Stream.of(CommentBy.values())
				.filter(genderType -> genderType.getType().equals(type))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Tipo de comentário inválido: " + type));
	}
	
	public static CommentBy toEnum(Integer code) {
		return Stream.of(CommentBy.values())
				.filter(genderType -> genderType.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Id comentário inválido: " + code));
	}
}
