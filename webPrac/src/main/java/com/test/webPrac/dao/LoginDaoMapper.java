package com.test.webPrac.dao;

import org.springframework.stereotype.Repository;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

@Repository
public interface LoginDaoMapper {

	MemberVO checkLoginInfo(LoginVO loginVO);

	void updateLastlogin(int accnt_id);

	void insertLastloginhistory(MemberVO loginVO);

	void insert(int i);

	void insert2(int i);

	void insert1(int i);

}
