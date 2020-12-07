package com.test.webPrac.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

public interface Login {
	
	MemberVO loginLogic( MemberVO member, HttpServletRequest request,HttpServletResponse response, LoginVO loginVO) throws Exception;
	
	MemberVO checkLoginInfo(LoginVO loginVO);
	
	boolean checkLoginPw(LoginVO loginVO, MemberVO member );
	
	void updateLastlogin(MemberVO member);

	void insertLastloginhistory(LoginVO loginVO);
	
}	

