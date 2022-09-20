package com.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.AddressRepository;
import com.project.Response.UserInformation;
import com.project.model.Address;


@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public Address addUserAddress(Address theAddress){
		Long userId = UserInformation.userId;
		theAddress.setAddress(theAddress.getAddress());
		theAddress.setContact(theAddress.getContact());
		theAddress.setContactPerson(theAddress.getContactPerson());
		theAddress.setUserId(userId);
		return addressRepository.save(theAddress);
		
	}
	 
	public Address updateUserAddress(Address theAddress){
		Long userId = UserInformation.userId;
		Address userDetails=addressRepository.findByUserId(userId);
		userDetails.setAddress(theAddress.getAddress());
		userDetails.setContact(theAddress.getContact());
		userDetails.setContactPerson(theAddress.getContactPerson());
		return addressRepository.save(userDetails);
		
	}

	public Address showHistory(Address theAddress) {
			Long userId = UserInformation.userId;
			Address userDetails=addressRepository.findByUserId(userId);
			return  userDetails;
	}
	 
	
}
