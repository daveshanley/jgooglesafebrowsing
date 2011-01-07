package com.buildabrand.gsb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * SubChunkData
 * Chunk data bean for sub chunks
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
public class SubChunkData extends ChunkData {
	
	private List<String> addChunkNum;
	
	public Integer getType() {
		return ChunkData.S_TYPE;
	}
	
	public List<String> getAddChunkNum() {
		return addChunkNum;
	}

	public void setAddChunkNum(List<String> addChunkNum) {
		this.addChunkNum = addChunkNum;
	}

	public SubChunkData() {
		super();
		this.addChunkNum = new ArrayList<String>();
	}
}
