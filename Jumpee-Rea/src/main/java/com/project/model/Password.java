package com.project.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Password {
	@Pattern(regexp =".*[A-Z].*.[0-9].*",message="Password should be alphanumeric")
	@Size(min=8, message="Password should be minimum of 8")
	@NotEmpty(message = "password is required!")
	private String password;

	@Size(min=8, message="Password should be minimum of 8")
	@NotEmpty(message = "confirm password is required!")
	private String confirmPassword;
	
	
	public Password() {
	}
	
	public Password(String password, String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	public void setCpnfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "Password [password=" + password + ", cpnfirmPassword=" + confirmPassword + "]";
	}
	
	
}
