package uk.co.mccann.gsb.interfaces;

/**
 * GSBURL
 * Represents a URL within the API.
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
public interface GSBURL {
	
	/**
	 * Set MD5 Hash of URL
	 * @param md5Hash
	 */
	public void setMD5Hash(String md5Hash);
	
	/**
	 * Get MD5 Hash of URL
	 * @return
	 */	
	public String getMD5Hash();
	
	/**
	 * Set the status of the URL (add / remove)
	 * @param status
	 */
	public void setStatus(int status);
	
	/**
	 * Is this URL to be added to the list?
	 * @return
	 */
	public boolean isAdded();
	
	/**
	 * Is this URL to be removed from the list?
	 * @return
	 */
	public boolean isRemoved();
	
}
