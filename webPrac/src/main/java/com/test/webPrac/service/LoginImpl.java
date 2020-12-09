package com.test.webPrac.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.webPrac.dao.LoginDaoMapper;
import com.test.webPrac.util.SHA256Util;
import com.test.webPrac.util.Util;
import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Service
public class LoginImpl implements LoginService {

	@Autowired
	public LoginDaoMapper loginDaoMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String loginLogic(HttpServletRequest request, HttpServletResponse response, LoginVO loginVO) {

		// 로그인 시 입력한 id로 존재 여부 확인
		MemberVO member = loginDaoMapper.checkLoginInfo(loginVO);
		String loginApi = loginVO.getLoginApi();
		// 일반 로그인 경우
		if (member != null && loginApi == null) {

			// PW 확인
			if (checkLoginPw(loginVO, member)) {

				// DB 정보 세팅
				member = loginDaoMapper.setInfoOfMember(member);

				// browser, moblie, os 정보 세팅
				member = Util.getInfoFromRequest(request, member);

				// Session Cookie를 사용한 로그인 처리
				HttpSession session = request.getSession();
				session.setAttribute("loginStatus", member);

				// 로그인 시간 갱신 및 로그인 히스토리 삽입 (트랜잭션 매니저 적용)
				// loginDaoMapper.updateLastlogin(member.getAccnt_id());
				// loginDaoMapper.insertLastloginhistory(member);
				
				System.out.println("loginSuccess");
				return "loginSuccess";

			} else {
				System.out.println("loginFail");
				return "loginFail";
			}
		}
		// 로그인 API 연동 시 추가 회원가입 절차 X
		else if (member == null && loginApi != null) {
			System.out.println("apiRegister");
			return "apiRegister";
		}

		// 로그인 API 연동 시 (네이버)
		else if (member != null && loginApi != null) {
			System.out.println("apiLogin");
			return "apiLogin";
		} 
		
		// 회원가입 X 
		else {
			System.out.println("loginFail");
			return "loginFail";
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void transactest() {
		loginDaoMapper.insert1(1);
		loginDaoMapper.insert2(2);
		throw new RuntimeException();
	}

	@Override
	public String naverLoginLogic(String userProfile) {

		// 넘어온 값들 JSON 형변환
		JSONObject naverInfo = JSONObject.fromObject(JSONSerializer.toJSON(userProfile)).getJSONObject("response");
		
		// Naver 고유 ID 추출
		
		
		return "";
	}

}
