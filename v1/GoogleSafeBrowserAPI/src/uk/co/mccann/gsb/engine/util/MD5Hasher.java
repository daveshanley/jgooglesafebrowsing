package uk.co.mccann.gsb.engine.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uk.co.mccann.gsb.interfaces.Hashable;

/**
 * MD5Hasher
 * Generate an MD5 hash of a string.
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <david.shanley@europe.mccann.com> & Henrik Sjostrand, Netvouz, http://www.netvouz.com/, info@netvouz.com
 */
public abstract class MD5Hasher implements Hashable {
	
	/**
	 * Generate an MD5 hash of a String
	 * @author Henrik Sjostrand, Netvouz, http://www.netvouz.com/, info@netvouz.com
	 */
	public String generateMD5Hash(String hashString) throws NoSuchAlgorithmException {
		
		/* @author Henrik Sjostrand, Netvouz, http://www.netvouz.com/, info@netvouz.com */
		
		if (hashString == null)
			return null;

			
		MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
		mdAlgorithm.update(hashString.getBytes());
		byte[] digest = mdAlgorithm.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			hashString = Integer.toHexString(0xFF & digest[i]);
			if (hashString.length() < 2)
				hashString = "0" + hashString;
			hexString.append(hashString);
		}
		return hexString.toString();
		
	}

}
