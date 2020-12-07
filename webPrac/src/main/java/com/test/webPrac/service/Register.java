package com.test.webPrac.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;


public interface Register {
	
	MemberVO getAccountMemberInfo(String nickname);

	int getIdCheck(String idinput);

	int getNicknameCheck(String nickname);

	int insertAcctMember(MemberVO accnt);

	

	


}
