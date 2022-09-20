package com.project.Controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.Repository.UserRepository;
import com.project.Response.MessageResponse;
import com.project.Response.UserInformation;
import com.project.Service.PasswordService;
import com.project.Service.UserService;
import com.project.View.View;
import com.project.model.Login;
import com.project.model.Password;
import com.project.model.User;



@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private UserRepository userRepository;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/sign-up")
	public ResponseEntity<MessageResponse> signUp(@Valid @RequestBody User theUser) {
		ResponseEntity<MessageResponse> mesg = null;
		this.passwordEncoder=new BCryptPasswordEncoder();
		
		//CHECK IF EXISTED
				if (userRepository.existsByContact(theUser.getContact())) {
				      return new ResponseEntity<>(new MessageResponse("Error: Contact is already taken!"),HttpStatus.BAD_REQUEST);
				    }
				if (userRepository.existsByEmail(theUser.getEmail())) {
				      return new ResponseEntity<>(new MessageResponse("Error: Email is already taken!"),HttpStatus.BAD_REQUEST);
				    }

	    	        if (theUser.getPassword().equals(theUser.getConfirmPassword())) {
	    		        //For Encrypting password
	    		        String encodedPassword = this.passwordEncoder.encode(theUser.getPassword());
	    		        theUser.setPassword(encodedPassword);
	    		        String encodedConfirmPassword = this.passwordEncoder.encode(theUser.getConfirmPassword());
	    		        theUser.setConfirmPassword(encodedConfirmPassword);
	    		        // End For Encrypting password
	
	    		       userService.registerNewuser(theUser);
	    		        mesg =new ResponseEntity<>(new MessageResponse("User registered successfully!Welcome to Jumpee Website!"),HttpStatus.CREATED);
	    		
	    		        }
	    		        else {
	    		        	return mesg = new ResponseEntity<>(new MessageResponse("Password and Confirm Password are not match!"),HttpStatus.BAD_REQUEST);     
	           }
	    	     return mesg;

			}
	
	//Account Details of the specific id of the user
	@JsonView(View.Base.class)
	@GetMapping("/accountdetails/{id}")
    public User getUserById(@PathVariable (value ="id" ) int userId){
		
		//Optional<User> theUser = userRepository.findById(user.getId());
        return this.userService.findById(userId);
    }
	
	//Account Details of all
	@GetMapping("/All")
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	
	//My Login
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody Login theUser){
		ResponseEntity<MessageResponse> mesg = null;
		User userDetails = userRepository.findByEmail(theUser.getEmail());
		String userPass = theUser.getPassword(); //user pass
		if(userDetails !=null) {
			Long userId = userDetails.getUserId();
			boolean themagicPass = passwordEncoder.matches(userPass, userDetails.getPassword());
			if(themagicPass) {
				UserInformation.userId = userId; // user ID
				UserInformation.userUniqueEmail = theUser.getEmail();
				mesg =ResponseEntity.ok().body(new MessageResponse("You are now login"));
				//System.out.print(UserLoginInfo.userId = userId);
			}else {
				mesg =new ResponseEntity<>(new MessageResponse("Please reset your password"),HttpStatus.UNAUTHORIZED);
			}
	
		}else {
			return new ResponseEntity <>(new MessageResponse("Error: Wrong email"),HttpStatus.UNAUTHORIZED);
		}

		return mesg;
	}
	
	@PutMapping("/reset-password")
	public ResponseEntity<MessageResponse> resetPassword(@Valid @RequestBody User user ) 
	{
		ResponseEntity<MessageResponse> mesg = null;
		//Password alpha
		if (user.getPassword().equals(user.getConfirmPassword())) {
	        //For Encrypting password
	        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
	        String encodedConfirmPassword = this.passwordEncoder.encode(user.getConfirmPassword());
	        user.setConfirmPassword(encodedConfirmPassword);
	        // End For Encrypting password
		       User emailCheck = userRepository.findByEmail(user.getEmail());
		        emailCheck.setPassword(user.getPassword());
		       userService.registerNewuser(emailCheck);
	        mesg = new ResponseEntity<>(new MessageResponse("Password has been changed"),HttpStatus.CREATED);
	
	        }
        
	        else {
	        	return mesg = new ResponseEntity<>(new MessageResponse("Password and Confirm Password are not match!"),HttpStatus.BAD_REQUEST);     
	        }
		return mesg; 	        
	} 

	
	@PutMapping("/update-password")
	public ResponseEntity<?> changePass(@Valid @RequestBody Password pass ) 
	{
		Long userId = UserInformation.userId;
		if(userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			Boolean mesg = passwordService.changePass(pass);
		
			if(mesg == true) {
				return new ResponseEntity<>(new MessageResponse("Success: Password has been changed!"),HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(new MessageResponse("Password and Confirm Password should match!"),HttpStatus.BAD_REQUEST);
			}
			
			
		}

	}
}






