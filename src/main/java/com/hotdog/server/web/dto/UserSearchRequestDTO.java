package com.hotdog.server.web.dto;



import com.hotdog.server.domain.Coordinate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Builder
@Setter
@NoArgsConstructor
public class UserSearchRequestDTO {

	private Coordinate clientLocation;

}
