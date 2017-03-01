package com.flyzebra.flydown;
/** 
* 功能说明：用户操作接口封装
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 上午11:57:27  
*/
public class FlyDown {
	public static String mCacheDir = "";
	FlyDown setCacheDir(String path){
		mCacheDir = path;
		return this;
	}
	
	public static DownRequest load(String url){
		DownManager dmg = DownManager.getInstace();
		return dmg.setDownUrl(url);
	}
}
