package com.novelsbr.backend.domain.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageResponseDTO<T> {

	private List<T> content;
	
	private int page;
	
	private int size;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean last;
	
	public PageResponseDTO(Page<T> page) {
		this.content = page.getContent();
		this.page = page.getNumber();
		this.size = page.getSize();
		this.totalElements = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.last = page.isLast();
	}

	public List<T> getContent() {
		return content;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public boolean isLast() {
		return last;
	}
}
