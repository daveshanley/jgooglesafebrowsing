package com.buildabrand.gsb;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

import com.buildabrand.gsb.exception.GSBBadRequestException;
import com.buildabrand.gsb.exception.GSBException;
import com.buildabrand.gsb.exception.GSBNotAuthorizedException;
import com.buildabrand.gsb.util.GSBRemote;

public class GSBRemoteTest {
	
	private String apiKey ="ABQIAAAASLrZjKKtpkYBuMqs772DCBSA_VeHu55BV3vkfe17EL-cc12nLQ";
	
	
	@Test
	public void GSBRemoteBadRequest() {
		
		try {
		
			URL url1 = new URL("http://safebrowsing.clients.google.com/safebrowsing/list");
			String response = GSBRemote.returnRequest(url1, "");
			assertNull(response);
			
		} catch (GSBBadRequestException exp) {
			
			// desired behaviour
			
		} catch (GSBException exp) {
		
			fail("failure: " + exp.getMessage());
		
		} catch (Exception exp) {
		
			fail("failure: " + exp.getMessage());
		
		}
		
		
	}
	
	@Test
	public void GSBRemoteSuccess() {
		
		try {
		
			URL url1 = new URL("http://safebrowsing.clients.google.com/safebrowsing/list?client=api&apikey="+apiKey+"&appver=2.0&pver=2.2");
			String response = GSBRemote.returnRequest(url1, "");
			assertNotNull(response);
			
		} catch (GSBNotAuthorizedException exp) {
			
			fail("failure: " + exp.getMessage());
			
		} catch (GSBException exp) {
		
			fail("failure: " + exp.getMessage());
		
		} catch (Exception exp) {
		
			fail("failure: " + exp.getMessage());
		
		}
		
		
	}
	
	@Test
	public void GSBRemoteDownloadRequest() {
		
		try {
		
			URL url1 = new URL("http://safebrowsing.clients.google.com/safebrowsing/downloads?client=api&apikey="+apiKey+"&appver=2.0&pver=2.2");
			String response = GSBRemote.returnRequest(url1, "goog-malware-shavar;\n");
			assertNotNull(response);
			
		} catch (GSBNotAuthorizedException exp) {
			
			fail("failure: " + exp.getMessage());
			
		} catch (GSBException exp) {
		
			fail("failure: " + exp.getMessage());
		
		} catch (Exception exp) {
		
			fail("failure: " + exp.getMessage());
		
		}
		
		
	}
	
	
}
