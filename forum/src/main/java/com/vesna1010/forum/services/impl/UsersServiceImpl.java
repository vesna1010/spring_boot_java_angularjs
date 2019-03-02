package com.vesna1010.forum.services.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vesna1010.forum.exceptions.ResourceNotFoundException;
import com.vesna1010.forum.models.User;
import com.vesna1010.forum.repositories.UserRepository;
import com.vesna1010.forum.services.UsersService;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Page<User> findUsersByPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public User findUserById(Long id) {
		Optional<User> optional = repository.findById(id);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No user with id " + id));
	}

	@Override
	public User findUserByEmail(String email) {
		Optional<User> optional = repository.findByEmail(email);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No user with email " + email));
	}

	@Override
	public User saveUser(User user) {
		String password = user.getPassword();
		user.setPassword(encodePassword(password));

		return repository.save(user);
	}

	@Override
	public User updateUser(User user, Long id) {
		String password = user.getPassword();
		user.setPassword(encodePassword(password));

		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("No user with id " + id);
		}

		return repository.save(user);
	}

	public String encodePassword(String password) {
		return encoder.encode(password);
	}

	@Override
	public void deleteUserById(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("No user with id " + id);
		}

		repository.deleteById(id);
	}

	@Override
	public void disableUserById(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("No user with id " + id);
		}

		repository.disableById(id);
	}

}
