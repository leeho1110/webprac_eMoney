package com.test.webPrac.service;

import java.io.PrintWriter;
import java.security.spec.ECField;

import javax.crypto.CipherInputStream;
import javax.servlet.http.Cookie;
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

@Service
public class LoginImpl implements LoginService{
	
	
	@Autowired
	public LoginDaoMapper loginDaoMapper;
	

	@Transactional(rollbackFor=Exception.class)
	@Override
	public MemberVO loginLogic(MemberVO member, HttpServletRequest request, HttpServletResponse response, LoginVO loginVO){
		
		// 멤버 확인
		
		member = loginDaoMapper.checkLoginInfo(loginVO);
		
		// 암호 확인
		try {
			PrintWriter out = response.getWriter();
			if (member == null) {

				out.println("<html><body>");
				out.println("<meta charset='UTF-8'>");
				out.println("<script>alert('아이디를 다시 확인해주세요'); location.href='login.do';</script>");
				out.println("</body></html>");

			} else {

				// 성공 시
				if (checkLoginPw(loginVO, member)) {

					// Session Cookie를 사용한 로그인 처리
					HttpSession session = request.getSession();
					session.setAttribute("loginStatus", member);
				
					// 로그인 시간 갱신 (트랜잭션 매니저 적용)
					loginDaoMapper.updateLastlogin(member.getAccnt_id());
					loginDaoMapper.insertLastloginhistory(Util.getInfoFromRequest(request, member));
					
					
					response.sendRedirect("main.do");
					
				} else {
					out.println("<html><body>");
					out.println("<meta charset='UTF-8'>");
					out.println("<script>alert('비밀번호를 다시 확인해주세요'); location.href='login.do';</script>");
					out.println("</body></html>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();

		}

		return member;
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
	@Transactional(rollbackFor=Exception.class)
	public void transactest() {
		loginDaoMapper.insert1(1);
		loginDaoMapper.insert2(2);
		throw new RuntimeException();
	}

}
