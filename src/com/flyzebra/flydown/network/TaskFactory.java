package com.flyzebra.flydown.network;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 上午9:49:50  
*/
public class TaskFactory {
	public static IHandleTask creat(String url){
		IHandleTask ihandleRequest = null;
		if(url.indexOf("http://")==0){
			ihandleRequest = new HttpRequest();
		}
		return ihandleRequest;		
	}
}
