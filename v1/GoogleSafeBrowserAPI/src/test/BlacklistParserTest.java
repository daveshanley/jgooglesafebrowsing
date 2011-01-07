package test;

import java.util.HashMap;

import uk.co.mccann.gsb.engine.util.AbstractParser;
import uk.co.mccann.gsb.interfaces.GSBParser;
import uk.co.mccann.gsb.model.ListURL;


import junit.framework.TestCase;

public class BlacklistParserTest extends TestCase {

	public String data  = "[goog-black-hash 1.13363][mac=3sG47Yl3Fm/bTzg7eZ8Rxw==]\n" + 
						 "+000663058ad7b540a8f6362a72a685cf\n" + 	
						 "+0006a2d7191585560b05d6ac5afa113b\n" + 	
						 "+000758268ab4cc1564046b55ccc96389\n" + 	
						 "+00077db14492e19056fe162c43508eb2\n" + 	
						 "+0011c823b5974ead3dbe3e040207df6c";	
	
	
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


	
	
	
	public void testExtractMajorVersionString() {
		//fail("Not yet implemented");
		
		GSBParser parser = new AbstractParser();
		Integer version = parser.extractMajorVersion(data);
		
		assertEquals(version, new Integer(1));
		
	}

	public void testExtractMajorVersionBufferedReader() {
		
		
	}

	public void testExtractMinorVersionString() {
		
		GSBParser parser = new AbstractParser();
		Integer version = parser.extractMinorVersion(data);
		
		assertEquals(version, new Integer(13363));
	
	}

	public void testExtractMinorVersionBufferedReader() {
		//fail("Not yet implemented");
	}

	public void testParseResponseString() {
		
		GSBParser parser = new AbstractParser();
		HashMap<String, ListURL> list = parser.parseResponse(serverTest, "MTrILkq3LDJtWp8V8zHJaJc2");
		assertEquals(list.size(), 14);
	}

	public void testParseResponseBufferedReader() {
		//fail("Not yet implemented");
	}

}
