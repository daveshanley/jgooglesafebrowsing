package uk.co.mccann.gsb.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import uk.co.mccann.gsb.engine.util.AbstractParser;
import uk.co.mccann.gsb.engine.util.GSBRemote;
import uk.co.mccann.gsb.engine.util.URLUtils;
import uk.co.mccann.gsb.exceptions.GSBException;
import uk.co.mccann.gsb.interfaces.GSBDAO;
import uk.co.mccann.gsb.interfaces.GSBEngine;
import uk.co.mccann.gsb.interfaces.GSBEngineConfiguration;
import uk.co.mccann.gsb.interfaces.GSBParser;
import uk.co.mccann.gsb.interfaces.GSBSession;
import uk.co.mccann.gsb.model.CheckURL;
import uk.co.mccann.gsb.model.ConfigImpl;
import uk.co.mccann.gsb.model.JDBCDAO;
import uk.co.mccann.gsb.model.ListURL;
import uk.co.mccann.gsb.model.MySQLDAO;
import uk.co.mccann.gsb.model.SessionImpl;

/**
 * GoogleSafeBrowser
 * Check URL's against Google's experimental Safe Browsing API to check if they are dangerous (blacklisted or malicious).
 * <p>
 * Example usage:
	<code><pre>
	
	// create an instance of the GoogleSafeBrowser
	// use the directory /tmp/gsb to store any preferences
	// we are not going to use data validation (not correctly implemented yet) so its set to false.
	GSBEngine gsb = new GoogleSafeBrowser("YOUR API KEY HERE", "/tmp/gsb/", false);
	
	// Use a flat file to read /store data (will create the files in the same directory as your preferences set in the session)
	//GSBDAO fileDao = new FileDAO();
	
	// Use MySQL Data Access Object to read / store data
	GSBDAO fileDao = new MySQLDAO("username", "password", "localhost", "your_db_name");
	
	// Register MySQL DAO with GoogleSafeBrowser
	gsb.registerDAO(fileDao);
	
	// Update blacklist and malwarelist (if you have never run this before, all data will be downloaded)
	gsb.updateMalwarelist();
	gsb.updateBlacklist();
	
	if(gsb.isDangerous("http://fitkidplanet.com/llnfz/Servilet/")) {
		System.out.println("URL is dangerous!");
	} else {
		System.out.println("URL is not dangerous");
	}
	</pre></code>
 
 * <p>
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <david.shanley@europe.mccann.com>
 */
public class GoogleSafeBrowser implements GSBEngine {
	
	public static final String REGEX_HEADER = "\\[([\\w-]{1,20}) ([0-9]{1})\\.([0-9]{1,10})([\\w\\s]{0,10})\\]";
	public static final String REGEX_MAC = "\\[mac=[\\w\\/=]{1,30}\\]";
	public static final String REGEX_HASH = "([\\+-]{1})([\\w]{32})";
	
	private GSBDAO dao;
	
	private GSBEngineConfiguration config;
	private GSBSession session;
	private HashMap<String, ListURL> malwareMap, blacklistMap;
	
	
	/**
	 * Set up and create new instance of GoogleSafeBrowser
	 * @param apiKey
	 * @param dataStore
	 * @param useKey
	 * @throws GSBException
	 */public GoogleSafeBrowser(String apiKey, String dataStore, boolean useKey) throws GSBException {
		
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
		
		URLUtils urlutils = new URLUtils();
		ArrayList<CheckURL> urls = urlutils.getLookupURLs(url);
		
		/* check the DAO being used, if its a JDBC DAO then we can speed things up a little by a direct query */
		if(this.dao instanceof JDBCDAO) {
			
			for(CheckURL checkURL : urls) {
				
				if(this.dao.locateBlacklistHash(checkURL.getMD5Hash()) || this.dao.locateMalwareHash(checkURL.getMD5Hash())) {
					return true;
				}
			}
			
			return false;
		} else {
			
			HashMap<String, ListURL> blacklistDataMap = this.getBlacklistURLDataMap();
		
			if(checkDataMap(urls, blacklistDataMap)) { 
				return true;
			}
		
			return false;
		}	
	}
	
	public boolean isMalwarelisted(String url) throws GSBException {
		
		/* update lists */
		this.updateMalwarelist();
		
		URLUtils urlutils = new URLUtils();
		ArrayList<CheckURL> urls = urlutils.getLookupURLs(url);
		
		/* check the DAO being used, if its a JDBC DAO then we can speed things up a little by a direct query */
		if(this.dao instanceof JDBCDAO) {
			
			for(CheckURL checkURL : urls) {
				
				if(this.dao.locateBlacklistHash(checkURL.getMD5Hash()) || this.dao.locateMalwareHash(checkURL.getMD5Hash())) {
					return true;
				}
			}
			
			return false;
		
		} else {
			HashMap<String, ListURL> malwareDataMap = this.getMalwareURLDataMap();
		
			if(checkDataMap(urls, malwareDataMap)) { 
				return true;
			}
		
			return false;
		}
	}
	
	private HashMap<String, ListURL> getBlacklistURLDataMap() throws GSBException {
		
		return this.dao.readBlacklist(this.config);
	}
	
	private HashMap<String, ListURL> getMalwareURLDataMap()  throws GSBException {
		
		return this.dao.readMalwarelist(this.config);
	}
	
	
	public boolean isDangerous(String url) throws GSBException {
		
		/* update lists */
		//this.updateBlacklist();
		//this.updateMalwarelist();
		
		URLUtils urlutils = new URLUtils();
		ArrayList<CheckURL> urls = urlutils.getLookupURLs(url);
		
		CheckURL testurl = new CheckURL(url);
		
		/* check the DAO being used, if its a JDBC DAO then we can speed things up a little by a direct query */
		if(this.dao instanceof JDBCDAO) {
			
			for(CheckURL checkURL : urls) {
				if(this.dao.locateBlacklistHash(checkURL.getMD5Hash()) || this.dao.locateMalwareHash(checkURL.getMD5Hash())) {
					return true;
				}
			}
			
			return false;
			
		} else {	
			
			
			HashMap<String, ListURL> blacklistDataMap = this.getBlacklistURLDataMap();
			HashMap<String, ListURL> malwareDataMap = this.getMalwareURLDataMap();
		
			if(checkDataMap(urls, blacklistDataMap) || checkDataMap(urls, malwareDataMap)) { 
				return true;
			}
		
			return false;
		}
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
