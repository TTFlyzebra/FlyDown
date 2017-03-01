package com.flyzebra.flydown.dataio;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 下午1:47:37  
*/
public class DownBlock {
	private long statPos;
	private long downPos;
	private long endPos;
	
	public static DownBlock create(String str){
		if(str==null||str.length()==0){
			return null;
		}
		String ss[] = str.split("X");
		if(ss.length!=3){
			return null;
		}
		
		return null;
	}

	/**
	 * 转换字符串格式，转换成16进制字符串，三个变量中间用字母X隔开，结束用字母E
	 */
	@Override
	public String toString() {
		//TODO: 需添加异常错误处理
		StringBuffer sb = new StringBuffer();
		sb.append(Long.toHexString(statPos))
		.append("X")
		.append(Long.toHexString(downPos))
		.append("X")
		.append(Long.toHexString(endPos))
		.append("E");
		return sb.toString();
	}
}
