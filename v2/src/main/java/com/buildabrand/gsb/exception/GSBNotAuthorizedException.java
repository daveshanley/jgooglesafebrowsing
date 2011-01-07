package com.buildabrand.gsb.exception;

/**
 * GSBNotAuthorizedException
 * Specific exception for invalid client id error (401) returned by Google.
 * The client id is invalid.
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
public class GSBNotAuthorizedException extends GSBException {

	private static final long serialVersionUID = 1L;

	public GSBNotAuthorizedException() {
		super();
	}

	public GSBNotAuthorizedException(String arg0) {
		super(arg0);
	}
	
}
