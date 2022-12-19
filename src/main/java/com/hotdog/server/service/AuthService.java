package com.hotdog.server.service;

import java.util.Optional;

import com.hotdog.server.domain.Coordinate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotdog.server.domain.user.UserRepository;

import com.hotdog.server.web.dto.UserRegisterFormDTO;
import com.hotdog.server.web.dto.UserRegisterResponseDTO;
import com.hotdog.server.exception.AuthException;
import com.hotdog.server.domain.user.User;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;



	@Transactional
	public UserRegisterResponseDTO registerUser(UserRegisterFormDTO userRegisterFormDTO) {
		validateDupliateUser(userRegisterFormDTO);

		User user = User.builder()
			.email(userRegisterFormDTO.getEmail())
			.password(userRegisterFormDTO.getPassword())
			.username(userRegisterFormDTO.getUsername())
			.coordinate(Coordinate.from(userRegisterFormDTO.getCoordinate()))
			.build();

		User saveUser = userRepository.save(user);

		return new UserRegisterResponseDTO(saveUser.getId());

	}
	private void validateDupliateUser(UserRegisterFormDTO userRegisterFormDTO)
	{
		Optional<User> result = userRepository.findByEmail(userRegisterFormDTO.getEmail());
		if(result.isPresent())
		{
			throw new AuthException("이미 존재하는 사용자입니다",HttpStatus.BAD_REQUEST);
		}
	}
}
