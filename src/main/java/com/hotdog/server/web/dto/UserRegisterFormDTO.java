package com.hotdog.server.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.hotdog.server.domain.Coordinate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegisterFormDTO {

	private String uid;

	@NotBlank(message = "유효하지 않은 강아지 이름입니다")
	private String username;

	@Email(message = "유효하지 않은 이메일 양식입니다")
	private String email;

	@NotBlank(message = "유효하지 않은 견종입니다")
	private CoordinateDto coordinate;

}
