package com.project.model;

import javax.validation.constraints.NotEmpty;

public class Login {
	
	@NotEmpty(message = "Email is required!")
	private String email;
	@NotEmpty(message = "Password is required!")
	private String password;
	
	public Login() {
	}
	
	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

}
