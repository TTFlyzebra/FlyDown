package com.flyzebra.flydown;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午1:49:06  
*/
public interface IDown {
	//指定下载地址
	public IDown setUrl(String url);
	//开始下载
	public void start();
	//设置下载开始的点
	public IDown setStartPos(int pos);
	//设置下载结束的点
	public IDown setEndPos(int pos);
	//设置下载信息监听事件
	public IDown listener(IDownListener iDownEvent);
	//设置下载文件保存位置
	public IDown setSavaFilePath(String saveFilePath);
}
