package com.hotdog.server.web.dto;




import com.hotdog.server.domain.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {

	private Long id;
	private String uid;

	private String username;

	private String email;

	private CoordinateDto coordinate;

	public static UserDTO fromEntity(User user) {
		return new UserDTO(
			user.getId(),
			user.getUid(),
			user.getUsername(),
			user.getEmail(),
			new CoordinateDto(
				user.getCoordinate().getLatitude(),
				user.getCoordinate().getLongitude()
			)
		);
	}

}
