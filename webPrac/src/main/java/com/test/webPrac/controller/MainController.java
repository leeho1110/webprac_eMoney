package com.test.webPrac.controller;

import java.io.IOException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.test.webPrac.service.BoardService;
import com.test.webPrac.service.LoginService;
import com.test.webPrac.service.NaverLoginService;
import com.test.webPrac.service.RegisterService;
import com.test.webPrac.util.RSA;
import com.test.webPrac.util.RSAUtil;
import com.test.webPrac.util.SHA256Util;
import com.test.webPrac.vo.BoardVO;
import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;
import com.test.webPrac.vo.PagingVO;

@Controller
public class MainController {

	static final Logger logger = LoggerFactory.getLogger("emoney Web Practice");

	@Autowired
	private RegisterService registerServ;

	@Autowired
	private LoginService loginServ;

	@Autowired
	private NaverLoginService naverLoginServ;

	@Autowired
	private BoardService boardServ;

	// BASIC
	// 한글 이름, 영문 이름, 생일, 현재 날짜 및 시간 출력 (index.jsp)
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {

		logger.info("Log Index.do");

		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		Date time = new Date();

		request.setAttribute("time", format.format(time));

		return "common/index";
	}

	// 테이블에 있는 모든 데이터를 가져와서 노출 (account.이름약자) - http://ip/account.sj
	@RequestMapping(value = "account.*.do", method = RequestMethod.GET)
	public String getInfo(HttpServletRequest request, HttpServletResponse response) {

		logger.info("Log Index.do");

		// account URL 파싱 처리
		String url = request.getRequestURI();
		String parsedUrl = url.substring(url.indexOf("/account"), url.length());
		String nickname = parsedUrl.replace("/account.", "").replace(".do", "");

		request.setAttribute("accntNickname", nickname);
		request.setAttribute("accntInfo", registerServ.selectIdCheck(nickname));

		return "common/accountInfo";
	}

	// REGISTER
	@RequestMapping(value = "register.do", method = RequestMethod.GET)
	public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("REGISTER");

		if (session.getAttribute("loginStatus") != null) {
			try {
				response.sendRedirect("main.do");
			} catch (Exception e) {
			}
		}

		// 기존 키 제거
		PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
		if (key != null) {
			session.removeAttribute("RSAprivateKey");
		}

		// RSA 개인키 세션 저장 후 공개키 클라이언트에 전달
		RSAUtil rsaUtil = new RSAUtil();
		RSA rsa = rsaUtil.creatRSA();
		session.setAttribute("RSAprivateKey", rsa.getPrivatekey());

		request.setAttribute("modulus", rsa.getModulas());
		request.setAttribute("exponent", rsa.getExponent());

		return "regit/regit";
	}

	@RequestMapping(value = "register.idcheck.do", method = RequestMethod.POST)
	public void register_idcheck(HttpServletRequest request, HttpServletResponse response, String dupliinput) {

		logger.info("REGISTER ID DUPLICATE CHECK");

		response.setCharacterEncoding("UTF-8");

		String result = "";
		if (registerServ.selectIdCheck(dupliinput) > 0) {
			result = "duplicated";
		} else {
			result = "pass";
		}

		// Result
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

		String result = "";
		if (registerServ.selectNicknameCheck(dupliinput) > 0) {
			result = "duplicated";
		} else {
			result = "pass";
		}

		// Result
		try {
			response.getOutputStream().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "register.submit.do", method = RequestMethod.POST)
	public String register_submit(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			MemberVO memberVO) {

		logger.info("REGISTER SUBMIT");

		PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");

		// 비정상적 접근 처리
		if (key == null) {
			request.setAttribute("navigate", "rsaError");
			return "common/navigatePage";
		}

		session.removeAttribute("RSAprivateKey");

		RSAUtil rsaUtil = new RSAUtil();
		String decrypedid = "";
		String decrypedPw = "";

		try {
			decrypedid = rsaUtil.getDecryptText(key, memberVO.getId());
			decrypedPw = rsaUtil.getDecryptText(key, memberVO.getS_passwd());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 암호화에 필요한 salt값 및 암호화
		SHA256Util sha256 = new SHA256Util();
		String salt = sha256.generateSalt();
		String newPwd = sha256.getEncrypt(decrypedPw, salt);

		memberVO.setId(decrypedid);
		memberVO.setSalt(salt);
		memberVO.setS_passwd(newPwd);

		// insert
		int regitResult = registerServ.insertAcctMember(memberVO);

		// 회원가입 성공
		if (regitResult > 0) {
			session.setAttribute("loginStatus", memberVO);
			request.setAttribute("navigate", "main.do");
			return "common/navigatePage";
		} else {
			request.setAttribute("navigate", "registerError");
			return "common/navigatePage";
		}
	}

	@RequestMapping(value = "register.extraSubmit.do", method = RequestMethod.POST)
	public String register_Api_Submit(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String nickname, String phone) {

		logger.info("REGISTER API PART SUBMIT");

		MemberVO member = new MemberVO();

		// 세션에 저장된 네이버 교유 ID번호 및 이름 가져옴
		member.setId((String) session.getAttribute("naverUniqId"));
		member.setName((String) session.getAttribute("naverName"));
		member.setNickname(nickname);
		member.setPhone(phone);

		// insert
		int regitResult = registerServ.insertApiMember(member);

		// 추가 회원가입 성공
		if (regitResult > 0) {
			session.setAttribute("loginStatus", member);
			request.setAttribute("navigate", "main.do");
			return "common/navigatePage";
		} else {
			request.setAttribute("navigate", "registerApiError");
			return "common/navigatePage";
		}
	}

	// LOGIN
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("LOGIN");

		if (session.getAttribute("loginStatus") != null) {
			try {
				response.sendRedirect("main.do");
			} catch (Exception e) {
			}
		}

		// 세션에서 RSA 개인키 가져옴
		PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");

		// 비정상적인 접근 처리
		if (key != null) {
			session.removeAttribute("RSAprivateKey");
		}

		// RSA 개인키 세션 저장 후 공개키 클라이언트에 전달
		RSAUtil rsaUtil = new RSAUtil();
		RSA rsa = rsaUtil.creatRSA();
		session.setAttribute("RSAprivateKey", rsa.getPrivatekey());

		request.setAttribute("modulus", rsa.getModulas());
		request.setAttribute("exponent", rsa.getExponent());

		// Naver Login Api
		String naverAuthUrl = naverLoginServ.getAuthorizationUrl(session);
		request.setAttribute("url", naverAuthUrl);

		return "login/login";

	}

	@RequestMapping(value = "naverApiLogin.do", method = RequestMethod.GET)
	public String naverLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, String code, String state, String error) {

		logger.info("NAVER LOGIN API ");

		if (error == null) {
			// 네이버 로그인 사용자 정보를 읽어온다
			OAuth2AccessToken oauthToken;
			try {
				oauthToken = naverLoginServ.getAccessToken(session, code, state);
				loginServ.naverLoginLogic(naverLoginServ.getUserProfile(oauthToken), session);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			LoginVO loginVO = new LoginVO();
			loginVO.setId((String) session.getAttribute("naverUniqId"));
			loginVO.setLoginApi("naver");

			String loginResult = "";
			try {
				loginResult = loginServ.loginLogic(request, response, session, loginVO);

			} catch (Exception e) {
				request.setAttribute("navigate", e.getClass());
				e.printStackTrace();
			}
			request.setAttribute("navigate", loginResult);

			return "common/navigatePage";
		} else {
			request.setAttribute("navigate", "naverLoginCancel");
			return "common/navigatePage";
		}
	}

	@RequestMapping(value = "login.submit.do", method = RequestMethod.POST)
	public String loginCheck(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			LoginVO loginVO) {

		logger.info("LOGIN INFO CHECK");
		logger.info(loginVO.toString());

		// // 이미 로그인 값이 들어가있다면 해당 값은 삭제
		response.setCharacterEncoding("UTF-8");

		if (session.getAttribute("loginStatus") != null) {
			session.removeAttribute("loginstatus");
		}

		// 전달된 아이디 비밀번호 decoding
		PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");

		// 비정상적인 접근 처리
		if (key == null) {
			request.setAttribute("navigate", "rsaError");
			return "common/navigatePage";
		}

		// 개인키 제거
		session.removeAttribute("RSAprivateKey");

		// 복호화
		RSAUtil rsaUtil = new RSAUtil();
		String decrypedid = "";
		String decrypedPw = "";

		try {
			decrypedid = rsaUtil.getDecryptText(key, loginVO.getId());
			decrypedPw = rsaUtil.getDecryptText(key, loginVO.getPw());

			loginVO.setId(decrypedid);
			loginVO.setPw(decrypedPw);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 없다면 로그인 로직 진행
		String loginResult = "";

		try {
			loginResult = loginServ.loginLogic(request, response, session, loginVO);
			Cookie c = new Cookie("login", "test");
			response.addCookie(c);
			if (loginResult.equals("main.do")) {
				request.setAttribute("navigate", loginResult);
			} else {
				request.setAttribute("navigate", loginResult);
			}

		} catch (Exception e) {
			request.setAttribute("navigate", e.getClass());
			e.printStackTrace();
		}

		return "common/navigatePage";
	}

	@RequestMapping(value = "register.Api.do", method = RequestMethod.GET)
	public String apiRegister() {

		logger.info("NAVER LOGIN EXTRA REGISTER");

		return "regit/regitApi";
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

	// MAIN
	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response, PagingVO pagingVO) {

		logger.info("MAIN LOADING");

		// 페이징 최초 접속 처리
		if (pagingVO.getNowPage() == 0 && pagingVO.getCntPerPage() == 0) {
			pagingVO.setNowPage(1);
			pagingVO.setCntPerPage(5);
		} else if (pagingVO.getNowPage() == 0) {
			pagingVO.setNowPage(1);
		} else if (pagingVO.getCntPerPage() == 0) {
			pagingVO.setCntPerPage(5);
		}

		pagingVO.setPagingInfo(boardServ.selectTotalBoardCnt());
		List<BoardVO> boardList = boardServ.selectBoardList(pagingVO);

		request.setAttribute("paging", pagingVO);
		request.setAttribute("boardList", boardList);

		return "board/main";
	}

	@RequestMapping(value = "navigate.do", method = RequestMethod.GET)
	public String navigate(HttpServletRequest request) {

		logger.info("NAVIGATING...");

		return "common/navigatePage";
	}

	// BOARD
	@RequestMapping(value = "board.write.do", method = RequestMethod.GET)
	public String write() {

		logger.info("DO WRITE");

		return "board/write";
	}

	@RequestMapping(value = "board.write.uploadFile.do", method = RequestMethod.POST)
	public void write_multiImg(HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("UPLOAD FILE");

		String fileInfo = boardServ.fileUpload(request);

		response.getOutputStream().print(fileInfo);
	}

	@RequestMapping(value = "board.write.submit.do", method = RequestMethod.POST)
	public String write_submit(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			BoardVO boardVO) {

		logger.info("WRITING SUBMIT");
		System.out.println(boardVO.toString());
		int result = boardServ.insertPost(boardVO, session);

		if (result > 0) {
			request.setAttribute("navigate", "main.do");
			return "common/navigatePage";
		} else {
			request.setAttribute("navigate", "writeError");
			return "common/navigatePage";
		}
	}

	@RequestMapping(value = "board.view.do", method = RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response, int post_num) {

		logger.info("GET VIEW PAGE");

		request.setAttribute("post", boardServ.selectPost(post_num));
		return "board/view";
	}

	@RequestMapping(value = "board.modify.do", method = RequestMethod.GET)
	public String modify(HttpServletRequest request, HttpServletResponse response, int post_num) {

		logger.info("MODIFY POST");

		request.setAttribute("post", boardServ.selectPost(post_num));
		return "board/modify";
	}

	@RequestMapping(value = " board.modify.submit.do", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) {

		logger.info("MODIFY POST SUBMIT");

		int result = boardServ.updatePost(boardVO);

		if (result > 0) {
			request.setAttribute("navigate", "main.do");
		} else {
			request.setAttribute("navigate", "updateError");
		}

		return "common/navigatePage";
	}

	@RequestMapping(value = "board.delete.do", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, int post_num) {

		logger.info("DELETE POST");

		int result = boardServ.deletePost(post_num);

		if (result > 0) {
			request.setAttribute("navigate", "main.do");
		} else {
			request.setAttribute("navigate", "deleteError");
		}

		return "common/navigatePage";
	}

	@RequestMapping(value = "testXss.do", method = RequestMethod.GET)
	public String xssTest() {
		return "common/xssTest";
	}

	@RequestMapping(value = "testXssS.do", method = RequestMethod.GET)
	public String xssTestsubmit(HttpServletRequest request, HttpServletResponse response, String test) {
		System.out.println(test);
		request.setAttribute("test", test);
		return "common/xssTest";
	}

}
