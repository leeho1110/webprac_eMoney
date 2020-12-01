package com.test.webPrac.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.webPrac.service.Board;

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

}
