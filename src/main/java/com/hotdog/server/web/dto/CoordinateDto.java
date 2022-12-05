package com.hotdog.server.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoordinateDto {

	/*
	경도 : x
	 */
	private Double latitude;
	/*
	위도 : y
	 */
	private Double longitude;
}
