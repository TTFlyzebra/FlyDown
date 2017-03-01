package com.flyzebra.flydown.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flyzebra.flydown.DownRequest;
import com.flyzebra.flydown.IHandleRequest;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月1日 上午9:53:41
 */
public class HttpHandleRequest implements IHandleRequest {
	private DownRequest request;
	ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());

	@Override
	public void handle(DownRequest downRequest) {
		// TODO Auto-generated method stub
		this.request = downRequest;
		executor.execute(new HttpTask(request.getDownUrl(), request.getSaveFilePath(), request.getiDownListener()));
	}

}
