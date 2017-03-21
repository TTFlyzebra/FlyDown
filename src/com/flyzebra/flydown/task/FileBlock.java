package com.flyzebra.flydown.task;
/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 下午1:47:37  
*/
public class FileBlock {
	public static final String SPLIT_A = "E";
	public static final String SPLIT_B = "X";
	public long startPos;
	public long downPos;
	public long endPos;	
	/**
	 * 构造函数
	 * @param startPos 下载起始点
	 * @param downPos 已经下载到的点
	 * @param endPos 下载结束点
	 */
	public FileBlock(long startPos, long downPos, long endPos) {
		this.startPos=startPos;
		this.downPos=downPos;
		this.endPos=endPos;
	}

	/**
	 * 将字符串转为FileBlock对像
	 * @param str 必须遵守约定格式(三个16进制字符串，三个变量中间用字母X隔开，结束用字母E)
	 * @return FileBlock实例，创建失败返回null
	 */
	public static FileBlock create(String str){
		if(str==null||str.length()==0){
			return null;
		}
		String strs[] = str.split("X");
		if(strs.length==3){
			long startPos = Long.parseLong(strs[0], 16);
			long downPos = Long.parseLong(strs[1], 16);
			long endPos = Long.parseLong(strs[2], 16);
			return new FileBlock(startPos,downPos,endPos);
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
		.append(SPLIT_B)
		.append(Long.toHexString(downPos))
		.append(SPLIT_B)
		.append(Long.toHexString(endPos))
		.append(SPLIT_A);
		return sb.toString();
	}
}
