package uk.co.mccann.gsb.interfaces;

import java.util.HashMap;

import uk.co.mccann.gsb.exceptions.GSBException;
import uk.co.mccann.gsb.model.ListURL;

/**
 * GSBSession
 * Each Instance has a session, ket data & key methods are contained within.
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
public interface GSBSession {
	
	/**
	 * Start session using supplied configuration file
	 * @param config
	 * @throws GSBException
	 */
	public void startSession(GSBEngineConfiguration config) throws GSBException;
	
	/**
	 * Convenience method for getting the client key from the configuration
	 * @return
	 * @throws GSBException
	 */
	public String getClientKey() throws GSBException;
	
	/**
	 * Convenience method 
	 * @return
	 * @throws GSBException
	 */
	public String getWrappedKey() throws GSBException;
	
	/**
	 * Return HashMap containing blacklist data
	 * @return
	 * @throws GSBException
	 */
	public HashMap<String, ListURL> getBlacklist() throws GSBException;
	
	/**
	 * Return HashMap containing malware data
	 * @return
	 * @throws GSBException
	 */
	public HashMap<String, ListURL> getMalwarelist() throws GSBException;
	
	/**
	 * Boolean indicating if the blacklist data parsed from request was an updated version of existing hash data
	 * @return
	 */
	public boolean isBlacklistUpdated();
	
	/**
	 * Boolean indicating if the malware data parsed from request was an updated version of existing hash data
	 * @return
	 */
	public boolean isMalwareUpdated();
	
	/**
	 * Return the major version of the blacklist data (at the moment its always 1)
	 * @return
	 */
	public Integer getBlacklistMajorVersion();
	
	/**
	 * Return the minor version of the blacklist data
	 * @return
	 */
	public Integer getBlacklistMinorVersion();
	
	/**
	 * Return the major version of the malware data (at the moment its always 1)
	 * @return
	 */
	public Integer getMalwareMajorVersion();
	
	/**
	 * Return the minor version of the malware data
	 * @return
	 */
	public Integer getMalwareMinorVersion();
	
}
