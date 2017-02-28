package com.flyzebra.flydown;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月28日 上午9:47:25  
*/
public class DownManager {
	private static DownManager INSTACE = new DownManager();
	private String downUrl;
	public static DownManager getInstace() {
		// TODO Auto-generated method stub
		return INSTACE;
	}
	
	/**
	 * 设置下载地址（该方法会新生成一个DownRequest实例做为结果返回）
	 * @param url下载地址
	 * @return DownRequest实例
	 */
	public DownRequest setUrl(String url) {
		// TODO Auto-generated method stub
		DownRequest dr = new DownRequest(url);
		return dr;
	}
}
