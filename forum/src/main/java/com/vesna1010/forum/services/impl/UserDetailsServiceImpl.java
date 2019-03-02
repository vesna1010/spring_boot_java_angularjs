package com.vesna1010.forum.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vesna1010.forum.models.User;
import com.vesna1010.forum.repositories.UserRepository;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optional = repository.findByEmail(email);
		User user = optional.orElseThrow(() -> new UsernameNotFoundException("No user found with email " + email));
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(user.getAuthority());

		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), user.isEnabled(), true,
				true, true, authorities);

	}

}
