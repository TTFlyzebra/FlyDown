package com.flyzebra.flydown.request;

import java.util.ArrayList;
import java.util.List;

import com.flyzebra.flydown.utils.EncodeHelper;
import com.flyzebra.flydown.utils.FileUtils;
import com.flyzebra.flydown.utils.HttpUtils;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月21日 下午3:47:53
 */
public class FileBlockList {
	private String downUrl;
	private List<FileBlock> fileBlocks = new ArrayList<>();

	public FileBlockList(String downUrl, int threadNum) {
		this.downUrl = downUrl;
		String fileName = EncodeHelper.md5(downUrl)+".tmp";
		String str = FileUtils.readFile(fileName);
		if (str != null) {
			String blockStrs[] = str.split(FileBlock.SPLIT_A);
			for (int i = 0; i < blockStrs.length; i++) {
				FileBlock fileBlock = FileBlock.create(blockStrs[i]);
				if (fileBlock != null) {
					fileBlocks.add(fileBlock);
				}
			}
		}else{
			long length = HttpUtils.getLength(downUrl);
			FileBlock fileBlock = new FileBlock(0, length);
			fileBlocks.add(fileBlock);
		}

	}

	public List<FileBlock> getFileBlocks() {
		return fileBlocks;
	}

}
