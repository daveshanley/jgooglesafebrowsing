package com.quobix.gsb.interfaces;

import java.security.NoSuchAlgorithmException;

public interface Hashable {

	public String generateMD5Hash(String hashString) throws NoSuchAlgorithmException ;
	
}
