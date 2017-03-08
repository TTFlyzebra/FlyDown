package com.flyzebra.flydown.network;

import com.flyzebra.flydown.task.SingleDownTask;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 上午9:46:27  
*/
public interface IHandleTask {
	
	/**
	 * 處理下載
	 * @param downRequest
	 */
	void handle(SingleDownTask downRequest);
}
