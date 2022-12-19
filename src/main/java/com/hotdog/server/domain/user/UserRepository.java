package com.hotdog.server.domain.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository
extends JpaRepository<User,Long>, JpaSpecificationExecutor<User>,CustomUserRepository {
	Page<User> findAllBy(Pageable pageable);
	Optional<User> findByEmail(String email);

	Optional<User> findByUid(String uid);
}
