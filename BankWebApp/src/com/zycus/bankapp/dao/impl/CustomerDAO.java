package com.zycus.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.zycus.bankWebApp.ConnectionUtil;
import com.zycus.bankapp.bo.Customer;
import com.zycus.bankapp.dao.DAO;

public class CustomerDAO implements DAO<Customer>{

	private static final String SQL_SELECT_ID_CUSTOMER = "select * from customerbank where id = ?";
	private static final String INSERT_CUSTOMER = "INSERT INTO customerbank (title, firstname, lastname, age, email, dob) values(?, ?, ?, ?,?, ?)";
	private static final String SEL_CUST = "select * from customerbank";
	private static final String GET_ID_QUERY = "select id from customerbank where email=?";
	
	@Override
	public void create(Customer object) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER);
			ps.setString(1, object.getTitle());
			ps.setString(2, object.getFirstname());
			ps.setString(3, object.getLastname());
			ps.setInt(4, object.getAge());
			ps.setString(5, object.getEmail());
			ps.setDate(6, object.getDob());
			System.out.println("Success:" +ps.executeUpdate());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}		
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customerList = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnection()){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SEL_CUST);
			while(rs.next()) {
				Customer customer = convertResult(rs);
				customerList.add(customer);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
		return customerList;
	}

	@Override
	public Customer findById(int id) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID_CUSTOMER);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Customer c = new Customer();
			if(rs.next()) {
				c.setId(rs.getInt(1));
				c.setTitle(rs.getString(2));
				c.setFirstname(rs.getString(3));
				c.setLastname(rs.getString(4));
				c.setAge(rs.getInt(5));
				c.setEmail(rs.getString(6));
				c.setDob(rs.getDate(7));
			}
			//System.out.println(c.toString());
			return c;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	private static Customer convertResult(ResultSet rs) throws SQLException{
		int id = rs.getInt(1);
		String title = rs.getString(2);
		String firstname = rs.getString(3);
		String lastname = rs.getString(4);
		int age = rs.getInt(5);
		String email = rs.getString(6);
		Date date = rs.getDate(7);
		
		return new Customer(id, title, firstname, lastname, age, email, (java.sql.Date) date);
	}
	
	public static int getIdFromEmail(String email)
	{
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(GET_ID_QUERY);
			ps.setString(1, email);
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

}
