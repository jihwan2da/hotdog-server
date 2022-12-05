package com.hotdog.server.domain.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;

public class CustomUserRepositoryImpl extends QuerydslRepositorySupport implements CustomUserRepository {

	public CustomUserRepositoryImpl() {
		super(User.class);
	}

	@Override
	public List<User> search(UserSearch userSearch) {
		var query = searchUserQuery(userSearch, null);
		return query.fetch();
	}

	@Override
	public Page<User> search(UserSearch partySearch, Pageable pageable) {
		var query = searchUserQuery(partySearch, pageable);
		var queryResult = query.fetchResults();
		return new PageImpl<>(queryResult.getResults(), pageable, queryResult.getTotal());
	}

	private JPQLQuery<User> searchUserQuery(UserSearch userSearch, Pageable pageable) {
		QUser user = QUser.user;
		JPQLQuery<User> query = from(user);

		if (userSearch.getFrom() != null && userSearch.getTo() != null) {
			query.where(
				user.coordinate.latitude.between(
					userSearch.getFrom().getLatitude(),
					userSearch.getTo().getLatitude()),
				user.coordinate.longitude.between(
					userSearch.getFrom().getLongitude(),
					userSearch.getTo().getLongitude())
			);
		}
		if (pageable != null) {
			query.limit(pageable.getPageSize());
			query.offset(pageable.getOffset());
		}
		return query.orderBy(user.id.desc());
	}
}
