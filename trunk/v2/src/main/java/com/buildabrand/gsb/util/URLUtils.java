package com.buildabrand.gsb.util;


import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import com.buildabrand.gsb.exception.GSBException;
import com.buildabrand.gsb.model.CheckURL;

public class URLUtils {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String FILL = ""; // The object we insert into the hashmaps
	private static final String ENCODING = "UTF-8";
	private static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private static final int minLineLength = 33; // A valid line contains at least 33 characters (1 operator, 32 MD5 hash)
	private static UrlEncoder codec = new UrlEncoder();
	private static String IPValidation = "^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$";
	
	/** Unscapes a string repeatedly to remove all escaped characters. Returns null if the string is invalid.
	 * @author Henrik Sjostrand, Netvouz, http://www.netvouz.com/, info@netvouz.com
	 * @param url
	 * @return
	 * @throws GSBException
	 */
	private String unescape(String url) throws GSBException {
		
		
		
		if (url == null)
			return null;

		// Because URLDecoder chokes on some invalid URLs (those containing a percent sign not 
		// followed by a hex value) we need to check we're not processing such a URL.
		StringBuffer text1 = new StringBuffer(url);
		int p = 0;
		
		
		url = text1.toString();

		String text2 = url;
		
			int i=0;
			for(int x = 0; x < 50; x++) {
				
				text2 = codec.decode(text2); // Unescape repeatedly until no more percent signs left
			}	
				//logger.debug("decoded URL: " + text2);
		
		
		logger.debug("returning URL: " + text2);
		
		return text2;
	}
	
	public String decodeHost(String host) {
		
		try {
			InetAddress addr = InetAddress.getByName(host);
			logger.debug("checking host for IP: "+  host);
			if(host.matches(IPValidation)) return host.toLowerCase();
			
			
			if(host.indexOf('.')>-1) { // most likely a domain 
				logger.debug("host contains period");
				return addr.getHostName().toLowerCase();
			
			} else {
				
				logger.debug("host does not contain a period");
				if(!addr.getHostName().matches(IPValidation)) return addr.getHostAddress();
				return addr.getHostName().toLowerCase();
					
			}
			
		} catch (UnknownHostException exp) {
			return host;
		}
		
	}
	

	
	
	/** Returns the canonicalized form of a URL, core logic written by Henrik Sjostrand
	 * @author Henrik Sjostrand, Netvouz, http://www.netvouz.com/, info@netvouz.com
	 * @param queryURL
	 * @return
	 * @throws GSBException
	 */
	
	public String canonicalizeURL(String queryURL) throws GSBException, Exception{
		
		//queryURL = URIUtil.decode(queryURL);
		
		if (queryURL == null)
			return null;

		String url = queryURL;
		//System.out.println("Original       : " + url);

		try {
			// Create a URL object and extract the fields we need
			
			/* first of all extract the components of the URL to make sure that it has a protocol! */
			if(url.indexOf("http://") <=-1 && url.indexOf("https://")<=-1) url = "http://"+url;
			
			url = url.replaceAll("[\\t\\n\\r\\f\\e]*", ""); // replace all whitespace
			
			URL theURL = new URL(url);
			String host = theURL.getHost();
			String path = theURL.getPath();
			String query = theURL.getQuery();
			String protocol = theURL.getProtocol();
			if(protocol==null||protocol.isEmpty()) protocol = "http";
			int port = theURL.getPort();
			String user = theURL.getUserInfo();

			//
			// 2. Process the hostname
			// 
			// 2a. Unescape until no more hex-encodings
			
			logger.debug("pre-escaped host:  " + host);
			host = unescape(host);
			logger.debug("post-escaped host:  " + host);
			
			host = decodeHost(host);
			logger.debug("decoded host:  " + host);
			
			
		
			// 2b. Escape non-standard characters (escape once).
			// Note: When escaping the hostname we have less characters allowed unescaped
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < host.length(); i++) {
				char c = host.charAt(i);
				if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || c == '.' || c == '-')
					sb.append(c);
				else
					sb.append(codec.encode(String.valueOf(c))); // Escape using UTF-8
			}
			host = sb.toString();

			// 2c. Remove leading and trailing dots 
			while (host.startsWith("."))
				host = host.substring(1);
			while (host.endsWith("."))
				host = host.substring(0, host.length() - 1);

			// 2d. Replace consecutive dots with a single dot
			int p = 0;
			while ((p = host.indexOf("..")) != -1)
				host = host.substring(0, p + 1) + host.substring(p + 2);

			

			// 2f. Add trailing slash if path is empty
			if ("".equals(path))
				host = host + "/";
			
			
			Pattern pattern =  Pattern.compile("([a-z]{1})([0-9]{2})");
			 
		       Matcher matcher = pattern.matcher(host);
		       String val = "$2";
		       while (matcher.find()) {
	               logger.debug(":::TEXT FOUND "+  matcher.group() + " tarting at " +
	                   "index " +  matcher.start() + "  and ending at index " + matcher.end());
	               host = matcher.replaceAll(val);
	              
	               logger.debug("::TEXT " + host);
		       }
			
			
			
			
		       host = host.replaceAll("(?i)%5C", "%");
		       
			
		       logger.debug("post-processed host:  " + host);
			
			//
			// Process the path
			//
			// 3a. Unescape until no more hex-encodings
			logger.debug("pre-escaped path:  " + path);
			
			path = unescape(path);
			
			logger.debug("post-escaped path:  " + path);
			
			
			/*
			// 3b. Remove consecutive slashes from path
			while ((p = path.indexOf("//")) != -1)
				path = path.substring(0, p + 1) + path.substring(p + 2);

			// 3b. Remove /./ occurences from path
			while ((p = path.indexOf("/./")) != -1)
				path = path.substring(0, p + 1) + path.substring(p + 3);

			// 3c. Resolve /../ occurences in path
			while ((p = path.indexOf("/../")) != -1) {
				int previousSlash = path.lastIndexOf("/", p-1);
				// if (previousSlash == -1) previousSlash = 0; // If path begins with /../
				path = path.substring(0, previousSlash) + path.substring(p + 3);
				p = previousSlash;
			}
			
			while ((p = path.indexOf("/..")) != -1) {
				int previousSlash = path.lastIndexOf("/", p-1);
				// if (previousSlash == -1) previousSlash = 0; // If path begins with /../
				path = path.substring(0, previousSlash) + path.substring(p + 3);
				p = previousSlash;
			}
			
			
			
			
			*/
			
			

			
			URI uri = null;
			try {
				
				/* only normalise if the host doesn't contain some odd hex */
				if(!host.contains("%") && !host.matches("[\\s].*")) { 
					logger.debug("normalising URL......");
					uri = new URI(protocol, user, host, -1, path, query, null);
					logger.debug(uri.normalize().getPath().toString() + " <-- normalised path");
				}
				
			} catch (URISyntaxException exp) {
				
				try {
					
					/* only normalise if the host doesn't contain some odd hex */
					if(!host.contains("%") && !host.matches(".*[\\s].*")) { 
						logger.debug("error normalising URL");
						uri = new URI(protocol, user, unescape(host), -1, path, query, null);
						logger.debug(uri.normalize().getPath().toString() + " <-- normalised path after error debug");
					}
				} catch (URISyntaxException e) {
					
					// total fail.
				}
			}
			
			
			if(uri!=null && !uri.normalize().getPath().toString().trim().isEmpty()) {
				logger.debug("normalized path is: empty!");
				path = uri.normalize().getPath().toString();
			}  
				
			logger.debug("post-cleaned path:  " + path);
			logger.debug("post-processed host:  " + host);
			
			
			
			
			// 3d. Escape once
			path = escape(path);
			path = path.replaceAll("[;]*", ""); // replace all semi-colons
			
			
			// 
			// Process the query
			//
			// 4a. Unescape until no more hex-encodings
			query = unescape(query);

			// 4b. Escape once
			query = escape(query);

			//
			// Rebuild the URL
			//
			sb.setLength(0);
			sb.append(protocol + ":");
		
			sb.append("//");
			if (user != null)
				sb.append(user + "@");
			
			
			
			if (port != -1) {
				/* remove slash from host */
				logger.debug("removing last slash: " + host.lastIndexOf("/",host.length()));
				
				if(host.lastIndexOf("/",host.length())>8) {
					logger.debug("new host name: " + host.substring(0,host.length()-1));
					
					host = host.substring(0,host.length()-1);
				}
				sb.append(host);
				sb.append(":");
				sb.append(port);
			} else {
				sb.append(host);
			}
			
			/* make sure any hashes are re-encoded back to %23*/
			path = path.replaceAll("#","%23");
			
			sb.append(path);
			if(sb.toString().endsWith("//")) sb = sb.replace(sb.length()-1, sb.length(), "");
			
			if (query != null)
				sb.append("?" + query);
			
		
		
			url = sb.toString();
			
			

			logger.debug("canonicalised url is:  " + url);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GSBException("Could not canonicalise URL: " + queryURL);
		}
		
		
		logger.debug("final decoding URL: " + url);

		return url;
	}
	
	/** Escapes a string by replacing characters having ASCII <=32, >=127, or % with their UTF-8-escaped codes 
	 * 
	 * @param url
	 * @return escaped url
	 * @author Henrik Sjostrand, Netvouz, http://www.netvouz.com/, info@netvouz.com
	 */
	private String escape(String url) throws GSBException {
		if (url == null)
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < url.length(); i++) {
			char c = url.charAt(i);
			if (c == ' ')
				sb.append("%20");
			else if (c <= 32 || c >= 127 || c == '%') {
				//try {
					sb.append(codec.encode(String.valueOf(c)));
				//} catch (UnsupportedEncodingException e) {
				//	throw new GSBException("escapeUTF8: Could not encode character " + c + " at position " + i + " in " + url + ". ErrMsg=" + e.toString());
				//}
			} else
				sb.append(c);
		}
		return sb.toString();
	}
	
	/** Parses the query URL into several different url strings, each of which should be looked up 
	 * @author Henrik Sjostrand, Netvouz, http://www.netvouz.com/, info@netvouz.com
	 * @param queryURL
	 * @return
	 */
	public ArrayList<CheckURL> getLookupURLs(String queryURL) throws GSBException {
		ArrayList<CheckURL> urls = new ArrayList<CheckURL>();
		if (queryURL != null) {
			try {
				String canonicalizedURL = canonicalizeURL(queryURL);
				if (canonicalizedURL != null) { // canonicalizeURL & unescapeUTF8 returns null on invalid strings, and then we don't add any URLs to the lookup list which means we consider this URL safe
					URL url = new URL(canonicalizedURL);
					String host = url.getHost();
					String path = url.getPath();
					String query = url.getQuery();
					if (query != null)
						query = "?" + query;

					// Generate a list of the hosts to test (exact hostname plus up to four truncated hostnames) 
					ArrayList<String> hosts = new ArrayList<String>();
					hosts.add(host); // Should always test the exact hostname
					String[] hostArray = host.split("\\.");
					StringBuffer sb = new StringBuffer();
					int start = (hostArray.length < 6 ? 1 : hostArray.length - 5);
					int stop = hostArray.length;
					for (int i = start; i < stop - 1; i++) {
						sb.setLength(0);
						for (int j = i; j < stop; j++)
							sb.append(hostArray[j] + ".");
						sb.setLength(sb.length() - 1); // Trim trailing dot
						hosts.add(sb.toString());
					}

					// Generate a list of paths to test
					ArrayList<String> paths = new ArrayList<String>();
					if (query != null)
						paths.add(path + query); // exact path including query
					paths.add(path); // exact path excluding query
					if (!paths.contains("/"))
						paths.add("/");

					int maxCount = (query == null ? 5 : 6);
					String pathElement = "/";
					StringTokenizer st = new StringTokenizer(path, "/");
					while (st.hasMoreTokens() && paths.size() < maxCount) {
						String thisToken = st.nextToken();
						pathElement = pathElement + thisToken + (thisToken.indexOf(".") == -1 ? "/" : "");
						if (!paths.contains(pathElement))
							paths.add(pathElement);
					}

					for (int i = 0; i < hosts.size(); i++) {
						for (int j = 0; j < paths.size(); j++)
							urls.add(new CheckURL(hosts.get(i).toString() + paths.get(j).toString()));
					}

				}
			} catch (Exception e) {
				throw new GSBException("Could not generate lookup URLs");
			}
		}
		return urls;
	}
	
}
