package com.quobix.gsb.engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.quobix.gsb.exceptions.GSBException;

public class GSBRemote {

	public static String returnRequest(URL url) throws GSBException {
		
		try {
			
			/* set up the connection */
			URLConnection connection = (URLConnection)url.openConnection();
			InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"UTF8");
			BufferedReader reader = new BufferedReader(isr);
			
			/* read in the server response */
			String s;
			StringWriter data = new StringWriter();
			while((s = reader.readLine())!=null) {
				
				data.append(s + "\n");
			}
			/* return as string */
			
			return data.toString();
			
		} catch (MalformedURLException exp) {
			
			throw new GSBException("malformed URL: "  + url.toString());
		
		} catch (IOException exp) {
			throw new GSBException("unable to read from url stream: " + url.toString() + ", server said: " + exp.getMessage());
		}
		
		
	}
	
	public static BufferedReader returnRawRequest(URL url) throws GSBException {
		
		try {
			
			/* set up the connection */
			URLConnection connection = (URLConnection)url.openConnection();
			InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"UTF8");
			BufferedReader reader = new BufferedReader(isr);
			return reader;
			
		} catch (MalformedURLException exp) {
			
			throw new GSBException("malformed URL: "  + url.toString());
		
		} catch (IOException exp) {
			
			throw new GSBException("unable to read from url stream: " + url.toString() + ", server said: " + exp.getMessage());
		}
		
		
	}
	
}
