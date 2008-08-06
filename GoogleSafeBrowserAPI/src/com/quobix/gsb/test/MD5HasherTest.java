package com.quobix.gsb.test;

import com.quobix.gsb.engine.util.MD5Hasher;
import com.quobix.gsb.engine.util.MD5HasherImpl;
import com.quobix.gsb.interfaces.Hashable;

import junit.framework.TestCase;

public class MD5HasherTest extends TestCase {

	public void testGenerateMD5Hash() {
		
		try {
			
			/* lets check this md5 is working */
			String sample = "2d2d5818d90069660ac66b3d69e24710";
			String phrase = "I love the smell of napalm in the morning";
		
			Hashable hasher = MD5HasherImpl.getInstance();
			this.assertEquals(hasher.generateMD5Hash(phrase), sample);
			
		} catch (Exception exp) {
		
		
			fail("complete failure: " + exp.getMessage());
		
		}
	}

}
