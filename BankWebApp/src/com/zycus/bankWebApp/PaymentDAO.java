package com.zycus.bankWebApp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements DAO<Payment> {

	private static final String GET_PAY_DATA_BY_ID = "select * from payment where id = ?";
	private static final String INSERT_PAY = "INSERT INTO payment (facilityId, amount, time ) values(?, ?, ?)";
	private static final String SEL_PAY = "select * from payment";
	
	@Override
	public void create(Payment object) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(INSERT_PAY);
			ps.setInt(1, object.getFacid());
			ps.setFloat(2, object.getAmount());
			ps.setDate(3, object.getTime());
			System.out.println("Success:" +ps.executeUpdate());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}		
	}

	@Override
	public List<Payment> findAll() {	
		List<Payment> payList = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnection()){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SEL_PAY);
			while(rs.next()) {
				Payment payment = convertResult(rs);
				payList.add(payment);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
		return payList;
	}
	
	private static Payment convertResult(ResultSet rs) throws SQLException{
		int id = rs.getInt("id");
		float amount = rs.getFloat("amount");
		Date time = rs.getDate("time");
		return new Payment(id, amount, time);
	}
	
	@Override
	public Payment findById(int id) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(GET_PAY_DATA_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Payment pay = new Payment();
			if(rs.next()) {
				pay.setId(rs.getInt(1));
				pay.setFacid(rs.getInt(2));
				pay.setAmount(rs.getFloat(3));
				pay.setTime(rs.getDate(4));
			}

			return pay;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
	}

}
