package com.zycus.bankWebApp;

public class Facility {
	private int id, accountNo;
	private String billType, provider;
	private int consumerNo;
	
	
	public Facility() {
		super();
	}

	public Facility(int id, String billType, String provider, int consumerNo) {
		super();
		this.id = id;
		this.billType = billType;
		this.provider = provider;
		this.consumerNo = consumerNo;
	}

	public Facility(int id, int accountNo, String billType, String provider, int consumerNo) {
		this(id, billType, provider, consumerNo);
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

	public int getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(int consumerNo) {
		this.consumerNo = consumerNo;
	}
	
	
}
