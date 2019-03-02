package com.vesna1010.forum.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vesna1010.forum.models.User;

public interface UsersService {

	Page<User> findUsersByPage(Pageable pageable);

	User findUserById(Long id);

	User findUserByEmail(String email);

	User saveUser(User user);

	User updateUser(User user, Long id);

	void deleteUserById(Long id);

	void disableUserById(Long id);

}
