package uk.co.mccann.gsb.interfaces;


import java.io.IOException;
import java.util.Properties;

/**
 * GSBEngineConfiguration
 * Configure the instance of the GoogleSaveBrowsing engine.
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
public interface GSBEngineConfiguration {
	
	/**
	 * Set your API key (supplied by Google)
	 * @param apikey
	 */
	public void setAPIKey(String apikey);
	
	/**
	 * If you are using a File based DAO, then set the directory where you want to keep your data
	 * @param directory
	 */
	public void setDataStoreDirectory(String directory);
	
	/**
	 * Get the location of your data store directory
	 * @return
	 */
	public String getDataStoreDirectory();
	
	/**
	 * Get the value of your API key (supplied by Google)
	 * @return
	 */
	public String getAPIKey();
	
	/**
	 * Use verification key (not yet properly implemented)
	 * @param useKey
	 */
	public void useKey(boolean useKey);
	
	/**
	 * Returns boolean value indicating if verification key is being used (not yet properly implemented)
	 * @return
	 */
	public boolean isUseKey();
	
	/**
	 * Set GSB properties
	 * @param properties
	 */
	public void setPropertiesFile(String properties);
	
	/**
	 * Get GSB properties.
	 * @return
	 */
	public Properties getPropertiesFile();
	
	/**
	 * Save properties file to local file system
	 * @throws IOException
	 */
	public void savePropertiesFile() throws IOException;

}
