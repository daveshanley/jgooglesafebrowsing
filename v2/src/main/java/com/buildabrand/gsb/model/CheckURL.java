package com.buildabrand.gsb.model;


import com.buildabrand.gsb.util.Hashable;

/**
 * CheckURL
 * Type of URL that is used to check against existing hashes
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
public class CheckURL extends ListURL implements Hashable {

	private String url;
	
	/**
	 * Constructor.
	 * @param url
	 */
	public CheckURL(String url) {
		super();
		//this.md5Hash = this.generateMD5Hash(url.toString());
		this.url = url;
	}
	
	/**
	 * Get URL being checked
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Set the URL to be checked.
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Generate an MD5 Hash of the URL.
	 */
	public String generateSHA256Hash(String hashString) {
		return null;
		
		/*
		try { 
			//Hashable hasher =  MD5HasherImpl.getInstance();
			//this.md5Hash = hasher.generateMD5Hash(hashString);
			//return this.md5Hash;
		
		} catch (NoSuchAlgorithmException exp) {
		
			return null;
		
		}
		*/
	}
	
	
}
