package uk.co.mccann.gsb.engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import uk.co.mccann.gsb.exceptions.GSBException;

/**
 * GSBSRemote
 * Performs remote reading of HTTP requests
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
public class GSBRemote {

	/**
	 * Returns String of remote HTTP request.
	 * @param url
	 * @return
	 * @throws GSBException
	 */
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
	
	/**
	 * Returns BufferedReader from remote HTTP request
	 * @param url
	 * @return
	 * @throws GSBException
	 */
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
