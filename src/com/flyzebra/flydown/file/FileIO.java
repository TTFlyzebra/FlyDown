package com.flyzebra.flydown.file;

import java.io.IOException;
import java.io.RandomAccessFile;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月21日 上午11:22:34  
*/
public class FileIO implements IFileIO{
	private RandomAccessFile mRandomAccessFile;

	
	public FileIO(String fileName) throws IOException{
		mRandomAccessFile = new RandomAccessFile(fileName, "rw");
	}
	
	@Override
	public void save(byte[] b, long start, long len) {
		try {
			mRandomAccessFile.seek(start);
			mRandomAccessFile.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
