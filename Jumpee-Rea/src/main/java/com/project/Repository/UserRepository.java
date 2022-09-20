package com.project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Password;
import com.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail (String email);
	boolean existsByEmail(String email);
	boolean existsByContact(String contact);
	Password save(Password pass);
	User findByUserId(Long userId);
}
