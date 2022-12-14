package com.hotdog.server.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotdog.server.common.PageResponse;
import com.hotdog.server.domain.user.User;
import com.hotdog.server.service.UserService;
import com.hotdog.server.web.dto.UserDTO;
import com.hotdog.server.web.dto.UserRegisterFormDTO;
import com.hotdog.server.web.dto.UserRegisterResponseDTO;
import com.hotdog.server.service.AuthService;
import com.hotdog.server.web.dto.UserSearchRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final AuthService authService;

	private final UserService userService;

	@PostMapping("/auth/register")
	public UserRegisterResponseDTO registerUser(@RequestBody UserRegisterFormDTO userRegisterFormDTO) {
		System.out.println("ininin");
		return authService.registerUser(userRegisterFormDTO);
	}


	@GetMapping("denied")
	public String denied(){
		return "access denied";
	}

	@GetMapping("/search")
	public ResponseEntity<PageResponse> search(
		@RequestParam(value = "uid") String uid,
		@PageableDefault Pageable pageable) {
		Page<UserDTO> userPage = userService.search(uid, pageable);
		PageResponse<UserDTO> pageResponse = PageResponse.from(userPage);
		return ResponseEntity.ok(pageResponse);
	}
}
