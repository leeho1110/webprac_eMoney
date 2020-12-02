package com.test.webPrac.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.SessionScope;

import com.test.webPrac.service.Board;
import com.test.webPrac.util.RSA;
import com.test.webPrac.util.RSAUtil;
import com.test.webPrac.vo.AccountVO;

import net.sf.json.JSONObject;

@Controller
public class MainController {

	@Autowired
	private Board bServ;
	
	RSAUtil rsaUtil;

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
		PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
		if(key != null){
			session.removeAttribute("RSAprivateKey");
		}
		RSA rsa = rsaUtil.creatRSA();
		req.setAttribute("modulus", rsa.getModulas());
		req.setAttribute("exponent", rsa.getExponent());
		session.setAttribute("RSAprivateKey", rsa.getPrivatekey());
		
		return "regit";
	}

	@RequestMapping(value = "register.idcheck.do", method = RequestMethod.POST)
	public void register_idcheck(HttpServletRequest req, HttpServletResponse resp, String idinput) {
		
		// 인코딩
		resp.setCharacterEncoding("UTF-8");
		
		// 값 반환
		try {
			PrintWriter writer = resp.getWriter();
			
			// 일치하는 아이디가 1개라도 존재하면 중복
			if (bServ.getIdCheck(idinput) > 0) {
				writer.write("duplicated");
			} else {
				writer.write("ok");				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest req, HttpServletResponse resp, String id, String password){
		
		return "login";
	}
	
	@RequestMapping(value = "register.submit.do", method = RequestMethod.POST)
	public void register_submit(HttpServletRequest req, HttpServletResponse resp, AccountVO accnt){
		
		System.out.println(accnt.getAccnt_id());
		System.out.println(accnt.getId());
		System.out.println(accnt.getName());
		System.out.println(accnt.getNickname());
		System.out.println(accnt.getPhone());
		System.out.println(accnt.getS_passwd());
		System.out.println(accnt.getUser_type());
	}
}
