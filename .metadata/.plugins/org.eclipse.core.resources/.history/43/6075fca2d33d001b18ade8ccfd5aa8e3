package com.test.webPrac.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

public interface LoginService {

	String loginLogic(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginVO loginVO);

	boolean checkLoginPw(LoginVO loginVO, MemberVO member);

	void naverLoginLogic(String userProfile, HttpSession session);
	
	void updateLoginInfoLogic(HttpServletRequest request, MemberVO member, HttpSession session);
}
