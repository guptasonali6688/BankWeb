package com.zycus.bankapp.bo;

import java.sql.Date;

public class Payment {
	private int id, facid;
	private float amount;
	private Date time;
	
	
	public Payment() {
		super();
	}


	public Payment(int facid, float amount, Date time) {
		super();
		this.facid = facid;
		this.amount = amount;
		this.time = time;
	}

	public Payment(int id, int facid, float amount, Date time) {
		this(facid, amount, time);
		this.id = id;
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


	@Override
	public String toString() {
		return "Payment [id=" + id + ", facid=" + facid + ", amount=" + amount + ", time=" + time + "]";
	}
	
	
			
}
