package com.flyzebra.flydown.dataio;

import java.util.Vector;

import com.flyzebra.flydown.utils.EncodeHelper;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 下午2:27:13  
*/
public class FileInfo {
	
	private String saveName = null;
	
	private Vector<FileBlock> fileBlocks = null;
	
	public FileInfo(String url){
		String md5 = EncodeHelper.md5(url);
		saveName = "."+md5+".tmp";
		fileBlocks = new Vector<>();
	}
	
	public static FileInfo create(String url){
		return new FileInfo(url);
	}
}
