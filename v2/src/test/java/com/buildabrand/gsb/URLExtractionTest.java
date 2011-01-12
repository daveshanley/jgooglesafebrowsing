package com.buildabrand.gsb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.buildabrand.gsb.model.CheckURL;
import com.buildabrand.gsb.util.URLUtils;

public class URLExtractionTest {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void testURLCanoniconialization() {
		
		try {
		 
			URLUtils utils = URLUtils.getInstance();
			
			List<CheckURL> urls = utils.getLookupURLs("http://a.b.c/1/2.html?param=1");
			assertEquals(8, urls.size());
			
			urls = utils.getLookupURLs("http://a.b.c.d.e.f.g/1.html");
			assertEquals(10, urls.size());
			
			urls = utils.getLookupURLs("http://1.2.3.4/1/");
			assertEquals(6, urls.size());
			
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed: " +exp);
		}
		
	}
	
}
