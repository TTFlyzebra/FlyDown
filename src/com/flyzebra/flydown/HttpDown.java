package com.flyzebra.flydown;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flyzebra.flydown.utils.CloseableUtils;
import com.flyzebra.flydown.utils.FlyLog;

/**
 * 功能说明：
 * 
 * @author 作者：FlyZebra
 * @version 创建时间：2017年2月27日 下午1:53:26
 */
public class HttpDown implements IDown, Runnable {
	private int StartPos = 0;
	private int EndPos = 0;
	private String downUrl = null;
	private String saveFilePath = null;
	private final int CONNECT_TIME = 30;
	private static final int CACHE_SIZE = 2 * 1024 * 1024;
	private List<IDownListener> iDownEvents = new ArrayList<>();

	ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());

	@Override
	public void start() {
		executor.execute(this);
	}

	@Override
	public IDown setStartPos(int pos) {
		this.StartPos = pos;
		return this;
	}

	@Override
	public IDown setEndPos(int pos) {
		this.EndPos = pos;
		return this;
	}

	@Override
	public IDown setUrl(String url) {
		this.downUrl = url;
		return this;
	}

	@Override
	public IDown listener(IDownListener iDownEvent) {
		iDownEvents.add(iDownEvent);
		return this;
	}

	private void connectAndDown(String downUrl) {
		HttpURLConnection urlConnection = null;
		InputStream ins = null;
		RandomAccessFile ous = null;
		try {
			ous = new RandomAccessFile(new File(saveFilePath), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			final URL url = new URL(downUrl);
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
				for (IDownListener iDownEvent : iDownEvents) {
					iDownEvent.onFinish(downUrl);
				}
				iDownEvents.remove(this);
				FlyLog.d("listent size = " + iDownEvents.size() + "\n");
			} else {
				// TODO:添加其它HTTP返回码的处理信息
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			CloseableUtils.Close(ins);
			CloseableUtils.Close(ous);
		}
	}

	@Override
	public IDown setSavaFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
		return this;
	}

	@Override
	public void run() {
		connectAndDown(downUrl);
	}

}
