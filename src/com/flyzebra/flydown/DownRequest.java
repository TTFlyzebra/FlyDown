package com.flyzebra.flydown;

import com.flyzebra.flydown.utils.FlyLog;

/** 
* 功能说明：把下载信息封装成一个请求对像
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
	private String saveFilePath = null;
	
	private IDownListener iDownListener = null;
	
	/**
	 * 构造函数，生成实例
	 * @param url 下载地址
	 */
	public DownRequest(String url){
		downUrl = url;
	}


	public DownRequest setTdNum(int tdNum) {
		this.tdNum = tdNum;
		return this;
	}

	public DownRequest saveFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
		return this;
	}

	/**
	 * 
	 * @param iDownListener
	 * @return
	 */
	public DownRequest listener(IDownListener iDownListener) {
		this.iDownListener = iDownListener;
		return this;
	}
	
	/**
	 * 开始执行下载请求
	 */
	public void start(){
		if(downUrl==null){
			FlyLog.d("无效的下载地址!");
			return;
		}
		IDown httpDown = new HttpDown();
		if(saveFilePath==null){
			saveFilePath = downUrl.substring(downUrl.lastIndexOf('/')+1, downUrl.length());
		}
		FlyLog.d("start down task url="+downUrl+",saveFile="+saveFilePath+"\n");
		if(iDownListener!=null){
			httpDown.listener(iDownListener);
		}
		httpDown.setSavaFilePath(saveFilePath);
		httpDown.setUrl(downUrl);
		httpDown.start();
	}
}
