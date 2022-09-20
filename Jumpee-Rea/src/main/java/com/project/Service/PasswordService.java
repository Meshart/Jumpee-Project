package com.project.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Repository.PasswordRepository;
import com.project.Repository.UserRepository;
import com.project.Response.UserInformation;
import com.project.model.Password;
import com.project.model.User;

@Service
public class PasswordService {
	
	@Autowired
	PasswordRepository passwordRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public boolean changePass(Password pass) {
		
		//Password alpha
		Long userId = UserInformation.userId;
		User userDetails=userRepository.findByUserId(userId);
		String password = pass.getPassword();
		String password2 = pass.getConfirmPassword();
		
		
		if(password.equals(password2)) {
			//For Encrypting password
	        String encodedPassword = this.passwordEncoder.encode(password);
	        pass.setPassword(encodedPassword);
	        String encodedConfirmPassword = this.passwordEncoder.encode(password2);
	        pass.setCpnfirmPassword(encodedConfirmPassword);
			userDetails.setPassword(encodedPassword);
			userDetails.setConfirmPassword(encodedConfirmPassword);
				passwordRepository.save(userDetails); 
				return true;

		}else {
			
			return false;
		}
	
		
	}
	

}
