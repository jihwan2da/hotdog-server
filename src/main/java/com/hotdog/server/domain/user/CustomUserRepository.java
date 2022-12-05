package com.hotdog.server.domain.user;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public interface CustomUserRepository{

	List<User> search(UserSearch userSearch);
	Page<User> search(UserSearch userSearch, Pageable pageable);
}
