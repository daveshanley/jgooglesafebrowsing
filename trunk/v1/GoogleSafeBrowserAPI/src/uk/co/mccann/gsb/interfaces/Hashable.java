package uk.co.mccann.gsb.interfaces;

import java.security.NoSuchAlgorithmException;

/**
 * Hashable
 * Should be implemented by any Hashable entity (mainly URLs)
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
public interface Hashable {
	
	/**
	 * Generate an MD5 hash of the URL supplied.
	 * @param hashString
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String generateMD5Hash(String hashString) throws NoSuchAlgorithmException ;
	
}
