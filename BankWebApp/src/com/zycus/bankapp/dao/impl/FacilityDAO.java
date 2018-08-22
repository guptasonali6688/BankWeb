package com.zycus.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zycus.bankWebApp.ConnectionUtil;
import com.zycus.bankapp.bo.Facility;
import com.zycus.bankapp.dao.DAO;

public class FacilityDAO implements DAO<Facility> {

	private static final String GET_FAC_DATA_BY_ID = "select * from facility where id = ?";
	private static final String INSERT_FAC = "INSERT INTO facility (accountId, billType, provider, consumerId) values(?, ?, ?, ?)";
	private static final String SEL_FAC = "select * from facility";
	private static final String GET_DATA_FROM_ACCNO = "select id,accountId,billType,provider,consumerId from facility where accountId = ?";
	
	@Override
	public void create(Facility object) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(INSERT_FAC);
			ps.setInt(1, object.getAccountNo());
			ps.setString(2, object.getBillType());
			ps.setString(3, object.getProvider());
			ps.setInt(4, object.getConsumerNo());
			System.out.println("Success:" +ps.executeUpdate());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}		
	}

	@Override
	public List<Facility> findAll() {
		List<Facility> facilityList = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnection()){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SEL_FAC);
			while(rs.next()) {
				Facility facility = convertResult(rs);
				facilityList.add(facility);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
		return facilityList;
	}
	
	private static Facility convertResult(ResultSet rs) throws SQLException{
		int id = rs.getInt("id");
		int accNo = rs.getInt("accountId");
		String billType = rs.getString("billType");
		String provider = rs.getString("provider");
		int consumerNo = rs.getInt("consumerId");
		return new Facility(id,accNo, billType, provider, consumerNo);
	}
	
	@Override
	public Facility findById(int id) {
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(GET_FAC_DATA_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Facility fac = new Facility();
			if(rs.next()) {
				fac.setId(rs.getInt(1));
				fac.setAccountNo(rs.getInt(2));
				fac.setBillType(rs.getString(3));
				fac.setProvider(rs.getString(4));
				fac.setConsumerNo(rs.getInt(5));
			}

			return fac;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	public static List<Facility> getDataFromAccNo(int accNo) {
		System.out.println(accNo);
		List<Facility> faclist = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(GET_DATA_FROM_ACCNO);
			ps.setInt(1, accNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				faclist.add(convertResult(rs));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
		return faclist;
	}
	

}
