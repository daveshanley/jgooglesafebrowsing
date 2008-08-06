package com.quobix.gsb.engine.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.quobix.gsb.interfaces.Hashable;

public abstract class MD5Hasher implements Hashable {
	
	
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
