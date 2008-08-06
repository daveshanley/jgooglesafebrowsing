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

public class MySQLDAO implements GSBDAO {
	
	private String user;
	private String pass;
	private String database;
	private String host;
	private Connection connection = null;
	private String connectionURL;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public MySQLDAO(String user, String password, String host, String database) throws GSBException {
		
		this.user = user;
		this.pass = password;
		this.host = host;
		this.database = database;
		
		try {
			
		    String driverName = "com.mysql.jdbc.Driver";
	        Class.forName(driverName);
	    
	        this.connectionURL = "jdbc:mysql://" + this.host +  "/" + this.database; 
	        this.connection = DriverManager.getConnection(this.connectionURL, this.user, this.pass);
	   
		} catch (ClassNotFoundException e) {
	    
			throw new GSBException("MySQL Driver not found, make sure it's in your classpath!");
			
	    } catch (SQLException e) {

	    	throw new GSBException("Unable to connect to the database: " + e.getMessage());
					
	    }
		
	}
	
	
	public HashMap<String, ListURL> readBlacklist(GSBEngineConfiguration config) throws GSBException {
		try {
			
			this.stmt = this.connection.createStatement();
			String query = "select * from blacklist";
			ResultSet result = stmt.executeQuery(query);
			
			HashMap<String, ListURL> dataMap = new HashMap<String, ListURL>();
			
			while(result.next()) {
				
				String hash = result.getString("hash");
				dataMap.put(hash, new ListURL(hash));
			} 
			
			this.connection.close();
			return dataMap;
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to read blacklist: " + exp.getMessage());
		}
		
	}

	public HashMap<String, ListURL> readMalwarelist(GSBEngineConfiguration config) throws GSBException {
		
			try {
			
			this.stmt = this.connection.createStatement();
			String query = "select * from malware";
			ResultSet result = stmt.executeQuery(query);
			
			HashMap<String, ListURL> dataMap = new HashMap<String, ListURL>();
			
			while(result.next()) {
				
				String hash = result.getString("hash");
				dataMap.put(hash, new ListURL(hash));
			} 
			
			this.connection.close();
			return dataMap;
			
		} catch (SQLException exp) {
			
			throw new GSBException("Unable to read blacklist: " + exp.getMessage());
		}
	}

	public void replaceBlacklist(HashMap<String, ListURL> list,
			GSBEngineConfiguration config) throws GSBException {
		// TODO Auto-generated method stub

	}

	public void replaceMalwarelist(HashMap<String, ListURL> list,
			GSBEngineConfiguration config) throws GSBException {
		// TODO Auto-generated method stub

	}

	public void updateBlacklist(HashMap<String, ListURL> list, GSBEngineConfiguration config) throws GSBException {
		
		
		

	}

	public void updateMalwarelist(HashMap<String, ListURL> list,
			GSBEngineConfiguration config) throws GSBException {
		// TODO Auto-generated method stub

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

}
