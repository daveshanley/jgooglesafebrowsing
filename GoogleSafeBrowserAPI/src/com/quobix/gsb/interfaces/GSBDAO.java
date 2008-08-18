package com.quobix.gsb.interfaces;

import java.util.HashMap;

import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.model.ListURL;

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
