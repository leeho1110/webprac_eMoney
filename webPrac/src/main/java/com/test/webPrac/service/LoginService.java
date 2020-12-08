package com.test.webPrac.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

public interface LoginService {

	MemberVO loginLogic(MemberVO member, HttpServletRequest request, HttpServletResponse response, LoginVO loginVO) throws IOException;

	boolean checkLoginPw(LoginVO loginVO, MemberVO member);

	void transactest();

}
