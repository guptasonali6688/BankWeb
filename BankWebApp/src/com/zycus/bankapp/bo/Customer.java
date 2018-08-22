package com.zycus.bankapp.bo;

import java.sql.Date;

public class Customer {
	private int id;
	private String title, firstname, lastname;
	private int age;
	private String email;
	private Date dob;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String title, String firstname, String lastname, int age, String email, Date dob) {
		super();
		this.title = title;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.email = email;
		this.dob = dob;
	}

	public Customer(int id, String title, String firstname, String lastname, int age, String email, Date dob) {
		this(title, firstname, lastname, age, email, dob);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
		
}
