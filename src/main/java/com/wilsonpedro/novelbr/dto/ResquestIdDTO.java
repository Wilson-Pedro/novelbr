package com.wilsonpedro.novelbr.dto;

import java.io.Serializable;

public class ResquestIdDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long requestId;
	
	public ResquestIdDTO() {
	}
	
	public ResquestIdDTO(Long requestId) {
		this.requestId = requestId;
	}

	public Long getRequestId() {
		return requestId;
	}
}
