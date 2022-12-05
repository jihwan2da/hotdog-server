package com.hotdog.server.common;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PageResponse<T> {
	private Long totalCount;
	private Integer currentCount;
	private Integer currentPage;
	private Integer totalPage;
	private List<T> contents;

	private PageResponse(Long totalCount,
		Integer currentCount,
		Integer currentPage,
		Integer totalPage,
		List<T> contents) {
		this.totalCount = totalCount;
		this.currentCount = currentCount;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.contents = contents;
	}

	public static <T> PageResponse<T> from(Page<T> page) {
		return new PageResponse<>(page.getTotalElements(),
			page.getNumberOfElements(),
			page.getNumber(),
			page.getTotalPages(),
			page.getContent());
	}
}
