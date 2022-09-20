package com.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.Repository.AddressRepository;
import com.project.Response.MessageResponse;
import com.project.Response.UserInformation;
import com.project.Service.AddressService;
import com.project.model.Address;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	AddressRepository addressRepository;
	
	@PostMapping("/save-address")
	public ResponseEntity<?> addNewAddress(@RequestBody Address theAddress){
		if(UserInformation.userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			addressService.addUserAddress(theAddress);
			return new ResponseEntity<>(new MessageResponse("Success: Address has been added!"),HttpStatus.CREATED);
		}

	}
	
	@PutMapping("/update-address")
	public ResponseEntity<?> updateUserAddress(@RequestBody Address theAddress){
		Long userId = UserInformation.userId;
		if(userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			;
			addressService.updateUserAddress(theAddress);
			return new ResponseEntity<>(new MessageResponse("Success: Address has been updated!"),HttpStatus.CREATED);
		}

	}
	//Account Details of all in address
	@GetMapping("/all-address")
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
		
		@GetMapping("/show-address")
		public ResponseEntity<?> showAddress( Address theAddress){
			if(UserInformation.userId==null) {
				return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
			}else {
				Address add= addressService.showHistory(theAddress);
				return  ResponseEntity.ok(add);

			}
	
	}
}
