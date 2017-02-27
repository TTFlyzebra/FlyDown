package com.flyzebra.flydown;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午1:49:06  
*/
public interface ISubDown {
	//指定下载地址
	public ISubDown load(String url);
	//开始下载
	public void start();
	//设置下载开始的点
	public ISubDown setStartPos(int pos);
	//设置下载结束的点
	public ISubDown setEndPos(int pos);
	//设置下载信息监听事件
	public ISubDown addListener(IDownEvent iDownEvent);
}
