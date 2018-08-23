package com.zycus.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zycus.bankapp.bo.Account;
import com.zycus.bankapp.conn.ConnectionUtil;
import com.zycus.bankapp.dao.DAO;

public class AccountDAO implements DAO<Account> {

	private static final String SQL_SELECT_ID_ACCOUNT = "select * from account where id = ?";
	private static final String INSERT_ACCOUNT = "INSERT INTO account (customerId, accountType, balance, password) values(?, ?, ?, ?)";
	private static final String SEL_ACCOUNT = "select * from account";
	private static final String GET_ID = "select id from account where customerId = ?";
	private static final String UPDATE_AMT = "update account set balance = ? where id = ?";
	
	@Override
	public void create(Account object) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(INSERT_ACCOUNT);
			ps.setInt(1, object.getCustomerId());
			ps.setString(2, object.getAccountType());
			ps.setFloat(3, object.getBalance());
			ps.setString(4, object.getPassword());
			System.out.println("Success:" +ps.executeUpdate());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}			
	}
	@Override
	public List<Account> findAll() {	
		List<Account> accountList = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnection()){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SEL_ACCOUNT);
			while(rs.next()) {
				Account customer = convertResult(rs);
				accountList.add(customer);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
		return accountList;
	}
	@Override
	public Account findById(int id) {
		
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID_ACCOUNT);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Account c = new Account();
			if(rs.next()) {
				c.setId(rs.getInt(1));
				c.setCustomerId(rs.getInt(2));
				c.setAccountType(rs.getString(3));
				c.setBalance(rs.getFloat(4));
				c.setPassword(rs.getString(5));
			}

			return c;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	private static Account convertResult(ResultSet rs) throws SQLException{
		int id = rs.getInt(1);
		int customerId = rs.getInt(3);
		String accountType = rs.getString(3);
		Float balance = rs.getFloat(4);	
		String password = rs.getString(5);
		return new Account(id, customerId, accountType, balance, password);
	}
	
	public static int getIdFromCustID(int customerId) {

		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(GET_ID);
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				return rs.getInt("id");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}	
		return -1;
		
	}

	public void updateBalance(float balance, int accNo) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(UPDATE_AMT);
			System.out.println(balance+"  "+accNo);
			ps.setInt(2, accNo);
			ps.setFloat(1, balance);		
			ps.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}	
	}

}
