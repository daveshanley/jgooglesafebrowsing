package uk.co.mccann.gsb.interfaces;

import java.io.BufferedReader;
import java.util.HashMap;

import uk.co.mccann.gsb.model.ListURL;

/**
 * GSBParser
 * Parser the HTTP response(s) from the GoogleSafeBrowsinng API
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
public interface GSBParser {
	
	/**
	 * Parse the response.
	 * @param response
	 * @param key
	 * @return
	 */
	public HashMap<String, ListURL> parseResponse(String response, String key);
	
	/**
	 * Parse the response.
	 * @param response
	 * @param key
	 * @return
	 */
	public HashMap<String, ListURL> parseResponse(BufferedReader response, String key);
	
	/**
	 * Extract the minor version of the list being parsed (via string)
	 * @param response
	 * @return
	 */
	public Integer extractMinorVersion(String response);
	
	/**
	 * Extract the minor version of the list being parsed (via reader)
	 * @param response
	 * @return
	 */
	public Integer extractMinorVersion(BufferedReader response);
	
	/**
	 * Extract the Major version of the list being parsed (via string)
	 * @param response
	 * @return
	 */
	public Integer extractMajorVersion(String response);
	
	/**
	 * Extract the Major version of the list being parsed (via reader)
	 * @param response
	 * @return
	 */
	public Integer extractMajorVersion(BufferedReader response);
	
	/**
	 * Return boolean indicating the list is an update (your local version is out of date)
	 * @param response
	 * @return
	 */
	public boolean isUpdate(String response);
	
	/**
	 * Return boolean indicating the list is an update (your local version is out of date)
	 * @param response
	 * @return
	 */
	public boolean isUpdate(BufferedReader response);
	
	
}
