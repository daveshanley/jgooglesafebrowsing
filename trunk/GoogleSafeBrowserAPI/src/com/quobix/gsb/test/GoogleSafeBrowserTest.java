package com.quobix.gsb.test;

import com.quobix.gsb.engine.GSBScheduler;
import com.quobix.gsb.engine.GoogleSafeBrowser;
import com.quobix.gsb.interfaces.GSBDAO;
import com.quobix.gsb.interfaces.GSBEngine;
import com.quobix.gsb.model.FileDAO;

import junit.framework.TestCase;

public class GoogleSafeBrowserTest extends TestCase {
	
	public void testConfig() {
		
		try {
			
			GSBEngine gsb = new GoogleSafeBrowser("ABQIAAAASLrZjKKtpkYBuMqs772DCBSA_VeHu55BV3vkfe17EL-cc12nLQ", "/tmp/gsb/", false);
			GSBDAO fileDao = new FileDAO();
			gsb.registerDAO(fileDao);
			
			
			
			//assertTrue(gsb.isDangerous("http://www.keygen.us/pg/E/5.shtml"));
			assertTrue(gsb.isDangerous("http://baran-eblan.info/sutra/in.cgi?8&parameter=lyrics+for+when+you+believe+by+whitney+houston"));
			
		
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed! " + exp.getMessage());
		}
		
	}
}
