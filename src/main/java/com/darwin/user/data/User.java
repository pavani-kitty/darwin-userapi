package com.darwin.user.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String department;
	private String jobTitle;

	public User() {
	}

	public User(String name, String email, String password, String phone, String department, String jobTitle) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.department = department;
		this.jobTitle = jobTitle;
	}
	
	public User(Integer id, String name, String email, String password, String phone, String department, String jobTitle) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.department = department;
		this.jobTitle = jobTitle;
	}
}
