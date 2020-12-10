package com.test.webPrac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.webPrac.dao.testDao;

@Service
public class TransacTestService {

	@Autowired
	private testDao dao;
		
	@Transactional
	public int transacTest() throws Exception {
		
		int result = 0;
		
		result += dao.insert1();
		result += dao.insert1();
		result += dao.insert1();
		result += dao.insert2();
		
		return result;
	}
	
	
}
