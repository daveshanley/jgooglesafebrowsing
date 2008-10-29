package test;

import uk.co.mccann.gsb.engine.GSBScheduler;
import uk.co.mccann.gsb.engine.GoogleSafeBrowser;
import uk.co.mccann.gsb.interfaces.GSBDAO;
import uk.co.mccann.gsb.interfaces.GSBEngine;
import uk.co.mccann.gsb.model.FileDAO;
import uk.co.mccann.gsb.model.MySQLDAO;


import junit.framework.TestCase;

public class GoogleSafeBrowserTest extends TestCase {
	
	public void testConfig() {
		
		try {
			
			GSBEngine gsb = new GoogleSafeBrowser("ABQIAAAASLrZjKKtpkYBuMqs772DCBSA_VeHu55BV3vkfe17EL-cc12nLQ", "/tmp/gsb/", false);
			GSBDAO fileDao = new MySQLDAO("root", "", "localhost", "googlesafebrowsing");
			
			GSBDAO fileDao = new FileDAO();
			
			gsb.registerDAO(fileDao);
			
			gsb.updateMalwarelist();
			gsb.updateBlacklist();
			
			//assertTrue(gsb.isDangerous("http://www.keygen.us/pg/E/5.shtml"));
			assertTrue(gsb.isDangerous("http://fitkidplanet.com/llnfz/Servilet/"));
			//assertTrue(gsb.isDangerous("http://baran-eblan.info/sutra/in.cgi?8&parameter=lyrics+for+when+you+believe+by+whitney+houston"));
			
		
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed! " + exp.getMessage());
		}
		
	}
}
