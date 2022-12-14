package com.hotdog.server.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Authority {
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN"),
	;
	private final String role;
}
