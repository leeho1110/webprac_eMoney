package com.test.webPrac.util;

import javax.servlet.http.HttpServletRequest;

import com.test.webPrac.vo.LoginVO;

public class Util {

	public static LoginVO getInfoFromRequest(HttpServletRequest request, LoginVO loginVO) {

		// IP 가져오기
		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		// Set Ip address (IPv4)
		// Run -> Run Configurations -> Tomcat -> Arguments -> -Djava.net.preferIPv4Stack=true (IPv6 -> IPv4)
		loginVO.setIp(ip);

		// 브라우저 및 OS 정보 가져오기
		String browserAndOs = request.getHeader("User-Agent");
		
		// is_Mobile default -> false;
		String browser = "";
		loginVO.setIs_mobile(false);
		
		if (browserAndOs != null) {
			if (browserAndOs.indexOf("Trident") > -1) {
				browser = "MSIE";
			} else if (browserAndOs.indexOf("Edg") > -1) {
				browser = "Edge";
			} else if (browserAndOs.indexOf("Chrome") > -1) {
				browser = "Chrome";
			} else if (browserAndOs.indexOf("Opera") > -1) {
				browser = "Opera";
			} else if (browserAndOs.indexOf("iPhone") > -1 && browserAndOs.indexOf("Mobile") > -1) {
				browser = "iPhone";
				loginVO.setIs_mobile(true);
			} else if (browserAndOs.indexOf("Android") > -1 && browserAndOs.indexOf("Mobile") > -1) {
				browser = "Android";
				loginVO.setIs_mobile(true);
			}
		}
		
		// set Browser
		loginVO.setBrowser(browser);
		
		String os = null;
		if(browserAndOs.indexOf("NT 6.0") != -1) os = "Windows Vista/Server 2008";
		else if(browserAndOs.indexOf("NT 10.0") != -1) os = "Windows 10 Pro";
		else if(browserAndOs.indexOf("NT 5.2") != -1) os = "Windows Server 2003";
		else if(browserAndOs.indexOf("NT 5.1") != -1) os = "Windows XP";
		else if(browserAndOs.indexOf("NT 5.0") != -1) os = "Windows 2000";
		else if(browserAndOs.indexOf("NT") != -1) os = "Windows NT";
		else if(browserAndOs.indexOf("9x 4.90") != -1) os = "Windows Me";
		else if(browserAndOs.indexOf("98") != -1) os = "Windows 98";
		else if(browserAndOs.indexOf("95") != -1) os = "Windows 95";
		else if(browserAndOs.indexOf("Win16") != -1) os = "Windows 3.x";
		else if(browserAndOs.indexOf("Windows") != -1) os = "Windows";
		else if(browserAndOs.indexOf("Linux") != -1) os = "Linux";
		else if(browserAndOs.indexOf("Macintosh") != -1) os = "Macintosh";
		else os = ""; 
		
		// set OS
		loginVO.setOs(os);
		
		


		return loginVO;
	}

}
