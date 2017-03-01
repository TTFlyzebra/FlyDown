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

import com.flyzebra.flydown.DownRequest;
import com.flyzebra.flydown.IHandleRequest;
import com.flyzebra.flydown.utils.CloseableUtil;
import com.flyzebra.flydown.utils.FlyLog;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年3月1日 上午9:53:41  
*/
public class HttpHandleRequest implements IHandleRequest,Runnable {
	private DownRequest request;
	private final int CONNECT_TIME = 30;
	ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());
	
	@Override
	public void handle(DownRequest downRequest) {
		// TODO Auto-generated method stub
		this.request = downRequest;
		executor.execute(this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpURLConnection urlConnection = null;
		InputStream ins = null;
		RandomAccessFile ous = null;
		try {
			ous = new RandomAccessFile(new File(request.getSaveFilePath()), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			final URL url = new URL(request.getDownUrl());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(CONNECT_TIME);
			urlConnection.setReadTimeout(CONNECT_TIME);
			int fileLength = urlConnection.getContentLength();
			FlyLog.d(url + " file size = " + fileLength + "\n");
			// urlConnection.setDoInput(true);
			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ins = urlConnection.getInputStream();
				byte[] b = new byte[1024];
                int nRead = 0;
                long size = 0;
				while ((nRead = ins.read(b, 0, 1024)) > 0) {
					ous.write(b, 0, nRead);
					size = size + nRead;
				}
				FlyLog.d(url + " read size = " + size + "\n");
				if(request.getiDownListener()!=null){
					request.getiDownListener().Finish(request.getDownUrl());
				}
			} else {
				// TODO:添加其它HTTP返回码的处理信息
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			CloseableUtil.Close(ins);
			CloseableUtil.Close(ous);
		}
	}

}
