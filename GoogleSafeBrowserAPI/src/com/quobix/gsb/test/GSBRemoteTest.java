package com.quobix.gsb.test;

import java.net.URL;

import com.quobix.gsb.engine.util.GSBRemote;

import junit.framework.TestCase;

public class GSBRemoteTest extends TestCase {

	public void testReturnRequest() {
		
		try {
			
			System.out.println(GSBRemote.returnRequest(new URL("http://www.google.com")));
			
		} catch (Exception exp) {
			
			fail("failure: " + exp.getMessage());
			
		}
		
	}

	public void testReturnRawRequest() {
		try {
			
			System.out.println(GSBRemote.returnRawRequest(new URL("http://www.google.com")));
			
		} catch (Exception exp) {
			
			fail("failure: " + exp.getMessage());
			
		}
	}

}
