package com.hotdog.server.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class UserLoginFormDTO {
	@Email( message = " 유효하지 않은 이메일 양식입니다")
	private String email;
	@NotBlank(message = "유효하지 않은 비밀번호입니다")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message= "비밀번호는 8~16자이여야 하며 특수문자를 반드시 한 개 포함하여야합니다")
	private  String password;
}
