package com.zycus.bankWebApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacilityPaymentDAO implements DAO<FacilityPayment> {


	private static final String SQL_SELECT_ID_FAC_PAY = "select * from facility_payment where id = ?";
	private static final String INSERT_FAC_PAY = "INSERT INTO facility_payment (accountNo, billType, provider) values(?, ?, ?)";
	private static final String SEL_FAC_PAY = "select * from facility_payment";
	private static final String GET_DATA_FROM_ACCNO = "select billType,provider from facility_payment where accountNo = ?";
	
	@Override
	public void create(FacilityPayment object) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(INSERT_FAC_PAY);
			ps.setInt(1, object.getAccountNo());
			ps.setString(2, object.getBillType());
			ps.setString(3, object.getProvider());
			System.out.println("Success:" +ps.executeUpdate());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}				
	}

	@Override
	public List<FacilityPayment> findAll() {
	
		return null;
	}

	@Override
	public FacilityPayment findById(int i) {

		return null;
	}
	
	public static List<FacilityPayment> getDataFromAccNo(int accNo) {
		List<FacilityPayment> facpaylist = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(GET_DATA_FROM_ACCNO);
			ps.setInt(1, accNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				facpaylist.add(convertResult(rs));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
		return facpaylist;
	}
	
	private static FacilityPayment convertResult(ResultSet rs) throws SQLException{
		String billType = rs.getString(1);
		String provider = rs.getString(2);
		return new FacilityPayment(billType, provider);
	}

}
