package com.quobix.gsb.interfaces;

import java.util.HashMap;

import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.model.ListURL;

public interface GSBSession {
	
	public void startSession(GSBEngineConfiguration config) throws GSBException;
	
	public String getClientKey() throws GSBException;
	
	public String getWrappedKey() throws GSBException;
	
	public HashMap<String, ListURL> getBlacklist() throws GSBException;
	
	public HashMap<String, ListURL> getMalwarelist() throws GSBException;
	
	public boolean isBlacklistUpdated();
	
	public boolean isMalwareUpdated();
	
	public Integer getBlacklistMajorVersion();
	
	public Integer getBlacklistMinorVersion();
	
	public Integer getMalwareMajorVersion();
	
	public Integer getMalwareMinorVersion();
	
}
