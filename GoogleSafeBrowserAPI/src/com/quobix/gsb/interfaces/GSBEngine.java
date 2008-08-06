package com.quobix.gsb.interfaces;

import java.net.URL;

import com.quobix.gsb.exceptions.GSBException;

public interface GSBEngine {
	
	public boolean isDangerous(String url) throws GSBException;
	
	public boolean isBlacklisted(String url) throws GSBException;
	
	public boolean isMalwarelisted(String url) throws GSBException;
	
	public void registerDAO(GSBDAO dao);
	
	public void updateMalwarelist() throws GSBException;
	
	public void updateBlacklist() throws GSBException;
	
}
