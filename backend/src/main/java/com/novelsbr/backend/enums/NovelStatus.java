package com.novelsbr.backend.enums;

import java.util.stream.Stream;

public enum NovelStatus {

	IN_COURSE(1, "em curso"),
	FINISHED(2, "finalizado"),
	HIATO(3, "hiato");
	
	private Integer code;
	
	private String status;
	
	NovelStatus(int code, String status) {
		this.code = code;
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}
	
	public static NovelStatus toEnum(String status) {
		return Stream.of(NovelStatus.values())
				.filter(genderType -> genderType.getStatus().equals(status))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Novel status inválido: " + status));
	}
	
	public static NovelStatus toEnum(Integer code) {
		return Stream.of(NovelStatus.values())
				.filter(genderType -> genderType.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Id do status da novel inválido: " + code));
	}
}
