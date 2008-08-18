package com.quobix.gsb.model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import com.quobix.gsb.engine.util.AbstractParser;
import com.quobix.gsb.engine.util.GSBRemote;
import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.interfaces.GSBEngineConfiguration;
import com.quobix.gsb.interfaces.GSBParser;
import com.quobix.gsb.interfaces.GSBSession;

public class SessionImpl implements GSBSession {
	
	private String clientKey;
	private String wrappedKey;
	private GSBEngineConfiguration config;
	private Integer blacklistMinorVersion;
	private Integer malwareMinorVersion;
	private Integer blacklistMajorVersion;
	private Integer malwareMajorVersion;
	
	private String apiRoot;
	private String apiQuery;
	private String apiKeyArg;
	private String wrappedKeyArg;
	private String blacklistName;
	private String malwareName;
	
	
	private boolean blacklistupdate = false;
	private boolean malwareupdate = false;
	
	
	public String getClientKey() throws GSBException{
		return this.clientKey;
	}

	public String getWrappedKey() throws GSBException{
		return this.wrappedKey;
	}

	public void startSession(GSBEngineConfiguration config) throws GSBException {
		this.config = config;
		
		if(this.config.isUseKey()) {
			/* start by getting a key */
			try {
			
				BufferedReader keyData = GSBRemote.returnRawRequest(new URL(this.config.getPropertiesFile().getProperty("gsb.keygen-url")));
				
				/* read in the server response */
				String s;
				while((s = keyData.readLine())!=null) {
					if(s.contains(this.config.getPropertiesFile().getProperty("gsb.clientkey.string"))) {
						this.clientKey = this.extractKey(s);
					}
					if(s.contains(this.config.getPropertiesFile().getProperty("gsb.wrappedkey.string"))) {
						this.wrappedKey = this.extractKey(s);
					}
				}
				
			} catch (MalformedURLException exp) {
			
				throw new GSBException("unable to start session");
			
			} catch (IOException exp) {
			
				throw new GSBException("unable to start session");
			}
			
		}
		
		/* get current minor versions */
		this.blacklistMinorVersion = Integer.parseInt(this.config.getPropertiesFile().getProperty("gsb.blacklist.version.minor"));
		this.malwareMinorVersion = Integer.parseInt(this.config.getPropertiesFile().getProperty("gsb.malware.version.minor"));
		
		/* get current major versions */
		this.blacklistMajorVersion = Integer.parseInt(this.config.getPropertiesFile().getProperty("gsb.blacklist.version.major"));
		this.malwareMajorVersion = Integer.parseInt(this.config.getPropertiesFile().getProperty("gsb.malware.version.major"));
		
		/* set everything else */
		this.apiRoot = this.config.getPropertiesFile().getProperty("gsb.url");
		this.apiQuery = this.config.getPropertiesFile().getProperty("gsb.update-app");
		this.apiKeyArg = this.config.getPropertiesFile().getProperty("gsb.update-app.apikey");
		this.wrappedKeyArg = this.config.getPropertiesFile().getProperty("gsb.update-app.wrappedkey");
		this.blacklistName = this.config.getPropertiesFile().getProperty("gsb.blacklistname");
		this.malwareName = this.config.getPropertiesFile().getProperty("gsb.malwarename");
		
	}

	public HashMap<String, ListURL> getBlacklist() throws GSBException{
		
		/* parser */
		GSBParser parser = new AbstractParser();
		HashMap<String, ListURL> dataMap = null;
		
		try {
		
			String wrappedKey = "";
			/* key check */
			if(this.config.isUseKey()) {
				wrappedKey = "&" + wrappedKeyArg + "=" + this.getWrappedKey();
			}
			
			URL queryURL = new URL(apiRoot +  apiQuery + "?client=api&" + apiKeyArg + "=" + this.config.getAPIKey() + wrappedKey + "&version=" + this.blacklistName + ":" + this.blacklistMajorVersion + ":" + this.blacklistMinorVersion);
			/* get server response */
			String serverResponse = GSBRemote.returnRequest(queryURL);
			
			//System.out.println(serverResponse);
			
			/* check if update */
			if(parser.isUpdate(serverResponse)) {
				this.blacklistupdate = true;
			}
			
			dataMap = parser.parseResponse(serverResponse, this.getClientKey());
			
			/* only parse if the data map is populated! */
			if(dataMap.size() >0){
				this.config.getPropertiesFile().setProperty("gsb.blacklist.version.major", parser.extractMajorVersion(serverResponse).toString());
				this.config.getPropertiesFile().setProperty("gsb.blacklist.version.minor", parser.extractMinorVersion(serverResponse).toString());
				this.config.savePropertiesFile();
			}
			
		} catch (MalformedURLException exp) {
			// should never be thrown!
		
		} catch (IOException exp) {
		
			exp.printStackTrace();
		}
		
		
		return dataMap;
		
	}

	public HashMap<String, ListURL> getMalwarelist() throws GSBException{
		
		/* parser */
		GSBParser parser = new AbstractParser();
		HashMap<String, ListURL> dataMap = null;
		
		try {
		
			String wrappedKey = "";
			/* key check */
			if(this.config.isUseKey()) {
				wrappedKey = "&" + wrappedKeyArg + "=" + this.getWrappedKey();
			}
			
			URL queryURL = new URL(apiRoot +  apiQuery + "?client=api&" + apiKeyArg + "=" + this.config.getAPIKey() + wrappedKey + "&version=" + this.malwareName + ":" + this.malwareMajorVersion + ":" + this.malwareMinorVersion);
			/* get server response */
			String serverResponse = GSBRemote.returnRequest(queryURL);
			
			/* check if update */
			if(parser.isUpdate(serverResponse)) {
				this.malwareupdate = true;
			}
			
			dataMap = parser.parseResponse(serverResponse, this.getClientKey());
			
			/* only parse if the data map is populated! */
			if(dataMap.size() >0){
				this.config.getPropertiesFile().setProperty("gsb.malware.version.major", parser.extractMajorVersion(serverResponse).toString());
				this.config.getPropertiesFile().setProperty("gsb.malware.version.minor", parser.extractMinorVersion(serverResponse).toString());
				this.config.savePropertiesFile();
			}
			
		} catch (MalformedURLException exp) {
			// should never be thrown!
		
		} catch (IOException exp) {
		
			exp.printStackTrace();
		}
		
		
		return dataMap;
	}
	
	private String extractKey(String line) {
		if(line.contains(this.config.getPropertiesFile().getProperty("gsb.clientkey.string"))) {
			String[] tokenized = line.split(":");
			if(tokenized[2]!=null) {
				return tokenized[2];
			}
		}
		if(line.contains(this.config.getPropertiesFile().getProperty("gsb.wrappedkey.string"))) {
			String[] tokenized = line.split(":");
			if(tokenized[2]!=null) {
				return tokenized[2];
			}
		}
		return null;
	}

	public boolean isBlacklistUpdated() {
		return this.blacklistupdate;
	}

	public boolean isMalwareUpdated() {
		return this.malwareupdate;
	}
	
	public Integer getBlacklistMajorVersion() {
		return this.blacklistMajorVersion;
	}

	public Integer getBlacklistMinorVersion() {
		return this.blacklistMinorVersion;
	}

	public Integer getMalwareMajorVersion() {
		return this.malwareMajorVersion;
	}

	public Integer getMalwareMinorVersion() {
		return this.malwareMinorVersion;
	}
	
}
