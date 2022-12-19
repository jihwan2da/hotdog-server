package com.hotdog.server.domain.user.handler;

import com.hotdog.server.domain.user.User;

public interface UserQueryHandler {
    User findByUid(String uid);
}
