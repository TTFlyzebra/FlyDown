package com.flyzebra.flydown;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午1:55:43  
*/
public interface IDownListener {

	public void Error(String url,int ErrorCode);
	
	public void Fail(String url);
	
	public void Finish(String url);
	
	public void progress(String url, long downBytes, long sumBytes);
}
