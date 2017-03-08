package com.flyzebra.flydown;

import com.flyzebra.flydown.task.SingleDownTask;

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
	
	public static SingleDownTask load(String url){
		TaskManager dmg = TaskManager.getInstace();
		return dmg.addDownUrl(url);
	}
}
