package com.test.webPrac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.webPrac.dao.BoardDaoMapper;
import com.test.webPrac.vo.MemberVO;

@Service
public class RegisterImpl implements Register {

	@Autowired
	private BoardDaoMapper BoardMapper;

	@Override
	public MemberVO getAccountMemberInfo(String nickname) {
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
	public int insertAcctMember(MemberVO accnt) {
		return BoardMapper.insertAcctMember(accnt);
	}

}
