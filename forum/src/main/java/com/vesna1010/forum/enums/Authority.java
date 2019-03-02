package com.vesna1010.forum.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

	USER, ADMIN;

	@Override
	public String getAuthority() {
		return this.toString();
	}

}
