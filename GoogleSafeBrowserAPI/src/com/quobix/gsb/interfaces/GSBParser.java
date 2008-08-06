package com.quobix.gsb.interfaces;

import java.io.BufferedReader;
import java.util.HashMap;

import com.quobix.gsb.model.ListURL;

public interface GSBParser {
	
	public HashMap<String, ListURL> parseResponse(String response, String key);
	
	public HashMap<String, ListURL> parseResponse(BufferedReader response, String key);
	
	public Integer extractMinorVersion(String response);
	
	public Integer extractMinorVersion(BufferedReader response);
	
	public Integer extractMajorVersion(String response);
	
	public Integer extractMajorVersion(BufferedReader response);
	
	public boolean isUpdate(String response);
	
	public boolean isUpdate(BufferedReader response);
	
	
}
