package com.quobix.gsb.model;

import com.quobix.gsb.interfaces.GSBURL;

public class ListURL implements GSBURL {
	
	protected String md5Hash;
	protected int status = 1;
	
	public ListURL() {
	}
	
	public ListURL(String hash) {
		this.md5Hash = hash;
	}
	
	public void setMD5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
		
	}
	
	public String getMD5Hash() {
		return this.md5Hash;
	}

	public void setStatus(int status) {
		this.status = status;
		
	}

	public boolean isAdded() {
		if(this.status == 1) return true;
		return false;
	}

	public boolean isRemoved() {
		if(this.status == 1) return false;
		return true;
	}
		
}
