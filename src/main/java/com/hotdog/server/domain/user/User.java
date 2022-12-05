package com.hotdog.server.domain.user;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hotdog.server.domain.BaseTimeEntity;
import com.hotdog.server.domain.Coordinate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Embedded
	private Coordinate coordinate;



	@Builder
	public User(Long id, String email, String password, String username, Coordinate coordinate) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.coordinate = coordinate;
	}

}
