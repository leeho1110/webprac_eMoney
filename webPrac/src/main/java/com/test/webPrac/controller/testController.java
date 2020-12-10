package com.test.webPrac.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.webPrac.service.TransacTestService;

@Controller
public class testController {

	@Autowired
	private TransacTestService testService;
	
	@RequestMapping(value = "/testTran.do", method = { RequestMethod.GET })
	public void add(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			System.out.println(testService.transacTest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}