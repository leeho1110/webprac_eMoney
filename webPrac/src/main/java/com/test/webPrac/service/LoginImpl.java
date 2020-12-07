package com.test.webPrac.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.webPrac.dao.BoardDaoMapper;
import com.test.webPrac.util.SHA256Util;
import com.test.webPrac.util.Util;
import com.test.webPrac.vo.LoginVO;
import com.test.webPrac.vo.MemberVO;

@Service
public class LoginImpl implements Login{
	
	private BoardDaoMapper BoardMapper;
	

	@Transactional(rollbackFor=Exception.class)
	@Override
	public MemberVO loginLogic(MemberVO member, HttpServletRequest request, HttpServletResponse response, LoginVO loginVO){
		
		// 멤버 확인
	
		member = checkLoginInfo(loginVO);

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

					loginVO.setAccnt_id(member.getAccnt_id());

					// 로그인 시간 갱신 (트랜잭션 매니저 적용)
//					loginHistoryLogin(member, request, loginVO);
					
					updateLastlogin(member);
					insertLastloginhistory(Util.getInfoFromRequest(request, loginVO));
					

					out.println("<html><body>");
					out.println("<meta charset='UTF-8'>");
					out.println("<script>location.href='main.do';</script>");
					out.println("</body></html>");

				} else {
					out.println("<html><body>");
					out.println("<meta charset='UTF-8'>");
					out.println("<script>alert('비밀번호를 다시 확인해주세요'); location.href='login.do';</script>");
					out.println("</body></html>");
				}
			}
		} catch (Exception e) {
			System.out.println("==============SQL Catch==============");
			e.printStackTrace();

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
	public MemberVO checkLoginInfo(LoginVO loginVO) {
		return BoardMapper.checkLoginInfo(loginVO);
	}

	@Override
	public void updateLastlogin(MemberVO member) {
		BoardMapper.updateLastlogin(member.getAccnt_id());
	}

	@Override
	public void insertLastloginhistory(LoginVO loginVO) {
		BoardMapper.updateLastloginhistory(loginVO);

	}
	
	//	private void loginHistoryLogin(MemberVO member, HttpServletRequest request, LoginVO loginVO) throws RuntimeException {
	//	updateLastlogin(member);
	//	insertLastloginhistory(Util.getInfoFromRequest(request, loginVO));
	//}

}
