package com.flyzebra.flydown.request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.flyzebra.flydown.file.FileBlock;
import com.flyzebra.flydown.network.IHandleTask;
import com.flyzebra.flydown.network.TaskFactory;
import com.flyzebra.flydown.utils.FileUtils;
import com.flyzebra.flydown.utils.HttpUtils;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月22日 下午3:11:27  
*/
public class SimpleFileBlockQueue implements IFileBlockQueue {
	private BlockingQueue<FileBlock> fileBlockQueue = new LinkedBlockingQueue<>();
	private String downUrl; 
	private String saveFile; 
	private String tempFile;
	private int threadNum; 
	private IFileBlockReQuestListener iFileBlockReQuestListener;
	
	@Override
	public boolean isEmpty() {
		return fileBlockQueue.isEmpty();
	}

	@Override
	public void doNextQueue() {
		if(!fileBlockQueue.isEmpty()){
			IHandleTask iHandleTask =TaskFactory.creat(downUrl);
			iHandleTask.setUrl(downUrl).setSaveFile(saveFile).setFileBlockReQuestListener(iFileBlockReQuestListener).setFileBlock(fileBlockQueue.poll()).handle();
		}
	}

	@Override
	public SimpleFileBlockQueue setUrl(String downUrl) {
		this.downUrl = downUrl;
		return this;
	}

	@Override
	public SimpleFileBlockQueue setSaveFile(String saveFile) {
		this.saveFile = saveFile;
		return this;
	}

	@Override
	public SimpleFileBlockQueue setTempFile(String tempFile) {
		this.tempFile = tempFile;
		return this;
	}
	
	@Override
	public SimpleFileBlockQueue setThreadNum(int threadNum) {
		this.threadNum = threadNum;
		return this;
	}
	
	@Override
	public SimpleFileBlockQueue listener(IFileBlockReQuestListener iFileBlockReQuestListener) {
		this.iFileBlockReQuestListener = iFileBlockReQuestListener;
		return this;
	}

	@Override
	public void createQueue() {
		String str = FileUtils.readFile(tempFile);
		if (str != null) {
			for (int i = 0; i < str.length(); i=i+48) {
				int end = i+48;
				FileBlock fileBlockData = FileBlock.create(str.substring(i, end));
				fileBlockQueue.add(fileBlockData);
			}
		}else{
			long length = HttpUtils.getLength(downUrl);
			FileBlock fileBlockData = new FileBlock();
			fileBlockData.setStaPos(0);
			fileBlockData.setEndPos(length/2);
			fileBlockQueue.add(fileBlockData);
			FileBlock fileBlockData1 = new FileBlock();
			fileBlockData1.setStaPos(length/2);
			fileBlockData1.setEndPos(length);
			fileBlockQueue.add(fileBlockData1);
		}
	}


}
