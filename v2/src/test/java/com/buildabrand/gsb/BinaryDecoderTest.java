package com.buildabrand.gsb;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;

import com.buildabrand.gsb.core.GSBParser;

public class BinaryDecoderTest {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void loadBinaryFile() {
		
		try {
			
			 URL url = ClassLoader.getSystemResource("test_binary");
			 byte[] bytes = IOUtils.toByteArray(FileUtils.openInputStream(new File(url.toURI())));
			 assertNotNull(bytes);
			
		} catch (Exception exp) {
			fail("failed!: " + exp.getMessage());
			
		}
	}
	
	
	@Test
	public void decodeBinaryData() {
		
		try {
			
			Hex hex = new Hex();
			URL url = ClassLoader.getSystemResource("test_binary");
			String content = FileUtils.readFileToString(new File(url.toURI()));
			
			assertNotNull(content);
			
			
			
			String[] data = content.split("\n",2); 
			
			assertEquals(2, data.length);
			
			logger.debug("header: " + data[0]);
			
			data = (String[])ArrayUtils.remove(data, 0);
			
			assertNotSame("a:30401:4:150", data[0]);
			
			String allData = data[0];
			
			String chunkdata = new String(Hex.encodeHex(allData.trim().substring(0, 150).getBytes()));
			logger.debug(chunkdata);
			
			logger.debug("----- next chunk-------");
			
			allData = allData.substring(150);
			
			data = allData.split("\n",2); 
			
			assertEquals(2, data.length);
			
			logger.debug("header: " + data[0]);
			
			data = (String[])ArrayUtils.remove(data, 0);
			
			allData = data[0];
			
			chunkdata = new String(Hex.encodeHex(allData.trim().substring(0, 150).getBytes()));
			logger.debug(chunkdata);
			
			GSBParser parser= GSBParser.getInstance();
			
			//logger.debug(parser.test());
			
			
			//char[] chars = hex.encodeHex(arg0);
			
			
			
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed!: " + exp.getMessage());
			
		}
	}
	
}
