package uk.co.mccann.gsb.interfaces;

import java.util.HashMap;

import uk.co.mccann.gsb.exceptions.GSBException;
import uk.co.mccann.gsb.model.ListURL;

/**
 * GSBDAO
 * Read and write GSB data to and from a data source
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
public interface GSBDAO {
	
	public HashMap<String, ListURL> readBlacklist(GSBEngineConfiguration config) throws GSBException;
	
	public HashMap<String, ListURL> readMalwarelist(GSBEngineConfiguration config) throws GSBException;
	
	public boolean locateBlacklistHash(String hash) throws GSBException;
	
	public boolean locateMalwareHash(String hash) throws GSBException;
	
	public void updateBlacklist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException;
	
	public void updateMalwarelist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException;
	
	public void replaceBlacklist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException;
	
	public void replaceMalwarelist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException;
	
}
