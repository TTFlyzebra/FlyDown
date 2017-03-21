package com.flyzebra.flydown.request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月22日 下午3:11:27  
*/
public class SimpleFileBlockQueue implements IFileBlockQueue {
	private BlockingQueue<FileBlock> fileBlockQueue = new LinkedBlockingQueue<>();
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doNextQueue() {
		// TODO Auto-generated method stub

	}

	@Override
	public SimpleFileBlockQueue setUrl(String downUrl) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public SimpleFileBlockQueue setSaveFile(String saveFile) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public SimpleFileBlockQueue setTempFile(String tempFile) {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public SimpleFileBlockQueue listener(IFileBlockReQuestListener iFileBlockReQuestListener) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


}
