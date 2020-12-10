package com.test.webPrac.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class testDao {
	
	@Autowired
	private SqlSessionTemplate temp;
	
	public int insert1(){
		return temp.insert("test.insert1",1);
	}
	public int insert2(){
		return temp.insert("test.insert2",1);
	}
}
