package com.test.webPrac.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MainInterceptor extends HandlerInterceptorAdapter {
	
	static final Logger logger = LoggerFactory.getLogger("emoney Web Practice");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		
		logger.info("INTERCEPTING");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginStatus") == null) {
			try {
				response.sendRedirect("login.do");
				logger.info("INTERCEPTING FALSE");
			} catch (IOException e) {
				logger.info(e.getMessage());
			}
			return false;
		}
		
		logger.info("INTERCEPTING TRUE");
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
