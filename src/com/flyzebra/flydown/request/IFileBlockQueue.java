package com.flyzebra.flydown.request;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月22日 下午2:59:37
 */
public interface IFileBlockQueue {
	IFileBlockQueue setUrl(String downUrl);

	IFileBlockQueue setSaveFile(String saveFile);

	IFileBlockQueue setTempFile(String tempFile);

	IFileBlockQueue setThreadNum(int threadNum);

	IFileBlockQueue listener(IFileBlockReQuestListener iFileBlockReQuestListener);

	void init();

	boolean isEmpty();

	void doNextQueue();

}
