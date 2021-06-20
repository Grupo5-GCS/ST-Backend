package com.fivesolutions.safetravel.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fivesolutions.safetravel.entity.ProfileEntity;
import com.fivesolutions.safetravel.entity.UserEntity;

public class UserPrincipal implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String username;

	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(int id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal create(UserEntity user) {
		List<ProfileEntity> profiles = new ArrayList<ProfileEntity>();
		profiles.add(user.getProfile());
		List<GrantedAuthority> authorities = profiles.stream()
				.map(profile -> new SimpleGrantedAuthority(profile.getLongDescription())).collect(Collectors.toList());

		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), authorities);
	}

	public int getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserPrincipal other = (UserPrincipal) obj;
		return Objects.equals(id, other.id);
	}

}
