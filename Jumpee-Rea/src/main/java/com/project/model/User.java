package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.View.View;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long userId;
	
	@JsonView(View.Base.class)
	@NotEmpty(message = "First name is required!")
	private String firstName;
	
	@JsonView(View.Base.class)
	@NotEmpty(message = "Last name is required!")
	private String lastName;
	
	@JsonView(View.Base.class)
	@Column(unique = true)
	@Pattern(regexp =".*[@.].*[.].*",message="Wrong Email Format!")
	@NotEmpty(message = "Email is required!")
	private String email;
	
	@JsonView(View.Base.class)
	@Pattern(regexp ="^(09|\\+639)\\d{9}$",message="Wrong Contact Format!")
	@NotEmpty(message = "Contact Number is required!")
	private String contact;
	
	@Pattern(regexp =".*[A-Z].*.[0-9].*",message="Password should be alphanumeric")
	@Size(min=8, message="Password should be minimum of 8")
	@NotEmpty(message = "Password is required!")
	private String password;
	
	@Size(min=8, message="Password should be minimum of 8")
	@NotEmpty(message = "Confirm Password is required!")
	private String confirmPassword;
	
	public User() {}
	
	public User(Long userIdLong, String firstName, String lastName, String email, String contact, String password,
			String confirmPassword) {
		super();
		this.userId = userIdLong;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contact = contact;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userIdLong) {
		this.userId = userIdLong;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "User [userIdLong=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", contact=" + contact + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ "]";
	}
}
