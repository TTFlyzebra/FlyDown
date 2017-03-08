package com.flyzebra.flydown.task;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 下午1:47:37  
*/
public class FileBlockManager {
	public long startPos;
	public long downPos;
	public long endPos;	
	/**
	 * 构造函数
	 * @param startPos 下载起始点
	 * @param downPos 已经下载到的点
	 * @param endPos 下载结束点
	 */
	public FileBlockManager(long startPos, long downPos, long endPos) {
		this.startPos=startPos;
		this.downPos=downPos;
		this.endPos=endPos;
	}

	/**
	 * 将字符串转为FileBlock对像
	 * @param str 必须遵守约定格式(三个16进制字符串，三个变量中间用字母X隔开，结束用字母E)
	 * @return FileBlock实例
	 */
	public static FileBlockManager create(String str){
		if(str==null||str.length()==0){
			return null;
		}
		String ss[] = str.split("X");
		if(ss.length==3){
			long startPos = Long.parseLong(ss[0], 16);
			long downPos = Long.parseLong(ss[1], 16);
			long endPos = Long.parseLong(ss[2], 16);
			return new FileBlockManager(startPos,downPos,endPos);
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
		sb.append(Long.toHexString(startPos))
		.append("X")
		.append(Long.toHexString(downPos))
		.append("X")
		.append(Long.toHexString(endPos))
		.append("E");
		return sb.toString();
	}
}
