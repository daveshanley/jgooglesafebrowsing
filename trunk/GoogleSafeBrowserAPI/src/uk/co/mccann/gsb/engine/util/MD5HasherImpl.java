package uk.co.mccann.gsb.engine.util;

import uk.co.mccann.gsb.interfaces.Hashable;


public class MD5HasherImpl extends MD5Hasher {
	
	private static MD5HasherImpl instance = null; // This instance
	
	public static Hashable getInstance() {
	
		if (instance == null) {
			synchronized (MD5HasherImpl.class) {
				if (instance == null) {
					instance = new MD5HasherImpl();
				}
			}
		}
		return instance;
	}
}
