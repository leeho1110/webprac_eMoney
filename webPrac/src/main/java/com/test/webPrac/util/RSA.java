package com.test.webPrac.util;

import java.security.PrivateKey;

public class RSA {
	
	private PrivateKey privatekey;
	private String modulas;
	private String exponent;
		
	public RSA() {
	}

	public RSA(PrivateKey privateKey, String modulas, String exponent) {
		this.privatekey = privateKey;
		this.modulas = modulas;
		this.exponent = exponent;
	}
	
	public PrivateKey getPrivatekey() {
		return privatekey;
	}
	public void setPrivatekey(PrivateKey privatekey) {
		this.privatekey = privatekey;
	}
	public String getModulas() {
		return modulas;
	}
	public void setModulas(String modulas) {
		this.modulas = modulas;
	}
	public String getExponent() {
		return exponent;
	}
	public void setExponent(String exponent) {
		this.exponent = exponent;
	}
}
