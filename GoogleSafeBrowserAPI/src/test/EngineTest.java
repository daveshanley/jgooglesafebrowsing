package test;

import uk.co.mccann.gsb.engine.GSBScheduler;
import uk.co.mccann.gsb.engine.GoogleSafeBrowser;
import uk.co.mccann.gsb.interfaces.GSBDAO;
import uk.co.mccann.gsb.interfaces.GSBEngine;
import uk.co.mccann.gsb.model.MySQLDAO;

public class EngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
			
		Runnable schedule = new Runnable() {
			public void run() {
				try {
					GSBEngine gsb = new GoogleSafeBrowser("ABQIAAAASLrZjKKtpkYBuMqs772DCBSA_VeHu55BV3vkfe17EL-cc12nLQ", "/tmp/gsb/", false);
					GSBDAO fileDao = new MySQLDAO("root", "", "localhost", "googlesafebrowsing");
				
					gsb.registerDAO(fileDao);
			
					GSBScheduler scheduler = new GSBScheduler(gsb);
					scheduler.startSchedule(1);
				
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		};
		
		Thread scheduler = new Thread(schedule);
		scheduler.start();
		
		while(true) {
			Thread.sleep(5000);
		}
		
		
	}

}
