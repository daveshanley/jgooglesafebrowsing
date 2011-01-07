package uk.co.mccann.gsb.engine;

import java.util.Timer;
import java.util.TimerTask;

import uk.co.mccann.gsb.interfaces.GSBEngine;


/**
* GSBScheduler
* Schedule updates to be to run every number of minutes.
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
public class GSBScheduler extends TimerTask {
	
	private Timer timer;
	private GSBEngine engine;
	private int minutes;
	
	public GSBScheduler(GSBEngine engine) {
		this.engine = engine;
		this.timer = new Timer(true);
	}
	
	public void startSchedule(int minutes) {
		this.minutes = minutes;
		timer.schedule(this, 0, this.minutes * 60 * 1000);
	}
	
	private void updateData() {
		try {
			
			engine.updateBlacklist();
			engine.updateMalwarelist();

		} catch (Exception exp) {
			exp.printStackTrace();
			// implement logger of some kind.
		}
	}
	
	@Override
	public void run() {
		
		updateData();
		
	}

}
