package com.buildabrand.gsb.exception;

/**
 * GSBTooManyRequestsException
 * Specific exception for too many requests (503) returned by Google.
 * The server cannot handle the request. Clients MUST follow the backoff behavior specified in the Request Frequency section.
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
public class GSBTooManyRequestsException extends GSBException {

	private static final long serialVersionUID = 1L;

	public GSBTooManyRequestsException() {
		super();
	}

	public GSBTooManyRequestsException(String arg0) {
		super(arg0);
	}
	
}
