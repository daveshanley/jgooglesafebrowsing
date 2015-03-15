# Introduction #

This is an alpha stage API, the Google Safe Browsing API is experimental as well, so don't expect the greatest results at the moment!

# Details #

## Getting Started ##

Right lets get started, Its quick and easy to use. At the moment there are two options for storing your data (lists of URL's that are considered a bit dodgy). You can use flat file or JDBC. Flat file is slower that JDBC but ideal if you don't have a database server handy.

At the moment there is only a MySQL DAO, but you can easily build your own or extend it. If you can't be bothered then you will have to wait until I can add in some more of your favourite RDBMS flavours.

```
        /* create an instance of the GoogleSafeBrowser
	   use the directory /tmp/gsb to store any preferences or flat files (you can set this to what ever you want, also
           we are not going to use data validation (not correctly implemented yet) so this argument is set to false */

	GSBEngine gsb = new GoogleSafeBrowser("YOUR-API-KEY-HERE", "/tmp/gsb/", false);
	
	/* Use a flat file to read /store data (will create the files in the same directory as your preferences set in the session)
	GSBDAO fileDao = new FileDAO(); 
        */
	
	/* Use MySQL Data Access Object to read / store data */
	GSBDAO fileDao = new MySQLDAO("username", "password", "localhost", "your_db_name");
	
	/* Register MySQL DAO with GoogleSafeBrowser */
	gsb.registerDAO(fileDao);
	
	/* Update blacklist and malwarelist (if you have never run this before, all data will be downloaded) */
	gsb.updateMalwarelist();
	gsb.updateBlacklist();
	
	/* test if URL is dangerous or not! */
        if(gsb.isDangerous("http://fitkidplanet.com/llnfz/Servilet/")) {
		System.out.println("URL is dangerous!");
	} else {
		System.out.println("URL is not dangerous");
	}
```

That's all there is to it!

## Updating the lists automatically / at an interval ##

If you want to periodically have an application update your lists for you, then you can implement a TimerTask or something yourself - or you can use the handy little class `co.uk.mccann.gsb.engine.GSBScheduler` to do it for you. Here is an example:

```
public static void main(String[] args) throws Exception{
	Runnable schedule = new Runnable() {
	public void run() {
		try {
			GSBEngine gsb = new GoogleSafeBrowser("YOUR-API-KEY-HERE", "/tmp/gsb/", false);
			GSBDAO fileDao = new MySQLDAO("root", "", "localhost", "googlesafebrowsing");
			gsb.registerDAO(fileDao);
			
			/* create a new GSBScheduler and pass it the GoogleSafeBrowser instance you just instantiated */
			GSBScheduler scheduler = new GSBScheduler(gsb);
			
			/* the scheduler takes an int argument that indicates the interval in minutes that you want it to run */
			scheduler.startSchedule(60); // update the lists every 60 minutes
					
				
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
```

If you have any problems or any questions, or you want to contribute to the project then you can email me at <dave@quobix.com>

- Dave Shanley