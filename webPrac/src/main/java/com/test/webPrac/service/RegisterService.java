package com.test.webPrac.service;

import com.test.webPrac.vo.MemberVO;


public interface RegisterService {
	
	MemberVO getAccountMemberInfo(String nickname);

	int getIdCheck(String idinput);

	int getNicknameCheck(String nickname);

	int insertAcctMember(MemberVO accnt);

	

	


}
