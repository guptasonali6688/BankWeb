package com.zycus.bankapp.service.impl;

import java.util.List;

import com.zycus.bankapp.bo.Payment;
import com.zycus.bankapp.dao.impl.PaymentDAO;

public class PaymentService {
	
	PaymentDAO paydao = new PaymentDAO();
	
	public void createPayment(Payment payment) {
		paydao.create(payment);
	}
	
	public static List<Payment> getDataFromFacilityId(int facid) {
		return PaymentDAO.getDataFromFacId(facid);
	}
}
