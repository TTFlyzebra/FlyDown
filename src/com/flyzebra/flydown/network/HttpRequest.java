package com.flyzebra.flydown.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flyzebra.flydown.file.FileBlock;
import com.flyzebra.flydown.request.FileBlock1;
import com.flyzebra.flydown.request.IFileBlockReQuestListener;
import com.flyzebra.flydown.request.SingleDownTask;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月1日 上午9:53:41
 */
public class HttpRequest implements IHandleTask,Runnable{
	private String downUrl;
	private String saveFile;
	private FileBlock fileBlock;
	private FileBlock1 fileBlock1;
	private SingleDownTask request;
	private IFileBlockReQuestListener iFileBlockReQuestListener;
	private ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	@Override
	public void handle() {
		if(request!=null&&fileBlock1!=null){
			executor.execute(new HttpTask(request.getDownUrl(), request.getSaveFilePath(), request.getiDownListener(),fileBlock1));
		}else{
			executor.execute(this);
		}
	}

	@Override
	public HttpRequest setFileBlock(FileBlock1 fileBlock) {
		this.fileBlock1 = fileBlock;
		return this;
	}

	@Override
	public HttpRequest setSingleDownTask(SingleDownTask singleDownTask) {
		this.request = singleDownTask;
		return this;
	}

	@Override
	public HttpRequest setFileBlockReQuestListener(IFileBlockReQuestListener iFileBlockReQuestListener) {
		this.iFileBlockReQuestListener = iFileBlockReQuestListener;
		return this;
	}

	@Override
	public HttpRequest setFileBlock(FileBlock fileBlock) {
		this.fileBlock = fileBlock;
		return this;
	}

	@Override
	public HttpRequest setUrl(String downUrl) {
		this.downUrl=downUrl;
		return this;
	}

	@Override
	public HttpRequest setSaveFile(String saveFile) {
		this.saveFile = saveFile;
		return this;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
