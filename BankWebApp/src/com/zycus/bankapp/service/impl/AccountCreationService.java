package com.zycus.bankapp.service.impl;

import com.zycus.bankapp.bo.Account;
import com.zycus.bankapp.dao.impl.AccountDAO;

public class AccountCreationService {	
	AccountDAO accDAO = new AccountDAO();	
	
	public void createAccount(Account account) {
		accDAO.create(account);
	}
	   
	public Account findByAccountId(int accounId) {
		return accDAO.findById(accounId);
	}
	
	public int getAccountIdByCustId(int customerId) {
		return AccountDAO.getIdFromCustID(customerId);
	}
	
	public void updateAccountBalance(float finalBalance, int accountId) {
		accDAO.updateBalance(finalBalance,accountId);
	}
}
