package com.test.webPrac.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import oracle.jdbc.driver.Message;

public class SHA256Util {

	public String getEncrypt(String source, String salt){
		return getEncrypt(source, salt);
	}
	
	public String getEncrypt(String source, byte[] salt){
		
		String result = "";
		
		byte[] sourceByte = source.getBytes();
		byte[] bytes = new byte[sourceByte.length + salt.length];
		
		System.arraycopy(sourceByte, 0, bytes, 0, sourceByte.length);
		System.arraycopy(salt, 0, bytes, sourceByte.length, salt.length);
		
		try {
				// 암ㅎ화 방식 지정
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(bytes);
				
				byte[] byteData = md.digest();
				
				StringBuffer sb = new StringBuffer();
				for( int i=0; i< byteData.length; i++){
					sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
				}
				
				result = sb.toString();
		} catch (NoSuchAlgorithmException  e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public String generateSalt(){
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i < salt.length; i++){
			// byte값을 Hex 값으로 변경
			sb.append(String.format("%02x", salt[i]));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SHA256Util sha = new SHA256Util();
		for(int i =0; i<9; i++){
			System.out.println(sha.generateSalt());
		}
	}
}
