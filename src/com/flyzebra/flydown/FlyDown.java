package com.flyzebra.flydown;
/** 
* 功能说明：下载文件
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 上午11:57:27  
*/
public class FlyDown {
	private static String mCacheDir = "";
	FlyDown setCacheDir(String path){
		this.mCacheDir = path;
		return this;
	}
}
