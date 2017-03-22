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
	private List<FileBlock1> fileBlocks = new ArrayList<>();

	public FileBlockList(String downUrl, int threadNum) {
		this.downUrl = downUrl;
		String fileName = EncodeHelper.md5(downUrl)+".tmp";
		String str = FileUtils.readFile(fileName);
		if (str != null) {
			String blockStrs[] = str.split(FileBlock1.SPLIT_A);
			for (int i = 0; i < blockStrs.length; i++) {
				FileBlock1 fileBlock = FileBlock1.create(blockStrs[i]);
				if (fileBlock != null) {
					fileBlocks.add(fileBlock);
				}
			}
		}else{
			long length = HttpUtils.getLength(downUrl);
			FileBlock1 fileBlock = new FileBlock1(0, length);
			fileBlocks.add(fileBlock);
		}

	}

	public List<FileBlock1> getFileBlocks() {
		return fileBlocks;
	}

}
