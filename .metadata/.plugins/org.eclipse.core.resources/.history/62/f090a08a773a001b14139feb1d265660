package com.test.webPrac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.webPrac.dao.RegisterDaoMapper;
import com.test.webPrac.vo.MemberVO;

@Service
public class RegisterImpl implements RegisterService {

	@Autowired
	private RegisterDaoMapper registerDaoMapper;

	@Override
	public MemberVO getAccountMemberInfo(String nickname) {
		return registerDaoMapper.getAccountMemberInfo(nickname);
	}

	@Override
	public int getIdCheck(String idinput) {
		return registerDaoMapper.getIdCheck(idinput);
	}

	@Override
	public int getNicknameCheck(String nicknameinput) {
		return registerDaoMapper.getNicknameCheck(nicknameinput);
	}

	@Override
	public int insertAcctMember(MemberVO accnt) {
		return registerDaoMapper.insertAcctMember(accnt);
	}

}
