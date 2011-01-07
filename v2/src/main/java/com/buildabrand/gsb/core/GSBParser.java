package com.buildabrand.gsb.core;

/**
 * GSBParser
 * Parser for list chunk data.
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
public class GSBParser {

	private static GSBParser instance = null;
	
	private GSBParser() { }
	
	public static GSBParser getInstance() {
		if(instance==null) {
			return new GSBParser();
		}
		return instance;
	}
	
	//public List<ChunkData>
	
	
}
