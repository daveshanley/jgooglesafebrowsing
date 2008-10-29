package uk.co.mccann.gsb.exceptions;

/**
 * GSBException
 * Generic GSB Exception that may be thrown by any number of classes
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
public class GSBException extends Exception {

	public GSBException() {
		super();
	}

	public GSBException(String arg0) {
		super(arg0);
	}

}
