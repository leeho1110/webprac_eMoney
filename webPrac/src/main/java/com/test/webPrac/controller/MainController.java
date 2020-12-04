package com.test.webPrac.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.webPrac.service.Board;
import com.test.webPrac.util.SHA256Util;
import com.test.webPrac.vo.AccountVO;


@Controller
public class MainController {
	
	@Autowired
	private Board bServ;
	
	// 한글 이름, 영문 이름, 생일, 현재 날짜 및 시간 출력 (index.jsp)
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse resp) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		Date time = new Date();

		req.setAttribute("time", format.format(time));

		return "index";
	}

	// 테이블에 있는 모든 데이터를 가져와서 노출 (account.이름약자) - http://ip/account.sj
	@RequestMapping(value = "account.*.do", method = RequestMethod.GET)
	public String getInfo(HttpServletRequest req, HttpServletResponse resp) {

		// account URL 파싱 처리
		String nickname = req.getRequestURI().replace("/account.", "").replace(".do", "");
		req.setAttribute("accntNickname", nickname);
		req.setAttribute("accntInfo", bServ.getAccountMemberInfo(nickname));
		return "accountInfo";
	}

	// 회원가입
	// 테이블에 있는 모든 데이터를 가져와서 노출 (account.이름약자) - http://ip/account.sj
	@RequestMapping(value = "register.do", method = RequestMethod.GET)
	public String register(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		
		// RSA  키 생성
//		PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
//		if(key != null){
//			session.removeAttribute("RSAprivateKey");
//		}
		
		// rsa도 새로운 키가 계속 생성되고있음
//		RSAUtil rsaUtil = new RSAUtil();
//		RSA rsa = rsaUtil.creatRSA();
		
//		System.out.println("Modulas:" + rsa.getModulas());
//		System.out.println("Exponent:" +rsa.getExponent());
		
//		req.setAttribute("modulus", rsa.getModulas());
//		req.setAttribute("exponent", rsa.getExponent());
//		session.setAttribute("RSAprivateKey", rsa.getPrivatekey());
		
		
		
		return "regit";
	}

	@RequestMapping(value = "register.idcheck.do", method = RequestMethod.POST)
	public void register_idcheck(HttpServletRequest req, HttpServletResponse resp, String dupliinput) {
		
		// 인코딩
		resp.setCharacterEncoding("UTF-8");
		
		// 값 반환
		try {
			PrintWriter writer = resp.getWriter();
			
			// 일치하는 아이디가 1개라도 존재하면 중복
			if (bServ.getIdCheck(dupliinput) > 0) {
				writer.write("duplicated");
			} else {
				writer.write("ok");				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "register.nicknamecheck.do", method = RequestMethod.POST)
	public void register_nicknamecheck(HttpServletRequest req, HttpServletResponse resp, String dupliinput){
		
		// 인코딩
		resp.setCharacterEncoding("UTF-8");
		
		// 값 반환
		try {
			PrintWriter writer = resp.getWriter();
			
			// 일치하는 닉네임이 1개라도 존재하면 중복
			if (bServ.getNicknameCheck(dupliinput) > 0) {
				writer.write("duplicated");
			} else {
				writer.write("ok");				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "register.submit.do", method = RequestMethod.POST)
	public void register_submit(HttpServletRequest req, HttpServletResponse resp, AccountVO accnt){
		
		// 전달된 아이디 비밀번호 decoding 
		
		// -----------------------
		
		// SHA-256 비밀번호 암호화
		SHA256Util sha256 = new SHA256Util();
		
		// 암호화에 필요한  salt값 및 암호화
		String salt = sha256.generateSalt();
		String newPwd = sha256.getEncrypt(accnt.getS_passwd(), salt);
		
		// 값 입력 
		accnt.setSalt(salt);
		accnt.setS_passwd(newPwd);
		
		// insert 작업
		int regitResult = bServ.insertAcctMember(accnt);
		
		// 회원가입 시 로그인 성공
		if(regitResult > 0){
			
		}
	}
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest req, HttpServletResponse resp, String id, String password){
		System.out.println("login");
		
		return "login";
		
	}
	
	@RequestMapping(value = "loginCheck.do", method = RequestMethod.GET)
	public String loginCheck(HttpServletRequest req, HttpServletResponse resp, String id, String password){
		
		String returnURL = "index.do";
		
		return returnURL;
	}
}
