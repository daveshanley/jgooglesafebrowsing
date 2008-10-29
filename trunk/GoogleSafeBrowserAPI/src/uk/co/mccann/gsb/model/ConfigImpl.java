package uk.co.mccann.gsb.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import uk.co.mccann.gsb.exceptions.GSBException;
import uk.co.mccann.gsb.interfaces.GSBEngineConfiguration;

/**
 * ConfigureImpl
 * Implementation of EngineConfiguration.
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
	
	/**
	 * Save properties file.
	 */
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
