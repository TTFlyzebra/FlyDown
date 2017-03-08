package com.flyzebra.flydown.task;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月8日 下午4:23:44  
*/
public interface IBlockTaskEvent {
	
	/**
	 * 分块任务下载完成时调用
	 * @param blocktask
	 */
	void onFinish(SingleBlockTask blocktask);

}
