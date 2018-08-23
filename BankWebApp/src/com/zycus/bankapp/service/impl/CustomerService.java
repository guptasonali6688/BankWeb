package com.zycus.bankapp.service.impl;

import com.zycus.bankapp.bo.Customer;
import com.zycus.bankapp.dao.impl.CustomerDAO;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();
	
	public void createNewUser(Customer customer) {
		customerDAO.create(customer);
	}
	
	public Customer findByCustomerId(int customerId) {
		return customerDAO.findById(customerId);
	}
	
	
}
