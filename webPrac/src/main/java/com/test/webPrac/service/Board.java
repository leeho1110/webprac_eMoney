package com.test.webPrac.service;

import java.util.ArrayList;


import com.test.webPrac.vo.AccountVO;


public interface Board {
	
	AccountVO getAccountMemberInfo(String nickname);

	int getIdCheck(String idinput);

	int getNicknameCheck(String nickname);

	int insertAcctMember(AccountVO accnt);

}
