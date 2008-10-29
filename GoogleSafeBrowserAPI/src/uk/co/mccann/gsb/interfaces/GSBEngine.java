package uk.co.mccann.gsb.interfaces;

import uk.co.mccann.gsb.exceptions.GSBException;


/**
 * GSBEngine
 * Main interface for GoogleSafeBrowser.
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
public interface GSBEngine {
	/**
	 * Check if a URL is dangerous, calls both isBlacklisted() and isMalwarelisted()
	 * @param url
	 * @return
	 * @throws GSBException
	 */
	public boolean isDangerous(String url) throws GSBException;
	
	
	/**
	 * Check the blacklist to check if any part of the supplied URL is listed
	 * @param url
	 * @return
	 * @throws GSBException
	 */
	public boolean isBlacklisted(String url) throws GSBException;
	
	/**
	 * Check the 
	 * @param url
	 * @return
	 * @throws GSBException
	 */
	public boolean isMalwarelisted(String url) throws GSBException;
	
	/**
	 * Regster a DAO for reading and writing.
	 * @param dao
	 */
	public void registerDAO(GSBDAO dao);
	
	/**
	 * Using your registered DAO, update the Malware list
	 * @throws GSBException
	 */
	public void updateMalwarelist() throws GSBException;
	
	/**
	 * Using your registered DAO, update the Blacklist.
	 * @throws GSBException
	 */
	public void updateBlacklist() throws GSBException;
	
}
