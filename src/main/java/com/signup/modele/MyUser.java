package com.signup.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.sun.istack.NotNull;

@Entity
public class MyUser {

	@Override
	public String toString() {
		return "MyUser [username=" + username + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", role=" + role + "]";
	}

	@Id
	private String username;
	@NotNull
	private String name;
	@NotNull
	private String password;
	@NotNull
	private String email;
	@NotNull
	private String role;

	
	@JsonSetter
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getName() {
		return name;
	}
	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}





	public String getEmail() {
		return email;
	}

	@JsonSetter
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	@JsonSetter
	public void setRole(String role) {
		this.role = role;
	}
}