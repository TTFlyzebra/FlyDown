
package com.flyzebra.flydown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.flyzebra.flydown.request.IFileReQuest;
import com.flyzebra.flydown.request.SingleDownTask;

/** 
* 功能说明：下载任务管理，添加下载任务，删除下载任务，暂停下载任务
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月28日 上午9:47:25  
*/
public class TaskManager {
	private static TaskManager INSTACE = new TaskManager();
	private BlockingQueue<IFileReQuest> fileRequestQueue = new LinkedBlockingQueue<>();
	public static TaskManager getInstace() {
		return INSTACE;
	}
	
	/**
	 * 设置下载地址（该方法会新生成一个DownRequest实例做为结果返回）
	 * @param url下载地址
	 * @return DownRequest实例
	 */
	public synchronized IFileReQuest addReQuest(String url) {
		IFileReQuest fileQequest = new SingleDownTask(url);
		fileRequestQueue.add(fileQequest);
		return fileQequest;
	}
	
	public synchronized void cancleRequest(String url){
		//TODO 删除下载任务
	}
}
