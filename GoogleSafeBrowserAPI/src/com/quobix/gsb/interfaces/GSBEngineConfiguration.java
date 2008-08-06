package com.quobix.gsb.interfaces;


import java.io.IOException;
import java.util.Properties;


public interface GSBEngineConfiguration {
	
	public void setAPIKey(String apikey);
	
	public void setDataStoreDirectory(String directory);
	
	public String getDataStoreDirectory();
	
	public String getAPIKey();
	
	public void useKey(boolean useKey);
	
	public boolean isUseKey();
	
	public void setPropertiesFile(String properties);
	
	public Properties getPropertiesFile();
	
	public void savePropertiesFile() throws IOException;

}
