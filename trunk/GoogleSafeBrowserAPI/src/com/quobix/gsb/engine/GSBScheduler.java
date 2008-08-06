package com.quobix.gsb.engine;

import java.util.Timer;
import java.util.TimerTask;

import com.quobix.gsb.interfaces.GSBEngine;


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
		timer.schedule(this, this.minutes * 60 * 1000);
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
