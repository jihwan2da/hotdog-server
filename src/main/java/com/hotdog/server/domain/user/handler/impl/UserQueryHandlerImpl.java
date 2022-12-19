package com.hotdog.server.domain.user.handler.impl;

import com.hotdog.server.domain.user.User;
import com.hotdog.server.domain.user.UserRepository;
import com.hotdog.server.domain.user.handler.UserQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserRepository userRepository;

    @Override
    public User findByUid(String uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }
}
