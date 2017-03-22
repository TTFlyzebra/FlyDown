package com.flyzebra.flydown.request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flyzebra.flydown.FlyDown;
import com.flyzebra.flydown.utils.FlyLog;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月22日 上午10:03:57
 */
public class SimpleFileReQuest implements Runnable, IFileReQuest, IFileBlockReQuestListener {

	private String downUrl;
	private String saveFile;
	private String tempFile;
	private int threadNum = 1;
	private IFileReQuestListener iFileReQuestListener;
	private IFileBlockQueue iFileBlockQueue;

	private ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());

	@Override
	public IFileReQuest setUrl(String downUrl) {
		this.downUrl = downUrl;
		return this;
	}

	@Override
	public IFileReQuest setSaveFile(String saveFile) {
		this.saveFile = saveFile;
		return this;
	}

	@Override
	public IFileReQuest listener(IFileReQuestListener iFileReQuestListener) {
		this.iFileReQuestListener = iFileReQuestListener;
		return this;
	}

	@Override
	public void goStart() {
		// 在线程中执行下载操作
		executor.execute(this);
	}

	@Override
	public void cancle() {

	}

	@Override
	public void Error(FileBlock1 fileBlock, int ErrorCode) {
		iFileReQuestListener.Error(downUrl, ErrorCode);
	}

	@Override
	public void Finish(FileBlock1 fileBlock) {
		if (iFileBlockQueue.isEmpty()) {
			iFileReQuestListener.Finish(downUrl);
		} else {
			iFileBlockQueue.doNextQueue();
		}
	}

	@Override
	public void run() {
		if (downUrl == null) {
			FlyLog.d("无效的下载地址!");
			return;
		}

		if (saveFile == null) {
			saveFile = FlyDown.mCacheDir + downUrl.substring(downUrl.lastIndexOf('/') + 1, downUrl.length());
		}

		if (tempFile == null) {
			tempFile = "." + FlyDown.mCacheDir + saveFile.substring(saveFile.lastIndexOf('/') + 1, saveFile.length()) + ".tmp";
		}

		if (iFileBlockQueue == null) {
			iFileBlockQueue = new SimpleFileBlockQueue();
		}
		iFileBlockQueue.setUrl(downUrl).setSaveFile(saveFile).setTempFile(tempFile).setThreadNum(threadNum).listener(this).init();

		for (int i = 0; i < threadNum; i++) {
			iFileBlockQueue.doNextQueue();
		}

	}

}
