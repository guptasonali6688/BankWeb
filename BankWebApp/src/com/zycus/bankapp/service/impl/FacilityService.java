package com.zycus.bankapp.service.impl;

import java.util.List;

import com.zycus.bankapp.bo.Facility;
import com.zycus.bankapp.dao.impl.FacilityDAO;

public class FacilityService {

	FacilityDAO facdao = new FacilityDAO();
	
	public void createFacility(Facility facility) {
		facdao.create(facility);
	}
	
	public static List<Facility> getFacilityFromAccountNo(int accountNo) {
		return FacilityDAO.getDataFromAccNo(accountNo);
	}
}
