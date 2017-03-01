package com.flyzebra.flydown;

import com.flyzebra.flydown.network.HttpHandleRequest;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 上午9:49:50  
*/
public class HandleRequestFactory {
	public static IHandleRequest creat(String url){
		IHandleRequest ihandleRequest = null;
		if(url.indexOf("http://")==0){
			ihandleRequest = new HttpHandleRequest();
		}
		return ihandleRequest;		
	}
}
