package com.novelsbr.backend.enums;

import java.util.stream.Stream;

public enum NovelStatusType {

	IN_COURSE(1, "Em curso"),
	FINISHED(2, "Finalizado"),
	HIATO(3, "Hiato");
	
	private Integer code;
	
	private String status;
	
	NovelStatusType(int code, String status) {
		this.code = code;
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}
	
	public static NovelStatusType toEnum(String status) {
		return Stream.of(NovelStatusType.values())
				.filter(genderType -> genderType.getStatus().equals(status))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Novel status inválido: " + status));
	}
	
	public static NovelStatusType toEnum(Integer code) {
		return Stream.of(NovelStatusType.values())
				.filter(genderType -> genderType.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Id do status da novel inválido: " + code));
	}
}
