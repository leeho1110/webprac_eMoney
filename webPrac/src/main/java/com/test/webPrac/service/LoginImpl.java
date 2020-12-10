package com.test.webPrac.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.webPrac.controller.MainController;
import com.test.webPrac.dao.LoginDaoMapper;
import com.test.webPrac.dao.RegisterDaoMapper;
import com.test.webPrac.util.SHA256Util;
import com.test.webPrac.util.Util;
import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Service
public class LoginImpl implements LoginService {
	
	static final Logger Logger = LoggerFactory.getLogger(LoginImpl.class);
	
	@Autowired
	public LoginDaoMapper loginDaoMapper;

	@Autowired
	public RegisterDaoMapper registerDaoMapper;

	@Transactional
	@Override
	public String loginLogic(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginVO loginVO) {
		
		Logger.info("lOGIN LOGIC START");
		
		// 로그인 시 입력한 id로 존재 여부 확인
		MemberVO member = loginDaoMapper.checkLoginInfo(loginVO);
		String loginApi = loginVO.getLoginApi();
		
		// 일반 로그인 경우
		Logger.info("ID PASS");
		if (member != null && loginApi == null) {

			// PW 확인
			if (checkLoginPw(loginVO, member)) {
				Logger.info("PW PASS");

				// DB 정보 세팅
				member = loginDaoMapper.setInfoOfMember(member);
				// browser, moblie, os 정보 세팅
				member = Util.getInfoFromRequest(request, member);

				// 로그인 시간 갱신 및 로그인 히스토리 삽입 (트랜잭션 매니저 적용)
				loginDaoMapper.updateLastlogin(member.getAccnt_id());
				loginDaoMapper.insertLastloginhistory(member);
				
				Logger.info("UPDATE AND INSERT LAST LOGIN HISTORY");

				// Session Cookie를 사용한 로그인 처리
				
				session.setAttribute("loginStatus", member);
				
				Logger.info("LOGIN COMPLETE");
				
				return "main.do";

			} else {
				System.out.println("loginFail");
				return "login.do";
			}
		}
		// 로그인 API 연동 시 (네이버) 바로 로그인 창으로 이동
		else if (member != null && loginApi != null) {

			// 리팩토링
			// DB 정보 세팅
			member = loginDaoMapper.setInfoOfMember(member);
			// browser, moblie, os 정보 세팅
			member = Util.getInfoFromRequest(request, member);
			System.out.println(member.toString());

			// Session Cookie를 사용한 로그인 처리
			session.setAttribute("loginStatus", member);

			// 로그인 시간 갱신 및 로그인 히스토리 삽입 (트랜잭션 매니저 적용)
			// loginDaoMapper.updateLastlogin(member.getAccnt_id());
			// loginDaoMapper.insertLastloginhistory(member);

			System.out.println("apiLogin");
			return "main.do";
		}

		// 로그인 API 연동 시 추가 회원가입 절차
		else if (member == null && loginApi != null) {
			System.out.println("regitApi");
			return "register.Api.do";
		}

		// 회원가입 X
		else {
			System.out.println("loginFail");
			return "login.do";
		}

	}

	@Override
	public boolean checkLoginPw(LoginVO loginVO, MemberVO member) {

		// 암호화 객체 생성
		SHA256Util sha = new SHA256Util();

		// 로그인 시 입력받은 비밀번호와 일치하는지 확인
		String dbPW = member.getS_passwd();
		String loginPW = sha.getEncrypt(loginVO.getPw(), member.getSalt());

		return dbPW.equals(loginPW);
	}

	@Transactional
	@Override
	public void transactest() {
		registerDaoMapper.insert1(1);
		registerDaoMapper.insert2(2);
		throw new RuntimeException();
	}

	@Override
	public void naverLoginLogic(String userProfile, HttpSession session) {

		// 넘어온 값들 JSON 형변환
		System.out.println(userProfile);
		JSONObject naverInfo = JSONObject.fromObject(JSONSerializer.toJSON(userProfile)).getJSONObject("response");

		// Naver 고유 ID 추출
		String naverUniqId = (String) naverInfo.get("id");
		String naverName = (String) naverInfo.get("name");

		session.setAttribute("naverUniqId", naverUniqId);
		session.setAttribute("naverName", naverName);
	}

}
