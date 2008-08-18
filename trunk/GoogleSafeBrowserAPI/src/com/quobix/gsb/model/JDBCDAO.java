package com.quobix.gsb.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.management.Query;

import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.interfaces.GSBDAO;
import com.quobix.gsb.interfaces.GSBEngineConfiguration;

public abstract class JDBCDAO implements GSBDAO {
	
	protected String user;
	protected String pass;
	protected String database;
	protected String host;
	protected String connectionURL;
	
	private ResultSet rs = null;
	
	public JDBCDAO(String user, String password, String host, String database) throws GSBException {
		
		this.user = user;
		this.pass = password;
		this.host = host;
		this.database = database;
		
	}
	
	
	public HashMap<String, ListURL> readBlacklist(GSBEngineConfiguration config) throws GSBException {
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			String query = "select * from blacklist";
			ResultSet result = statement.executeQuery(query);
			
			HashMap<String, ListURL> dataMap = new HashMap<String, ListURL>();
			
			while(result.next()) {
				
				String hash = result.getString("hash");
				dataMap.put(hash, new ListURL(hash));
			} 
			
			connection.close();
			return dataMap;
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to read blacklist: " + exp.getMessage());
		}
		
	}

	public HashMap<String, ListURL> readMalwarelist(GSBEngineConfiguration config) throws GSBException {
		
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			String query = "select * from malware";
			ResultSet result = statement.executeQuery(query);
			
			HashMap<String, ListURL> dataMap = new HashMap<String, ListURL>();
			
			while(result.next()) {
				
				String hash = result.getString("hash");
				dataMap.put(hash, new ListURL(hash));
			} 
			
			connection.close();
			return dataMap;
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to read blacklist: " + exp.getMessage());
		}
	}

	public void replaceBlacklist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			
			/* clear out existing */
			statement.executeUpdate("delete from blacklist");
			
			ListURL url;
			for(String key : list.keySet()) {
				url = list.get(key);
				
				/* add new */
				statement.executeUpdate("insert into blacklist (hash) values (\"" + url.getMD5Hash() + "\")");	
			}
			
			
			connection.close();
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to replace blacklist: " + exp.getMessage());
		}

	}

	public void replaceMalwarelist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			
			/* clear out existing */
			statement.executeUpdate("delete from malware");
			
			ListURL url;
			for(String key : list.keySet()) {
				url = list.get(key);
				
				/* add new */
				statement.executeUpdate("insert into malware (hash) values (\"" + url.getMD5Hash() + "\")");	
			}
			
			
			connection.close();
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to replace malware: " + exp.getMessage());
		}

	}

	public void updateBlacklist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		
		
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			
			
			ListURL url;
			for(String key : list.keySet()) {
				url = list.get(key);
				
				if(url.isAdded()) {
					/* add new */
					statement.executeUpdate("insert into blacklist (hash) values (\"" + url.getMD5Hash() + "\")");
				}
				if(url.isRemoved()) {
					/* add new */
					statement.executeUpdate("delete from blacklist where hash =  \"" + url.getMD5Hash() + "\"");
				}
			}
			
			
			connection.close();
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to update blacklist: " + exp.getMessage());
		}

	}

	public void updateMalwarelist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			
			
			ListURL url;
			for(String key : list.keySet()) {
				url = list.get(key);
				
				if(url.isAdded()) {
					/* add new */
					statement.executeUpdate("insert into malware (hash) values (\"" + url.getMD5Hash() + "\")");
				}
				if(url.isRemoved()) {
					/* add new */
					statement.executeUpdate("delete from malware where hash =  \"" + url.getMD5Hash() + "\"");
				}
			}
			
			
			connection.close();
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to update malware: " + exp.getMessage());
		}

	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public boolean locateBlacklistHash(String hashValue) throws GSBException {
		
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			String query = "select * from blacklist where hash = \"" + hashValue + "\"";
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				return true;
			} 
			
			connection.close();
			return false;
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to read blacklist: " + exp.getMessage());
		}
		
		
	}

	public boolean locateMalwareHash(String hashValue) throws GSBException {
		try {
			
			Connection connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
			Statement statement = connection.createStatement();
			String query = "select * from malware where hash = \"" + hashValue + "\"";
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				return true;
			} 
			
			connection.close();
			return false;
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to read blacklist: " + exp.getMessage());
		}
	}

}
