package com.zycus.bankWebApp;

import java.sql.Date;

public class Payment {
	private int id, facid;
	private float amount;
	private Date time;
	
	
	public Payment() {
		super();
	}


	public Payment(int id, float amount, Date time) {
		super();
		this.id = id;
		this.amount = amount;
		this.time = time;
	}


	public Payment(int id, int facid, float amount, Date time) {
		this(id, amount, time);
		this.facid = facid;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getFacid() {
		return facid;
	}


	public void setFacid(int facid) {
		this.facid = facid;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}
			
}
