package com.test.webPrac.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BoardInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

		// 세션 가져오기 ( 세션이 이미 존재하면 기존 것을 반환, 없다면 새로운 세션 생성)
		HttpSession session = request.getSession();

		if (session.getAttribute("loginStatus") == null) {
			try {
				response.sendRedirect("login.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
