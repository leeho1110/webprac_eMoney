package com.test.webPrac.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.webPrac.dao.BoardDaoMapper;
import com.test.webPrac.vo.AccountVO;

@Service
public class BoardImpl implements Board {
	
	@Autowired
	private BoardDaoMapper BoardMapper;
	
	@Override
	public AccountVO getAccountMemberInfo(String nickname) {
		return BoardMapper.getAccountMemberInfo(nickname);
	}

	@Override
	public int getIdCheck(String idinput) {
		return BoardMapper.getIdCheck(idinput);
	}

	@Override
	public int getNicknameCheck(String nicknameinput) {
		return BoardMapper.getNicknameCheck(nicknameinput);
	}

	@Override
	public int insertAcctMember(AccountVO accnt) {
		return BoardMapper.insertAcctMember(accnt);
	}

	@Override
	public HashMap<String, String> getPwdandSalt(String id) {
		return BoardMapper.getPwdandSalt(id);
	}
}
