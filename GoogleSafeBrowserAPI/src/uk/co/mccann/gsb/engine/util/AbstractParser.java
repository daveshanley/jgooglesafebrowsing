package uk.co.mccann.gsb.engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.co.mccann.gsb.engine.GoogleSafeBrowser;
import uk.co.mccann.gsb.interfaces.GSBParser;
import uk.co.mccann.gsb.model.ListURL;

/**
 * AbstractParser
 * Provides base logic for GSB parsers.
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
public class AbstractParser implements GSBParser {

	public Integer extractMajorVersion(String response) {
		
		/* set up a very simple regex */
		Pattern p = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Matcher matcher = p.matcher(response);
		boolean found = false;
		while(found = matcher.find()) {
			return new Integer(matcher.group(2).trim());
		} 
		return 1;	
	}

	public Integer extractMajorVersion(BufferedReader response) {
		
		Pattern p = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Matcher matcher;
		boolean found = false;
		
		String s;
		StringWriter data = new StringWriter();
	
		
		try { 
			
			while((s = response.readLine())!=null) {
				matcher = p.matcher(s);
				while(found = matcher.find()) {
					return new Integer(matcher.group(2).trim());
				} 
			}
		
			return 0;
		
		} catch (IOException exp) {
		
			return 1;
		
		}
		
	}

	public Integer extractMinorVersion(String response) {
	
		Pattern p = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Matcher matcher = p.matcher(response);
		boolean found = false;
		while(found = matcher.find()) {
			return new Integer(matcher.group(3).trim());
		} 
		return -1;	
	}

	public Integer extractMinorVersion(BufferedReader response) {
		
		Pattern p = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Matcher matcher;
		boolean found = false;
		
		String s;
		StringWriter data = new StringWriter();
		
		try { 
			
			while((s = response.readLine())!=null) {
				matcher = p.matcher(s);
				while(found = matcher.find()) {
					return new Integer(matcher.group(3).trim());
				} 
			}
		
			return 0;
		
		} catch (IOException exp) {
		
			return 1;
		
		}
		
	}

	public HashMap<String, ListURL> parseResponse(String response, String key) {
		
		Pattern header = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Pattern mac = Pattern.compile(GoogleSafeBrowser.REGEX_MAC);
		Pattern hash = Pattern.compile(GoogleSafeBrowser.REGEX_HASH);
		boolean found = false;
		Matcher matcher;
		
		matcher = hash.matcher(response);
		HashMap<String, ListURL> datamap = new HashMap<String, ListURL>();
		ListURL url;
		
		/* really need to verify the MAC code, but it seems like there is something wrong with it, its not a base64 encoded md5. */
	
		while(found = matcher.find()) {
			
			int status = 1;
			if(!matcher.group(1).equals("+")) status = 0;
			url = new ListURL(matcher.group(2));
			url.setStatus(status);
			datamap.put(matcher.group(2), url);
			
		} 
			
		return datamap;
		
	}

	public HashMap<String, ListURL> parseResponse(BufferedReader response, String key) {
		
		
		Pattern header = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Pattern mac = Pattern.compile(GoogleSafeBrowser.REGEX_MAC);
		Pattern hash = Pattern.compile(GoogleSafeBrowser.REGEX_HASH);
		boolean found = false;
		Matcher matcher;
		
		HashMap<String, ListURL> datamap = new HashMap<String, ListURL>();
		ListURL url;
		
		/* really need to verify the MAC code, but it seems like there is something wrong with it, its not a base64 encoded md5. */
		String s;
		StringWriter data = new StringWriter();
		
		try { 
			
			while((s = response.readLine())!=null) {
				matcher = hash.matcher(s);
				while(found = matcher.find()) {
			
					int status = 1;
					if(!matcher.group(1).equals("+")) status = 0;
					url = new ListURL(matcher.group(2));
					url.setStatus(status);
					datamap.put(matcher.group(2), url);
				}
			}
		} catch (IOException exp) {
				
			return null;
			
		}
			
		return datamap;
		
	}

	public boolean isUpdate(String response) {
		
		/* set up a very simple regex */
		Pattern p = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Matcher matcher = p.matcher(response);
		boolean found = false;
		while(found = matcher.find()) {
			if(matcher.group(4)!=null && matcher.group(4).trim().equals("update")) {
				return true;
			}
		} 
		return false;
		
	}

	public boolean isUpdate(BufferedReader response) {
		
		Pattern p = Pattern.compile(GoogleSafeBrowser.REGEX_HEADER);
		Matcher matcher;
		boolean found = false;
		
		String s;
		StringWriter data = new StringWriter();
		
		try { 
			
			while((s = response.readLine())!=null) {
				matcher = p.matcher(s);
				while(found = matcher.find()) {
					if(matcher.group(4)!=null && matcher.group(4).trim().equals("update")) {
						return true;
					}
				} 
			}
		
			return false;
		
		} catch (IOException exp) {
		
			return false;
		
		}
	}

}
