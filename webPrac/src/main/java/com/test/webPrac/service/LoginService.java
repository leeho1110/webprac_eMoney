package com.test.webPrac.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

public interface LoginService {

	String loginLogic(HttpServletRequest request, HttpServletResponse response, LoginVO loginVO) throws IOException;

	boolean checkLoginPw(LoginVO loginVO, MemberVO member);

	void transactest();

	String naverLoginLogic(String userProfile);
	
}
