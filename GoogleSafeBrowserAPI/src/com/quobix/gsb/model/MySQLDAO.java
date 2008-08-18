package com.quobix.gsb.model;

import com.quobix.gsb.exceptions.GSBException;

public class MySQLDAO extends JDBCDAO {

	public MySQLDAO(String user, String password, String host, String database) throws GSBException {
		
		super(user,password,host,database);
		
		try {
			
		    String driverName = "com.mysql.jdbc.Driver";
	        Class.forName(driverName);
	    
	        this.connectionURL = "jdbc:mysql://" + this.host +  "/" + this.database; 
	       
		} catch (ClassNotFoundException e) {
	    
			throw new GSBException("MySQL Driver not found, make sure it's in your classpath!");
			
	    } 
		
	}

	
}
