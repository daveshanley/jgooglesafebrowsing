package com.quobix.gsb.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.interfaces.GSBEngineConfiguration;

public class ConfigImpl implements GSBEngineConfiguration {
	
	private String dataStore;
	private String apiKey;
	private boolean useKey;
	private String properties;
	private Properties propertiesFile;
	
	public ConfigImpl(String dataStore) throws GSBException {
		
		this.propertiesFile = new Properties();
		this.dataStore = dataStore;
		
		try {
			
			/* check properties exist, if not load defaults */
			InputStream input;
			
			File props = new File(this.dataStore + "gsb.properties");
			
			if(props.exists() && props.canRead() && props.canWrite()) {
				input = new FileInputStream(this.dataStore + "gsb.properties");
			} else {
				input = ConfigImpl.class.getResourceAsStream("/gsb.properties");
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

	public void setPropertiesFile(String properties) {
		this.properties = properties;
			
	}

	public void useKey(boolean useKey) {
		this.useKey = useKey;
	}

	public boolean isUseKey() {
		if(this.useKey) return true;
		return false;
	}

	public Properties getPropertiesFile() {
	return this.propertiesFile;
	}

	public String getAPIKey() {
		return this.apiKey;
	}

	public void savePropertiesFile() throws IOException {
		
		this.propertiesFile.store(new FileOutputStream(this.dataStore + "gsb.properties"), null);
			
	}

	public String getDataStoreDirectory() {
		return this.dataStore;
	}

	public void setDataStoreDirectory(String directory) {
		this.dataStore = directory;
	}

}
