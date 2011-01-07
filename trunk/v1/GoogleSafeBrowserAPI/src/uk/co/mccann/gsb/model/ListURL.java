package uk.co.mccann.gsb.model;

import uk.co.mccann.gsb.interfaces.GSBURL;

/**
 * ListURL
 * Representation of hashed URL from persistent storage or from webservice.
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
public class ListURL implements GSBURL {
	
	protected String md5Hash;
	protected int status = 1;
	
	public ListURL() {
	}
	
	public ListURL(String hash) {
		this.md5Hash = hash;
	}
	
	public void setMD5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
		
	}
	
	public String getMD5Hash() {
		return this.md5Hash;
	}

	public void setStatus(int status) {
		this.status = status;
		
	}

	public boolean isAdded() {
		if(this.status == 1) return true;
		return false;
	}

	public boolean isRemoved() {
		if(this.status == 1) return false;
		return true;
	}
		
}
