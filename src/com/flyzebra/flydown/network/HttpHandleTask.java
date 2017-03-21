package com.flyzebra.flydown.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flyzebra.flydown.task.FileBlock;
import com.flyzebra.flydown.task.IFileBlockEnvent;
import com.flyzebra.flydown.task.SingleDownTask;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月1日 上午9:53:41
 */
public class HttpHandleTask implements IHandleTask {
	private FileBlock fileBlock;
	private SingleDownTask request;
	private IFileBlockEnvent iFileBlockEnvent;
	ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	@Override
	public void handle() {
		executor.execute(new HttpTask(request.getDownUrl(), request.getSaveFilePath(), request.getiDownListener(),fileBlock));
	}

	@Override
	public HttpHandleTask setFileBlock(FileBlock fileBlock) {
		this.fileBlock = fileBlock;
		return this;
	}

	@Override
	public IHandleTask setSingleDownTask(SingleDownTask singleDownTask) {
		this.request = singleDownTask;
		return this;
	}

	@Override
	public IHandleTask setFileBlockEnvent(IFileBlockEnvent iFileBlockEnvent) {
		this.iFileBlockEnvent = iFileBlockEnvent;
		return this;
	}

}
