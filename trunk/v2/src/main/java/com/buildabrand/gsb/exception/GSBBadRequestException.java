package com.buildabrand.gsb.exception;

/**
 * GSBBadRequestException
 * Specific exception for bad request errors (400) returned by Google.
 * The HTTP request was not correctly formed. The client did not provide all required CGI parameters.
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
public class GSBBadRequestException extends GSBException {

	private static final long serialVersionUID = 1L;

	public GSBBadRequestException() {
		super();
	}

	public GSBBadRequestException(String arg0) {
		super(arg0);
	}
	
}
