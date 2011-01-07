package uk.co.mccann.gsb.model;

import uk.co.mccann.gsb.exceptions.GSBException;

/**
 * MySQLDAO
 * Use MySQL database (loads MySQL driver)
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
