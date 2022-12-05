package com.hotdog.server.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotdog.server.domain.user.User;
import com.hotdog.server.domain.user.UserRepository;
import com.hotdog.server.domain.user.UserSearch;

import com.hotdog.server.web.dto.UserDTO;
import com.hotdog.server.web.dto.UserSearchRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public Page<UserDTO> search(UserSearchRequestDTO searchRequestDTO, Pageable pageable) {
		var clientCoordinate = searchRequestDTO.getClientLocation();

		var vertex = clientCoordinate.getVertex();
		UserSearch userSearch = UserSearch.builder()
			.from(vertex.getLeft())
			.to(vertex.getRight())
			.build();

		Page<User> page = userRepository.search(userSearch, pageable);
		List<UserDTO> content = page.getContent().stream()
			.map(UserDTO::fromEntity)
			.collect(Collectors.toList());

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

}
