package com.buildabrand.gsb.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.buildabrand.gsb.exception.GSBException;

public class GSBConfigImpl implements GSBConfig {

	
	private String dataStore;
	private String apiKey;
	private boolean useKey;
	private String properties;
	private String clientVersion;
	private String appVersion;
	
	private Properties propertiesFile;
	
	public GSBConfigImpl(String dataStore) throws GSBException {
		
		this.propertiesFile = new Properties();
		this.dataStore = dataStore;
		
		try {
			
			/* check properties exist, if not load defaults */
			InputStream input;
			
			File props = new File(this.dataStore + "gsb.properties");
			
			if(props.exists() && props.canRead() && props.canWrite()) {
				input = new FileInputStream(this.dataStore + "gsb.properties");
			} else {
				input = GSBConfigImpl.class.getResourceAsStream("/gsb.properties");
			}
			this.propertiesFile.load(input);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setAPIKey(String apikey) {
		this.apiKey = apikey;

	}

	public void setDataStoreDirectory(String directory) {
		this.dataStore = directory;

	}

	public String getDataStoreDirectory() {
		return this.dataStore;
	}

	public String getAPIKey() {
		return this.apiKey;
	}

	public void useKey(boolean useKey) {
		this.useKey = useKey;

	}

	public boolean isUseKey() {
		if(this.useKey) return true;
		return false;
	}

	public void setPropertiesFile(String properties) {
		this.properties = properties;

	}

	public Properties getPropertiesFile() {
		return this.propertiesFile;
	}

	/**
	 * Save properties file.
	 */
	public void savePropertiesFile() throws IOException {
		
		this.propertiesFile.store(new FileOutputStream(this.dataStore + "gsb.properties"), null);
			
	}

	public String getClientVersion() {
		return this.clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getAppVersion() {
		return this.appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;

	}

}
