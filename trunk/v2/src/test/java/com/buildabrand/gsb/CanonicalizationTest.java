package com.buildabrand.gsb;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.buildabrand.gsb.util.URLUtils;
import com.buildabrand.gsb.util.UrlEncoder;






public class CanonicalizationTest {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void testURLCanoniconialization() {
		
		try {
			
			
		  
	       
	       URLUtils utils = new URLUtils();
	       
	    	        
	        assertEquals("http://host/%25",utils.canonicalizeURL("http://host/%25%32%35"));
			assertEquals("http://host/%25%25",utils.canonicalizeURL("http://host/%25%32%35%25%32%35"));
			assertEquals("http://host/%25",utils.canonicalizeURL("http://host/%2525252525252525"));
			assertEquals("http://host/asdf%25asd",utils.canonicalizeURL("http://host/asdf%25%32%35asd"));
			assertEquals("http://www.google.com/",utils.canonicalizeURL("http://www.google.com"));
			assertEquals("http://168.188.99.26/.secure/www.ebay.com/",utils.canonicalizeURL("http://%31%36%38%2e%31%38%38%2e%39%39%2e%32%36/%2E%73%65%63%75%72%65/%77%77%77%2E%65%62%61%79%2E%63%6F%6D/"));
			assertEquals("http://195.127.0.11/uploads/%20%20%20%20/.verify/.eBaysecure=updateuserdataxplimnbqmn-xplmvalidateinfoswqpcmlx=hgplmcx/", utils.canonicalizeURL("http://195.127.0.11/uploads/%20%20%20%20/.verify/.eBaysecure=updateuserdataxplimnbqmn-xplmvalidateinfoswqpcmlx=hgplmcx/"));
			assertEquals("http://host%23.com/~a!b@c%23d$e%25f%255E00&11*22(33)44_55+", utils.canonicalizeURL("http://host%23.com/%257Ea%2521b%2540c%2523d%2524e%25f%255E00%252611%252A22%252833%252944_55%252B"));
			assertEquals("http://195.127.0.11/blah",utils.canonicalizeURL("http://3279880203/blah"));
			assertEquals("http://www.google.com/",utils.canonicalizeURL("http://www.google.com/blah/.."));
			assertEquals("http://www.google.com/",utils.canonicalizeURL("www.google.com"));
			assertEquals("http://www.evil.com/blah",utils.canonicalizeURL("http://www.evil.com/blah#frag"));
			assertEquals("http://www.google.com/",utils.canonicalizeURL("http://www.GOOgle.com/"));
			assertEquals("http://www.google.com/",utils.canonicalizeURL("http://www.google.com.../"));
			assertEquals("http://www.google.com/foobarbaz2",utils.canonicalizeURL("http://www.google.com/foo\tbar\rbaz\n2"));
			assertEquals("http://www.google.com/q?",utils.canonicalizeURL("http://www.google.com/q?"));
			assertEquals("http://www.google.com/q?r?",utils.canonicalizeURL("http://www.google.com/q?r?"));
			assertEquals("http://evil.com/foo",utils.canonicalizeURL("http://evil.com/foo#bar#baz"));
			assertEquals("http://evil.com/foo",utils.canonicalizeURL("http://evil.com/foo;"));
			assertEquals("http://evil.com/foo?bar;",utils.canonicalizeURL("http://evil.com/foo?bar;"));
			assertEquals("http://%01%80.com/",utils.canonicalizeURL("http://\\x01\\x80.com/"));
			assertEquals("http://www.gotaport.com:1234/",utils.canonicalizeURL("http://www.gotaport.com:1234"));
			assertEquals("http://www.google.com/",utils.canonicalizeURL("  http://www.google.com/  "));
			assertEquals("http://%20leadingspace.com/",utils.canonicalizeURL("http:// leadingspace.com/"));
			assertEquals("http://%20leadingspace.com/",utils.canonicalizeURL("http://%20leadingspace.com/"));
			assertEquals("http://%20leadingspace.com/",utils.canonicalizeURL("%20leadingspace.com/"));
			assertEquals("https://www.securesite.com/",utils.canonicalizeURL("https://www.securesite.com/"));
			assertEquals("http://host.com/ab%23cd",utils.canonicalizeURL("http://host.com/ab%23cd"));
			assertEquals("http://host.com/twoslashes?more//slashes",utils.canonicalizeURL("http://host.com//twoslashes?more//slashes"));
			assertEquals("http://what%20youtalking.com/there?value=moo",utils.canonicalizeURL("http://what youtalking.....com/there?value=moo#there"));
			assertEquals("http://host.com/what/",utils.canonicalizeURL("http://host.com/what/do/../think/.."));
			
			
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed: " +exp);
		}
		
	}
	
}
