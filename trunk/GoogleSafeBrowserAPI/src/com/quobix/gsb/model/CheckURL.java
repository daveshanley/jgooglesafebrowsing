package com.quobix.gsb.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import com.quobix.gsb.engine.util.MD5HasherImpl;
import com.quobix.gsb.interfaces.Hashable;

public class CheckURL extends ListURL implements Hashable {

	private String url;
	
	public CheckURL(String url) {
		super();
		this.md5Hash = this.generateMD5Hash(url.toString());
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String generateMD5Hash(String hashString) {
		
		try { 
			Hashable hasher =  MD5HasherImpl.getInstance();
			this.md5Hash = hasher.generateMD5Hash(hashString);
			return this.md5Hash;
		
		} catch (NoSuchAlgorithmException exp) {
		
			return null;
		
		}
	}
	
	
}
