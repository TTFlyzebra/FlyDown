package com.flyzebra.flydown.task;

import com.flyzebra.flydown.network.HandleTaskFactory;
import com.flyzebra.flydown.utils.EncodeHelper;
import com.flyzebra.flydown.utils.FlyLog;
import com.flyzebra.flydown.utils.HttpUtils;

/**
 * 功能说明：单个文件下载任务对象，管理下载线程，统计下载速度(各线程之和)，监听下载任务状态
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年2月28日 上午10:11:57
 */
public class SingleDownTask extends Thread implements IFileBlockEnvent{	
	
	private FileBlockTasks fileBlockTasks;

	/**
	 * 下载地址
	 */
	private String downUrl = null;

	/**
	 * 下载开启的线程数
	 */
	private int threadNum = 1;
	/**
	 * 保存文件的地址
	 */
	private String saveFile = null;

	/**
	 * 下载信息监听接口
	 */
	private IDownTaskEnvent iDownListener = null;

	/**
	 * 构造函数，生成实例
	 * 
	 * @param url
	 *            下载地址
	 */
	public SingleDownTask(String url) {
		downUrl = url;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public SingleDownTask setDownUrl(String downUrl) {
		this.downUrl = downUrl;
		return this;
	}

	public int getTdNum() {
		return threadNum;
	}

	/**
	 * 设置开启多少个线程下载文件
	 * @param threadNum
	 * @return
	 */
	public SingleDownTask thread(int threadNum) {
		this.threadNum = threadNum;
		return this;
	}

	public String getSaveFilePath() {
		return saveFile;
	}

	/**
	 * 设置下载后保存文件的文件名
	 * @param saveFile
	 * @return
	 */
	public SingleDownTask savefile(String saveFile) {
		this.saveFile = saveFile;
		return this;
	}
	
	public IDownTaskEnvent getiDownListener(){
		return this.iDownListener;
	}

	public SingleDownTask listener(IDownTaskEnvent iDownListener) {
		this.iDownListener = iDownListener;
		return this;
	}

	@Override
	public String toString() {
		return "DownRequest [downUrl=" + downUrl + ", tdNum=" + threadNum + ", saveFilePath=" + saveFile + ", iDownListener=" + iDownListener + "]";
	}
	
	@Override
	public void run() {
		if (downUrl == null) {
			FlyLog.d("无效的下载地址!");
			return;
		}
		if (saveFile == null) {
			saveFile = downUrl.substring(downUrl.lastIndexOf('/') + 1, downUrl.length());
		}
		
		//读取已下载信息
		fileBlockTasks = new FileBlockTasks(downUrl,threadNum);
			
		//获取下载文件大小，检测服务器是否支持断点续传
//		long fileLength = HttpUtils.getLength(downUrl);
//		
//		if(fileLength>0){
//			
//		}
		
		for(FileBlock fileBlock:fileBlockTasks.fileBlocks){
			HandleTaskFactory.creat(downUrl).setFileBlock(fileBlock).setSingleDownTask(this).setFileBlockEnvent(this).handle();
		}
		
		//运行下载线程
//		HandleTaskFactory.creat(downUrl).handle(this);
	}

	/**
	 * 开始执行下载请求
	 */
	@Override
	public void start() {
		super.start();
	}

	@Override
	public void Error(FileBlock fileBlock, int ErrorCode) {
	}

	@Override
	public void Finish(FileBlock fileBlock) {
	}
}
