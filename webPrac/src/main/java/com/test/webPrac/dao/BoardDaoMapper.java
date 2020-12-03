package com.test.webPrac.dao;

import java.util.ArrayList;


import org.springframework.stereotype.Repository;

import com.test.webPrac.vo.AccountVO;

@Repository
public interface BoardDaoMapper {
	
	AccountVO getAccountMemberInfo(String nickname);

	int getIdCheck(String idinput);

	int getNicknameCheck(String nicknameinput);
	
}
