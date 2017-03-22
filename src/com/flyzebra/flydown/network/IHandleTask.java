package com.flyzebra.flydown.network;

import com.flyzebra.flydown.file.FileBlock;
import com.flyzebra.flydown.request.FileBlock1;
import com.flyzebra.flydown.request.IFileBlockReQuestListener;
import com.flyzebra.flydown.request.SingleDownTask;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 上午9:46:27  
*/
public interface IHandleTask {
	
	IHandleTask setUrl(String downUrl);
	
	IHandleTask setSaveFile(String saveFile);
	
	IHandleTask setFileBlock(FileBlock fileBlock);
	
	IHandleTask setFileBlockReQuestListener(IFileBlockReQuestListener iFileBlockEnvent); 
	
	IHandleTask setSingleDownTask(SingleDownTask singleDownTask);
	
	IHandleTask setFileBlock(FileBlock1 fileBlock);
	/**
	 * 處理下載
	 * @param downRequest
	 */
	void handle();
}
