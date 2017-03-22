package com.flyzebra.flydown.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flyzebra.flydown.file.FileBlock;
import com.flyzebra.flydown.request.IFileBlockReQuestListener;
import com.flyzebra.flydown.utils.CloseableUtil;
import com.flyzebra.flydown.utils.FlyLog;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月1日 上午9:53:41
 */
public class HttpRequest implements IHandleTask,Runnable{
	private String downUrl;
	private String saveFile;
	private FileBlock fileBlock;
	private IFileBlockReQuestListener iFileBlockReQuestListener;
	private ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	@Override
	public void handle() {
		executor.execute(this);
	}

	@Override
	public HttpRequest setFileBlockReQuestListener(IFileBlockReQuestListener iFileBlockReQuestListener) {
		this.iFileBlockReQuestListener = iFileBlockReQuestListener;
		return this;
	}

	@Override
	public HttpRequest setFileBlock(FileBlock fileBlock) {
		this.fileBlock = fileBlock;
		return this;
	}

	@Override
	public HttpRequest setUrl(String downUrl) {
		this.downUrl=downUrl;
		return this;
	}

	@Override
	public HttpRequest setSaveFile(String saveFile) {
		this.saveFile = saveFile;
		return this;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpURLConnection con = null;
		InputStream ins = null;
		RandomAccessFile ous = null;
		try {
			ous = new RandomAccessFile(new File(saveFile), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			final URL url = new URL(downUrl);
			con = (HttpURLConnection) url.openConnection();
//			con.setConnectTimeout(CONNECT_TIME);
//			con.setReadTimeout(CONNECT_TIME);
			con.setRequestProperty("RANGE", "bytes="+fileBlock.getStaPos()+"-"+fileBlock.getEndPos());
			int fileLength = con.getContentLength();
			FlyLog.d(url + " file size = " + fileLength + "\n");
			// urlConnection.setDoInput(true);
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK||con.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
				ins = con.getInputStream();
				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = ins.read(b, 0, 1024)) > 0&&fileBlock.getStaPos()<=fileBlock.getEndPos()) {
//					ous.write(b, 0, nRead);
					ous.seek(fileBlock.getStaPos());
					ous.write(b);
					fileBlock.setStaPos(fileBlock.getStaPos()+nRead);
					iFileBlockReQuestListener.progress(fileBlock);
				}
				if (iFileBlockReQuestListener != null) {
					iFileBlockReQuestListener.finish(fileBlock);
				}
			} else {
				FlyLog.d("ResponseCode = "+con.getResponseCode());
				// TODO:添加其它HTTP返回码的处理信息
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
			CloseableUtil.Close(ins);
			CloseableUtil.Close(ous);
		}
	}

}
