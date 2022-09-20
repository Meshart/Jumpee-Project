package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Password;
import com.project.model.User;

@Repository
public interface PasswordRepository extends JpaRepository<User, Long> {

	Password findByUserId(Long userId);

	


}
