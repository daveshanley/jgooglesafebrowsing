package com.buildabrand.gsb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.buildabrand.gsb.exception.GSBBadRequestException;
import com.buildabrand.gsb.exception.GSBException;
import com.buildabrand.gsb.exception.GSBNotAuthorizedException;
import com.buildabrand.gsb.exception.GSBTooManyRequestsException;
import com.buildabrand.gsb.exception.GSBVersionNotSupportedException;

/**
 * GSBException
 * Generic GSB Exception that may be thrown by any number of classes
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) Buildabrand Ltd, 2011 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <dave@buildabrand.com>
 */
public class GSBRemote {

	/**
	 * Returns String of remote HTTP request.
	 * @param url
	 * @return
	 * @throws com.buildabrand.gsb.exception
	  * @deprecated use {@link #returnRequest(URL, String)} instead
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
	 * * Returns String of remote HTTP request.
	 * @param url
	 * @param body
	 * @return body of remote request.
	 * @throws GSBException
	 */
	public static String returnRequest(URL url, String body) throws GSBException {
			
			PostMethod method = new PostMethod(url.toExternalForm());
		    
			try {
			
		    	RequestEntity entity = new StringRequestEntity(body,null,null);
		    	HttpClient httpclient = new HttpClient();
		    	method.setRequestEntity(entity);
		    	int result = httpclient.executeMethod(method);
		    	
		    	/* bad request returned */
		    	if(result==400) throw new GSBBadRequestException("bad request error returned by server: The HTTP request was not correctly formed. The client did not provide all required CGI parameters.");
		    	if(result==401) throw new GSBNotAuthorizedException("client id invalid error returned by server:  The client id is invalid.");
		    	if(result==503) throw new GSBTooManyRequestsException("service unavilable error returned by server: The server cannot handle the request. Clients MUST follow the backoff behavior specified in the Request Frequency section.");
		    	if(result==505) throw new GSBVersionNotSupportedException("service unavilable error returned by server: The server CANNOT handle the requested protocol major version.");
		    	if(result==200) return method.getResponseBodyAsString();
		    	
		    	throw new GSBException("Unknown server exception, no content returned!");
		    	
		    } catch (UnsupportedEncodingException exp) {
		    	
		    	throw new GSBException("unsupported encoding excption on url/data: " + url.toString() + ", message: " + exp.getMessage());
		    	
		    } catch (IOException exp) {
		    	
		    	throw new GSBException("IO Exception: " + url.toString() + ", message: " + exp.getMessage());
		    
		    } 
		    
	}
	
	
	/**
	 * Returns BufferedReader from remote HTTP request
	 * @param url
	 * @return
	 * @throws com.buildabrand.gsb.exception
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
