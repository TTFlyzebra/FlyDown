package com.flyzebra.flydown.utils;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午2:33:00  
*/
public class FlyLog {
	
	private static boolean DEBUG = true;

	public static void d(String str,Object...args){
		if(DEBUG){
			System.out.print(String.format(str, args)); 
		}
	}
}
