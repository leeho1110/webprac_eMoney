package com.test.webPrac.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.test.webPrac.service.LoginService;
import com.test.webPrac.service.RegisterService;
import com.test.webPrac.util.SHA256Util;
import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;
import com.test.webPrac.vo.NaverLoginBO;

@Controller
public class MainController {

	@Autowired
	private RegisterService registerServ;

	@Autowired
	private LoginService loginServ;

	// Naver Login
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	// Basic Part
	// -----------------------------------------------------------------

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
		String url = req.getRequestURI();
		String parsedUrl = url.substring(url.indexOf("/account"), url.length());
		String nickname = parsedUrl.replace("/account.", "").replace(".do", "");

		req.setAttribute("accntNickname", nickname);
		req.setAttribute("accntInfo", registerServ.getAccountMemberInfo(nickname));
		return "accountInfo";
	}

	// Register
	// -------------------------------------------------------------------

	// 회원가입
	// 테이블에 있는 모든 데이터를 가져와서 노출 (account.이름약자) - http://ip/account.sj
	@RequestMapping(value = "register.do", method = RequestMethod.GET)
	public String register(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {

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
			if (registerServ.getIdCheck(dupliinput) > 0) {
				writer.write("duplicated");
			} else {
				writer.write("ok");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "register.nicknamecheck.do", method = RequestMethod.POST)
	public void register_nicknamecheck(HttpServletRequest req, HttpServletResponse resp, String dupliinput) {

		// 인코딩
		resp.setCharacterEncoding("UTF-8");

		// 값 반환
		try {
			PrintWriter writer = resp.getWriter();

			// 일치하는 닉네임이 1개라도 존재하면 중복
			if (registerServ.getNicknameCheck(dupliinput) > 0) {
				writer.write("duplicated");
			} else {
				writer.write("ok");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "register.submit.do", method = RequestMethod.POST)
	public void register_submit(HttpServletRequest req, HttpServletResponse resp, MemberVO member) {

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

				resp.sendRedirect("main.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// login Part
	// -------------------------------------------------------------------

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		// System.out.println("======== Cookies Info =========");
		// if(request.getCookies() != null){
		// for(Cookie c : request.getCookies()){
		// System.out.println(c.getName());
		// System.out.println(c.getValue());
		// System.out.println(c.getMaxAge());
		// }
		// }
		// System.out.println("==========================");

		// Naver Login Api
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		request.setAttribute("url", naverAuthUrl);

		return "login";

	}

	@RequestMapping(value = "naverCallback.do", method = RequestMethod.GET)
	public String naverLogin(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {

		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);

		return "callback";
	}

	@RequestMapping(value = "loginCheck.do", method = RequestMethod.POST)
	public void loginCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			LoginVO loginVO) {

		// // 이미 로그인 값이 들어가있다면 해당 값은 삭제
		response.setCharacterEncoding("UTF-8");

		if (session.getAttribute("loginStatus") != null) {
			session.removeAttribute("loginstatus");
		}

		// 사용자가 로그인한 값과 일치하는지 확인
		MemberVO member = null;

		try {
			member = loginServ.loginLogic(member, request, response, loginVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {

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
		return "main";
	}

	@RequestMapping(value = "testTransac.do", method = RequestMethod.GET)
	public String testTransac() {
		loginServ.transactest();
		return "main";
	}

}
