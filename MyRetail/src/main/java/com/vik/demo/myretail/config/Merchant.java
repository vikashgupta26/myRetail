package com.vik.demo.myretail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Merchant {
	private String[] merchantList;
	
	private String[] merchantPassword;
	
	@Autowired
	public Merchant(@Value("${myretail.merchants}") final String strs,
			@Value("${myretail.merchantsPass}") final String strs2) {
		merchantList = strs.split(",");
		merchantPassword = strs2.split(",");
	}
	
	public String[] getMerchantList() {
		return this.merchantList;
	}
	
	public String[] getMerchantPassword() {
		return this.merchantPassword;
	}
}
