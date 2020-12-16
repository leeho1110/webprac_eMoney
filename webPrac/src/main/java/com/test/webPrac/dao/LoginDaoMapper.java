package com.test.webPrac.dao;

import org.springframework.stereotype.Repository;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

@Repository
public interface LoginDaoMapper {

	MemberVO selectLoginInfo(LoginVO loginVO);

	void updateLastlogin(int accnt_id);

	void insertLastloginhistory(MemberVO loginVO);

	MemberVO selectInfoOfMember(MemberVO member);

}
