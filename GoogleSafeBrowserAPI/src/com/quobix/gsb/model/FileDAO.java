package com.quobix.gsb.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.interfaces.GSBDAO;
import com.quobix.gsb.interfaces.GSBEngineConfiguration;

public class FileDAO implements GSBDAO {

	public HashMap<String, ListURL> readBlacklist(GSBEngineConfiguration config) throws GSBException {
		
		/* read in existing list */
		try {
			
			HashMap<String, ListURL> existingList = new HashMap<String, ListURL>();
			BufferedReader reader = new BufferedReader(new FileReader(config.getDataStoreDirectory() + "gsb.blacklist"));
			String s;
			/* populate existing map */
			while((s = reader.readLine())!=null) {
				existingList.put(s, new ListURL(s));
			}
			
			return existingList;
			
		} catch (IOException exp)  {
			
			throw new GSBException("cannot read blacklist file: " + config.getDataStoreDirectory() + "gsb.blacklist");
		}
		
	}

	public HashMap<String, ListURL> readMalwarelist(GSBEngineConfiguration config) throws GSBException {
		/* read in existing list */
		try {
			
			HashMap<String, ListURL> existingList = new HashMap<String, ListURL>();
			BufferedReader reader = new BufferedReader(new FileReader(config.getDataStoreDirectory() + "gsb.malwarelist"));
			String s;
			/* populate existing map */
			while((s = reader.readLine())!=null) {
				existingList.put(s, new ListURL(s));
			}
			
			return existingList;
			
		} catch (IOException exp)  {
			
			throw new GSBException("cannot read malwarelist file: " + config.getDataStoreDirectory() + "gsb.malwarelist");
		}
	}

	public void replaceBlacklist(HashMap<String, ListURL> list,GSBEngineConfiguration config) throws GSBException {
		
		/* replace everything */
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(config.getDataStoreDirectory() + "gsb.blacklist"));
			
			for(String key : list.keySet()) {
				
				writer.write(list.get(key).getMD5Hash() + "\n");
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException exp)  {
			
			throw new GSBException("cannot write blacklist file: " + config.getDataStoreDirectory() + "gsb.blacklist");
		}

	}

	public void replaceMalwarelist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		
		/* replace everything */
		try {
		
			BufferedWriter writer = new BufferedWriter(new FileWriter(config.getDataStoreDirectory() + "gsb.malwarelist"));
			
			for(String key : list.keySet()) {
			
				writer.write(list.get(key).getMD5Hash() + "\n");
			}
		
			writer.flush();
			writer.close();
		
		} catch (IOException exp)  {
		
			throw new GSBException("cannot write blacklist file: " + config.getDataStoreDirectory() + "gsb.malwarelist");
		}

	
	}

	public void updateBlacklist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		/* read in existing list and update */
		try {
			
			HashMap<String, ListURL> existingList = new HashMap<String, ListURL>();
			HashMap<String, ListURL> mergedList = new HashMap<String, ListURL>();
			BufferedReader reader = new BufferedReader(new FileReader(config.getDataStoreDirectory() + "gsb.blacklist"));
			String s;
			
			/* populate existing map */
			while((s = reader.readLine())!=null) {
				existingList.put(s, new ListURL(s));
			}
			
			/* iterate through map (for removals) */
			for(String key : existingList.keySet()) {
				
				/* look for an item from the updated list */
				if(list.get(key)!=null) {
					/* it should be a remove option, but check anyway */
					if(!list.get(key).isRemoved()) {
						mergedList.put(key,existingList.get(key));
					}
				} else {
					mergedList.put(key,existingList.get(key));
				}
			}
			
			/* re-iterate through new map (for new additions) */
			for(String key : list.keySet()) {
				
				/* look for an item from the updated list */
				if(existingList.get(key)==null) {
					System.out.println("not found: " + list.get(key).getMD5Hash());
					/* it should be a new addition option, but check anyway */
					if(list.get(key).isAdded()) {
						mergedList.put(key, list.get(key));
					}
				} 
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(config.getDataStoreDirectory() + "gsb.blacklist"));
			
			for(String key : mergedList.keySet()) {
				
				writer.write(mergedList.get(key).getMD5Hash() + "\n");
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException exp)  {
			
			throw new GSBException("cannot write blacklist file: " + config.getDataStoreDirectory() + "gsb.blacklist");
		}

	}

	public void updateMalwarelist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		/* read in existing list and update */
		try {
			
			HashMap<String, ListURL> existingList = new HashMap<String, ListURL>();
			HashMap<String, ListURL> mergedList = new HashMap<String, ListURL>();
			BufferedReader reader = new BufferedReader(new FileReader(config.getDataStoreDirectory() + "gsb.malwarelist"));
			String s;
			
			/* populate existing map */
			while((s = reader.readLine())!=null) {
				existingList.put(s, new ListURL(s));
			}
			
			/* iterate through map (for removals) */
			for(String key : existingList.keySet()) {
				
				/* look for an item from the updated list */
				if(list.get(key)!=null) {
					/* it should be a remove option, but check anyway */
					if(!list.get(key).isRemoved()) {
						mergedList.put(key,existingList.get(key));
					}
				} else {
					mergedList.put(key,existingList.get(key));
				}
			}
			
			/* re-iterate through new map (for new additions) */
			for(String key : list.keySet()) {
				
				/* look for an item from the updated list */
				if(existingList.get(key)==null) {
					/* it should be a new addition option, but check anyway */
					if(list.get(key).isAdded()) {
						mergedList.put(key, list.get(key));
					}
				} 
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(config.getDataStoreDirectory() + "gsb.malwarelist"));
			
			for(String key : mergedList.keySet()) {
				
				writer.write(mergedList.get(key).getMD5Hash() + "\n");
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException exp)  {
			
			throw new GSBException("cannot write malware file: " + config.getDataStoreDirectory() + "gsb.malwarelist");
		}

	}

}
