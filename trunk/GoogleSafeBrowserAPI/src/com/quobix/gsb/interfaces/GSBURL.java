package com.quobix.gsb.interfaces;

import java.net.URL;

public interface GSBURL {
	
	public void setMD5Hash(String md5Hash);
	
	public String getMD5Hash();
	
	public void setStatus(int status);
	
	public boolean isAdded();
	
	public boolean isRemoved();
	
}
