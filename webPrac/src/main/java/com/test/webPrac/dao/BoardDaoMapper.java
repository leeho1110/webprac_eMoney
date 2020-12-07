package com.test.webPrac.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

@Repository
public interface BoardDaoMapper {
	
	MemberVO getAccountMemberInfo(String nickname);

	int getIdCheck(String idinput);

	int getNicknameCheck(String nicknameinput);

	int insertAcctMember(MemberVO accnt);

	HashMap<String, String> getPwdandSalt(String id);

	MemberVO checkLoginInfo(LoginVO loginVO);

	void updateLastlogin(int i);

	void updateLastloginhistory(LoginVO loginVO);

	void insertTest();

	void updateTest();

}
