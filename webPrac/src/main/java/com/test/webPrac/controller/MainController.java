package com.test.webPrac.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.test.webPrac.service.LoginService;
import com.test.webPrac.service.NaverLoginService;
import com.test.webPrac.service.RegisterService;
import com.test.webPrac.util.SHA256Util;
import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

@Controller
public class MainController {

	static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private RegisterService registerServ;

	@Autowired
	private LoginService loginServ;

	@Autowired
	private NaverLoginService naverLoginServ;

	// Basic Part
	// -----------------------------------------------------------------

	// 한글 이름, 영문 이름, 생일, 현재 날짜 및 시간 출력 (index.jsp)
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse resp) {

		logger.info("Log Index.do");

		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		Date time = new Date();

		req.setAttribute("time", format.format(time));

		return "common/index";
	}

	// 테이블에 있는 모든 데이터를 가져와서 노출 (account.이름약자) - http://ip/account.sj
	@RequestMapping(value = "account.*.do", method = RequestMethod.GET)
	public String getInfo(HttpServletRequest req, HttpServletResponse resp) {

		logger.info("Log Index.do");

		// account URL 파싱 처리
		String url = req.getRequestURI();
		String parsedUrl = url.substring(url.indexOf("/account"), url.length());
		String nickname = parsedUrl.replace("/account.", "").replace(".do", "");

		req.setAttribute("accntNickname", nickname);
		req.setAttribute("accntInfo", registerServ.getAccountMemberInfo(nickname));
		return "common/accountInfo";
	}

	// Register
	// -------------------------------------------------------------------

	@RequestMapping(value = "register.do", method = RequestMethod.GET)
	public String register(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {

		logger.info("REGISTER");

		// RSA 키 생성
		// PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
		// if(key != null){
		// session.removeAttribute("RSAprivateKey");
		// }

		// rsa도 새로운 키가 계속 생성되고있음
		// RSAUtil rsaUtil = new RSAUtil();
		// RSA rsa = rsaUtil.creatRSA();

		// System.out.println("Modulas:" + rsa.getModulas());
		// System.out.println("Exponent:" +rsa.getExponent());

		// req.setAttribute("modulus", rsa.getModulas());
		// req.setAttribute("exponent", rsa.getExponent());
		// session.setAttribute("RSAprivateKey", rsa.getPrivatekey());

		return "regit/regit";
	}

	@RequestMapping(value = "register.idcheck.do", method = RequestMethod.POST)
	public void register_idcheck(HttpServletRequest request, HttpServletResponse response, String dupliinput)
			throws IOException {

		logger.info("REGISTER ID DUPLICATE CHECK");

		// 인코딩
		response.setCharacterEncoding("UTF-8");

		// 값 반환
		String result = "";

		// 일치하는 아이디가 1개라도 존재하면 중복
		if (registerServ.getIdCheck(dupliinput) > 0) {
			result = "duplicated";
		} else {
			result = "ok";
		}

		try {
			response.getOutputStream().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "register.nicknamecheck.do", method = RequestMethod.POST)
	public void register_nicknamecheck(HttpServletRequest request, HttpServletResponse response, String dupliinput) {

		logger.info("REGISTER NICKNAME DUPLICATE CHECK");

		// 인코딩
		response.setCharacterEncoding("UTF-8");

		// 값 반환
		String result = "";

		// 일치하는 닉네임이 1개라도 존재하면 중복
		if (registerServ.getNicknameCheck(dupliinput) > 0) {
			result = "duplicated";
		} else {
			result = "ok";
		}

		try {
			response.getOutputStream().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "register.submit.do", method = RequestMethod.POST)
	public void register_submit(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			MemberVO member) {

		logger.info("REGISTER SUBMIT");

		// 전달된 아이디 비밀번호 decoding

		// -----------------------

		// SHA-256 비밀번호 암호화
		SHA256Util sha256 = new SHA256Util();

		// 암호화에 필요한 salt값 및 암호화
		String salt = sha256.generateSalt();
		String newPwd = sha256.getEncrypt(member.getS_passwd(), salt);

		// 값 입력
		member.setSalt(salt);
		member.setS_passwd(newPwd);

		// insert 작업
		int regitResult = registerServ.insertAcctMember(member);

		// 회원가입 시 로그인 성공
		if (regitResult > 0) {
			try {
				session.setAttribute("loginStatus", member);
				response.sendRedirect("main.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "register.extraSubmit.do", method = RequestMethod.POST)
	public void register_Api_Submit(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String nickname, String phone) {

		logger.info("REGISTER API PART SUBMIT");

		MemberVO member = new MemberVO();

		member.setId((String) session.getAttribute("naverUniqId"));
		member.setName((String) session.getAttribute("naverName"));
		member.setNickname(nickname);
		member.setPhone(phone);

		int regitResult = registerServ.insertApiMember(member);

		if (regitResult > 0) {
			try {
				session.setAttribute("loginStatus", member);
				response.sendRedirect("main.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// login Part
	// -------------------------------------------------------------------

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("LOGIN");

		// Naver Login Api
		String naverAuthUrl = naverLoginServ.getAuthorizationUrl(session);
		request.setAttribute("url", naverAuthUrl);

		return "login/login";

	}

	@RequestMapping(value = "naverApiLogin.do", method = RequestMethod.GET)
	public String naverLogin(HttpServletRequest request, HttpServletResponse response, String code, String state,
			HttpSession session) throws IOException {

		logger.info("NAVER LOGIN API ");

		// 네이버 로그인 사용자 정보를 읽어온다.
		OAuth2AccessToken oauthToken = naverLoginServ.getAccessToken(session, code, state);
		loginServ.naverLoginLogic(naverLoginServ.getUserProfile(oauthToken), session);

		LoginVO loginVO = new LoginVO();
		loginVO.setId((String) session.getAttribute("naverUniqId"));
		loginVO.setLoginApi("naver");

		String loginResult = "";
		try {
			loginResult = loginServ.loginLogic(request, response, session, loginVO);
		} catch (Exception e) {
			logger.info(e.getMessage());	
		}
		request.setAttribute("navigate", loginResult);

		return "common/navigatePage";
	}

	@RequestMapping(value = "loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			LoginVO loginVO) {

		logger.info("LOGIN INFO CHECK");

		// // 이미 로그인 값이 들어가있다면 해당 값은 삭제
		response.setCharacterEncoding("UTF-8");

		if (session.getAttribute("loginStatus") != null) {
			session.removeAttribute("loginstatus");
		}

		// 없다면 로그인 로직 진행
		String loginResult = "";

		try {
			loginResult = loginServ.loginLogic(request, response, session, loginVO);

			if (loginResult.equals("main.do")) {
				request.setAttribute("navigate", loginResult);
			} else {
				request.setAttribute("navigate", loginResult);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "common/navigatePage";
	}

	@RequestMapping(value = "register.Api.do", method = RequestMethod.GET)
	public String apiRegister() {
		return "regit.regitApi";
	}

	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {

		logger.info("LOGOUT");

		// 세션에서 loginStatus 제거
		HttpSession session = request.getSession();
		session.removeAttribute("loginStatus");

		try {
			// login 화면으로 redirect
			response.sendRedirect("login.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public String main() {
		return "board/main";
	}

	@RequestMapping(value = "testTransac.do", method = RequestMethod.GET)
	public String testTransac() {
		loginServ.transactest();
		return "board/main";
	}

	@RequestMapping(value = "navigate.do", method = RequestMethod.GET)
	public String navigate(HttpServletRequest request) {
		System.out.println(request.getAttribute("navigate"));
		return "common/navigatePage";
	}

	 @RequestMapping(value = "se2.do", method = RequestMethod.GET)
	 public String se2() {
		 return "se2";
	 }
	 @RequestMapping(value = "board.write.do", method = RequestMethod.GET)
		 public String write() {
		 return "board/write";
	 }

	// @RequestMapping(value = ".do", method = RequestMethod.GET)
	// public String () {
	// return "";
	// }

}
