package com.flyzebra.flydown;

import com.flyzebra.flydown.utils.FlyLog;

/**
 * 功能说明：把下载信息封装成一个请求对像
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年2月28日 上午10:11:57
 */
public class DownRequest {

	/**
	 * 下载地址
	 */
	private String downUrl = null;

	/**
	 * 下载开启的线程数
	 */
	private int tdNum = 1;
	/**
	 * 保存文件的地址
	 */
	private String saveFile = null;

	/**
	 * 下载信息监听接口
	 */
	private IDownListener iDownListener = null;

	/**
	 * 构造函数，生成实例
	 * 
	 * @param url
	 *            下载地址
	 */
	public DownRequest(String url) {
		downUrl = url;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public DownRequest setDownUrl(String downUrl) {
		this.downUrl = downUrl;
		return this;
	}

	public int getTdNum() {
		return tdNum;
	}

	public DownRequest setTdNum(int tdNum) {
		this.tdNum = tdNum;
		return this;
	}

	public String getSaveFilePath() {
		return saveFile;
	}

	public DownRequest setSaveFile(String saveFile) {
		this.saveFile = saveFile;
		return this;
	}

	public IDownListener getiDownListener() {
		return iDownListener;
	}

	public DownRequest listener(IDownListener iDownListener) {
		this.iDownListener = iDownListener;
		return this;
	}

	@Override
	public String toString() {
		return "DownRequest [downUrl=" + downUrl + ", tdNum=" + tdNum + ", saveFilePath=" + saveFile
				+ ", iDownListener=" + iDownListener + "]";
	}

	/**
	 * 开始执行下载请求
	 */
	public void start() {
		if (downUrl == null) {
			FlyLog.d("无效的下载地址!");
			return;
		}
		// IDown httpDown = new HttpDown();
		if (saveFile == null) {
			saveFile = downUrl.substring(downUrl.lastIndexOf('/') + 1, downUrl.length());
		}
		// FlyLog.d("start down task url="+downUrl+",saveFile="+saveFile+"\n");
		// if(iDownListener!=null){
		// httpDown.listener(iDownListener);
		// }
		// httpDown.setSavaFilePath(saveFile);
		// httpDown.setUrl(downUrl);
		// httpDown.start();
		HandleRequestFactory.creat(downUrl).handle(this);
	}
}
