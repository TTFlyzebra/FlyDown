package com.flyzebra.flydown.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.flyzebra.flydown.task.IDownTaskEvent;
import com.flyzebra.flydown.utils.CloseableUtil;
import com.flyzebra.flydown.utils.FlyLog;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年3月1日 上午11:19:26
 */
public class HttpTask implements Runnable {
	private static final int CONNECT_TIME = 15;
	private String saveFile;
	private String downUrl;
	private IDownTaskEvent iDownListener;
	public HttpTask(String downUrl,String saveFile,IDownTaskEvent iDownListener) {
		this.downUrl = downUrl;
		this.saveFile = saveFile;
		this.iDownListener = iDownListener;
	}

	@Override
	public void run() {
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
			con.setConnectTimeout(CONNECT_TIME);
			con.setReadTimeout(CONNECT_TIME);
			con.setRequestProperty("RANGE", "bytes=0-");
			int fileLength = con.getContentLength();
			FlyLog.d(url + " file size = " + fileLength + "\n");
			// urlConnection.setDoInput(true);
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK||con.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
				ins = con.getInputStream();
				byte[] b = new byte[1024];
				int nRead = 0;
				long size = 0;
				while ((nRead = ins.read(b, 0, 1024)) > 0) {
					ous.write(b, 0, nRead);
					size = size + nRead;
				}
				FlyLog.d(url + " read size = " + size + "\n");
				if (iDownListener != null) {
					iDownListener.Finish(downUrl);
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
