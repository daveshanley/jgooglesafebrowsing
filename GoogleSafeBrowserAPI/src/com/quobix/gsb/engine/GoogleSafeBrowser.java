package com.quobix.gsb.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import com.quobix.gsb.engine.util.AbstractParser;
import com.quobix.gsb.engine.util.GSBRemote;
import com.quobix.gsb.engine.util.URLUtils;
import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.interfaces.GSBDAO;
import com.quobix.gsb.interfaces.GSBEngine;
import com.quobix.gsb.interfaces.GSBEngineConfiguration;
import com.quobix.gsb.interfaces.GSBParser;
import com.quobix.gsb.interfaces.GSBSession;
import com.quobix.gsb.model.CheckURL;
import com.quobix.gsb.model.ConfigImpl;
import com.quobix.gsb.model.ListURL;
import com.quobix.gsb.model.SessionImpl;

public class GoogleSafeBrowser implements GSBEngine {
	
	public static final String REGEX_HEADER = "\\[([\\w-]{1,20}) ([0-9]{1})\\.([0-9]{1,10})([\\w\\s]{0,10})\\]";
	public static final String REGEX_MAC = "\\[mac=[\\w\\/=]{1,30}\\]";
	public static final String REGEX_HASH = "([\\+-]{1})([\\w]{32})";
	
	private GSBDAO dao;
	
	private GSBEngineConfiguration config;
	private GSBSession session;
	private HashMap<String, ListURL> malwareMap, blacklistMap;
	
	public GoogleSafeBrowser(String apiKey, String dataStore, boolean useKey) throws GSBException {
		
		GSBEngineConfiguration config = new ConfigImpl(dataStore);
		config.setAPIKey(apiKey);
		config.useKey(useKey);
		this.config = config;
		
		/* create a new session */
		this.session = new SessionImpl();
		this.session.startSession(this.config);
		
	}
	
	public boolean isBlacklisted(String url) throws GSBException {
		
		/* update lists */
		this.updateBlacklist();
		
		/* create URLUtils (most of this logic was written by ...*/
		URLUtils urlutils = new URLUtils();
		
		ArrayList<CheckURL> urls = urlutils.getLookupURLs(url);
		HashMap<String, ListURL> blacklistDataMap = this.getBlacklistURLDataMap();
		
		if(checkDataMap(urls, blacklistDataMap)) { 
			return true;
		}
		
		return false;
		
	}
	
	public boolean isMalwarelisted(String url) throws GSBException {
		
		/* update lists */
		this.updateMalwarelist();
		
		/* create URLUtils (most of this logic was written by ...*/
		URLUtils urlutils = new URLUtils();
		
		ArrayList<CheckURL> urls = urlutils.getLookupURLs(url);
		HashMap<String, ListURL> malwareDataMap = this.getMalwareURLDataMap();
		
		if(checkDataMap(urls, malwareDataMap)) { 
			return true;
		}
		
		return false;
		
	}
	
	private HashMap<String, ListURL> getBlacklistURLDataMap() throws GSBException {
		
		return this.dao.readBlacklist(this.config);
	}
	
	private HashMap<String, ListURL> getMalwareURLDataMap()  throws GSBException {
		
		return this.dao.readMalwarelist(this.config);
	}
	
	
	public boolean isDangerous(String url) throws GSBException {
		
		/* update lists */
		this.updateBlacklist();
		this.updateMalwarelist();
		
		/* create URLUtils (most of this logic was written by ...*/
		URLUtils urlutils = new URLUtils();
		
		ArrayList<CheckURL> urls = urlutils.getLookupURLs(url);
		HashMap<String, ListURL> blacklistDataMap = this.getBlacklistURLDataMap();
		HashMap<String, ListURL> malwareDataMap = this.getMalwareURLDataMap();
		
		if(checkDataMap(urls, blacklistDataMap) || checkDataMap(urls, malwareDataMap)) { 
			return true;
		}
		
		return false;
	}
	
	private boolean checkDataMap(ArrayList<CheckURL> urls, HashMap<String, ListURL> dataMap) {
		
		for(String key : dataMap.keySet()) {
			
			for(CheckURL checkURL : urls) {
				/* check for an MD5 match */
				if(dataMap.get(key).getMD5Hash().equals(checkURL.getMD5Hash())){
					return true;
				}
			}
		}
		return false;
		
	}

	public void updateBlacklist() throws GSBException {
		
		if(this.dao!=null) {
			
			HashMap<String, ListURL> data = this.session.getBlacklist();
			if(this.session.isBlacklistUpdated()) {
				if(data.size()>0) {
				}
			} else {
				if(data.size()>0) {
					this.dao.replaceBlacklist(data, this.config);
				}
			}
		} else {
			throw new GSBException("you need to register a DAO before you can write data");
		}
		
	}

	public void updateMalwarelist() throws GSBException {
		if(this.dao!=null) {
			HashMap<String, ListURL> data = this.session.getMalwarelist();
			if(this.session.isMalwareUpdated()) {
				if(data.size()>0) {
				}
			} else {
				if(data.size()>0) {
					this.dao.replaceMalwarelist(data, this.config);
				}
			}
		} else {
			throw new GSBException("you need to register a DAO before you can write data");
		}

	}


	public void registerDAO(GSBDAO dao) {
		this.dao = dao;
		
	}


}
