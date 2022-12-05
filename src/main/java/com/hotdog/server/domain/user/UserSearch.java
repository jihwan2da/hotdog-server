package com.hotdog.server.domain.user;



import com.hotdog.server.domain.Coordinate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class UserSearch {
	private Coordinate from;
	private Coordinate to;
}
