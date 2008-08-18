package test;

import java.util.HashMap;

import com.quobix.gsb.engine.util.AbstractParser;
import com.quobix.gsb.exceptions.GSBException;
import com.quobix.gsb.interfaces.GSBDAO;
import com.quobix.gsb.interfaces.GSBEngineConfiguration;
import com.quobix.gsb.interfaces.GSBParser;
import com.quobix.gsb.model.ConfigImpl;
import com.quobix.gsb.model.FileDAO;
import com.quobix.gsb.model.ListURL;

import junit.framework.TestCase;

public class FileDAOTest extends TestCase {

	public String serverTest = "[goog-black-hash 1.13367 update][mac=rmjSwH+NpZrPa93giULH6g==]" + 
    "-18589555476a9585f699ce7d91a0f037" + 
    "-1ce09e8326c6e1946c6bc655091dd8d9\n" + 
    "-23781e8fb5b50ad9546d40c327466362\n" + 
    "-3abaee712c6f2bfa327dc4f6f13dd0b4\n" + 
    "-3bb534ceb2dec2116f7a9d7073334359\n" + 
    "-49c3faae2340455a8eede81a08f9ac2b\n" + 
    "-502dfd912fadc57409f495bcd692c86b\n" + 
    "-8b1c091968bd6b388e6922276e2836b6\n" + 
    "-99df1f6a881a2a9687352464ea8f59a6\n" + 
    "-a42e13758393f382ef0eb67006b2da17\n" + 
    "-b1a7e1bea65a19370fcefa69d489a715\n" + 
    "-b62c8105ee55584a7a80dec542d0d260\n" + 
    "-c462a4306e5a4acc5475f16d25f0240f\n" + 
    "-d80e19ade1e1c44e69f3f0e7dd55e102\n";

	
	public String serverTestUpdate = "[goog-black-hash 1.13367 update][mac=rmjSwH+NpZrPa93giULH6g==]" + 
    "+18589555476a9585f699ce7d91a0f037" + 
    "+c462a4306e5a4acc5475f16d25f0240f\n" + 
    "-abc123abc123abc123abc123abc123ab\n" + 
    "-991def991def991def991def991def99\n"; 
    
    
	
	public void testReplaceBlacklist() {
		
		/*
		
		GSBDAO dao = new FileDAO();
		GSBParser parser = new BlacklistParser();
		
		GSBEngineConfiguration config;
		try {
			config = new ConfigImpl("/tmp/gsb/");
		
		config.setAPIKey("abc");
		config.useKey(true);
		
		HashMap<String, ListURL> list = parser.parseResponse(serverTest, "MTrILkq3LDJtWp8V8zHJaJc2");
		dao.replaceBlacklist(list, config);
		
		} catch (GSBException e) {
			e.printStackTrace();
			fail();
		}
		*/
	}
	
	public void testUpdateBlacklist() {
		
		/*
		GSBDAO dao = new FileDAO();
		GSBParser parser = new AbstractParser();
		
		GSBEngineConfiguration config;
		try {
			config = new ConfigImpl("/tmp/gsb/");
		
		config.setAPIKey("abc");
		config.useKey(true);
		
		HashMap<String, ListURL> list = parser.parseResponse(serverTestUpdate, "MTrILkq3LDJtWp8V8zHJaJc2");
		dao.updateBlacklist(list, config);
		
		} catch (GSBException e) {
			e.printStackTrace();
			fail();
		}
		*/
	}
	
	public void testReadMalwarelist() {
		
		
		GSBDAO dao = new FileDAO();
		
		GSBEngineConfiguration config;
		try {
			config = new ConfigImpl("/tmp/gsb/");
		
		config.setAPIKey("abc");
		config.useKey(true);
		
		HashMap<String, ListURL> list = dao.readMalwarelist(config);
		assertEquals(list.size(), 160086);
		
		} catch (GSBException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	

}
