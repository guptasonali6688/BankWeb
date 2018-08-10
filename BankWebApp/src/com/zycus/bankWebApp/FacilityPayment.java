package com.zycus.bankWebApp;

public class FacilityPayment {
	private int id, accountNo;
	private String billType, provider;
	private float amount;
	private boolean status;
	private String timestamp;
	
	public FacilityPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public FacilityPayment(String billType, String provider) {
		super();
		this.billType = billType;
		this.provider = provider;
		
	}

	public FacilityPayment(int accountNo, String billType, String provider) {
		this(billType, provider);
		this.accountNo = accountNo;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


}
