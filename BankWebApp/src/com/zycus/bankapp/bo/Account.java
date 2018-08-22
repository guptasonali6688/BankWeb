package com.zycus.bankapp.bo;

public class Account {

	private int id, customerId ;
	private String accountType;
	private float balance;
	private String password;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int id, int customerId, String accountType, float balance) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.accountType = accountType;
		this.balance = balance;
	}

	public Account(int id, int customerId, String accountType, float balance, String password) {
		this(id, customerId, accountType, balance);
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", accountType=" + accountType + ", balance="
				+ balance + ", password=" + password + "]";
	}	
	
	
}
