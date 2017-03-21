
package com.flyzebra.flydown;

import java.util.ArrayList;
import java.util.List;
import com.flyzebra.flydown.task.SingleDownTask;

/** 
* 功能说明：下载任务管理，添加下载任务，删除下载任务，暂停下载任务
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月28日 上午9:47:25  
*/
public class TaskManager {
	private static TaskManager INSTACE = new TaskManager();
	private List<SingleDownTask> downTaskList = new ArrayList<>();
	public static TaskManager getInstace() {
		return INSTACE;
	}
	
	/**
	 * 设置下载地址（该方法会新生成一个DownRequest实例做为结果返回）
	 * @param url下载地址
	 * @return DownRequest实例
	 */
	public synchronized SingleDownTask addDownUrl(String url) {
		SingleDownTask dr = new SingleDownTask(url);
		downTaskList.add(dr);
		return dr;
	}
	
	public synchronized void removeDownUrl(String url){
		//TODO 删除下载任务
	}
}
