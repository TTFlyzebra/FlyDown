package com.flyzebra.flydown;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午1:55:43  
*/
public interface IDownEvent {

	public void onError(String url);
	
	public void onFail(String url);
	
	public void onSuccese(String url);
}
