package com.buildabrand.gsb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ChunkData
 * Generic Chunk bean
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) Buildabrand Ltd, 2011 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <dave@buildabrand.com>
 */
public abstract class ChunkData {
	
	public static Integer A_TYPE = 1;
	public static Integer S_TYPE = 2;
	
	
	private String hostKey;
	private Integer count;
	private String checkNum;
	private String hashLen;
	private String chunkLen;
	private String realCount;
	private List<String> pairPrefixes;
	
	abstract Integer getType();
	
	public ChunkData() {
		this.pairPrefixes = new ArrayList<String>();
	}
	
	public String getHostKey() {
		return hostKey;
	}
	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getHashLen() {
		return hashLen;
	}
	public void setHashLen(String hashLen) {
		this.hashLen = hashLen;
	}
	public String getChunkLen() {
		return chunkLen;
	}
	public void setChunkLen(String chunkLen) {
		this.chunkLen = chunkLen;
	}
	public String getRealCount() {
		return realCount;
	}
	public void setRealCount(String realCount) {
		this.realCount = realCount;
	}
	public List<String> getPairPrefixes() {
		return pairPrefixes;
	}
	public void setPairPrefixes(List<String> pairPrefixes) {
		this.pairPrefixes = pairPrefixes;
	}
	
	
	
	
}
