package com.project.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Repository.UserRepository;
import com.project.model.Password;
import com.project.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public User registerNewuser(User theUser) {
		return userRepository.save(theUser);
	}

	
	public User findById(long theId) {
		Optional<User> result = userRepository.findById(theId);
		User theUser = null;
		
			if(result.isPresent()) {
				theUser = result.get();
			}else {
				throw  new RuntimeException("No id found"+" "+ theId);
			}
		return theUser;
	
	}

	public Password save(Password pass) {
		return userRepository.save(pass);
	}


}
