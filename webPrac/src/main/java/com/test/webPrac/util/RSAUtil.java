package com.test.webPrac.util;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

// Reference -> https://vip00112.tistory.com/40 
public class RSAUtil implements Serializable{

	// 암호화에 필요한 객체들 생성
	private KeyPairGenerator generator;
	private KeyFactory keyFactory;
	private KeyPair keyPair;
	private Cipher cipher;

	public RSAUtil() {
		try {
			generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			keyFactory = KeyFactory.getInstance("RSA");
			cipher = Cipher.getInstance("RSA");
		} catch (Exception e) {
			System.out.println(("RSAUtil 생성 실패."));
		}

	}

	// 새로운 키값을 가지는 RSA 생성
	public RSA creatRSA() {
		RSA rsa = null;
		try {
			keyPair = generator.generateKeyPair();
			
			PublicKey publickey = keyPair.getPublic();
			PrivateKey privatekey = keyPair.getPrivate();

			RSAPublicKeySpec publicSpec =  (RSAPublicKeySpec) keyFactory.getKeySpec(publickey, RSAPublicKeySpec.class);
			String modulus = publicSpec.getModulus().toString(16);
			String exponent = publicSpec.getPublicExponent().toString(16);
			
			rsa = new RSA(privatekey, modulus, exponent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsa;
	}

	public String getDecryptText(PrivateKey privateKey, String encryptedText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(hexToByteArray(encryptedText));
		return new String(decryptedBytes, "UTF-8");
	}

	// 16진수 문자열을 byte 배열로 변환
	private byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}

}
