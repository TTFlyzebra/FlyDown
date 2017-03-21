package com.flyzebra.flydown.task;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月21日 下午5:40:46
 */
public interface IFileBlockEnvent {

	public void Error(FileBlock fileBlock, int ErrorCode);

	public void Finish(FileBlock fileBlock);
}
