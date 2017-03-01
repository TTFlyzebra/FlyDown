package com.flyzebra.flydown.network;
//package com.flyzebra.flydown;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.RandomAccessFile;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import com.flyzebra.flydown.utils.CloseableUtils;
//import com.flyzebra.flydown.utils.FlyLog;
//
///**
// * 功能说明：
// * 
// * @author 作者：FlyZebra
// * @version 创建时间：2017年3月1日 上午11:19:26
// */
//public class HttpTask implements Runnable {
//	public HttpTask() {
//
//	}
//
//	@Override
//	public void run() {
//		HttpURLConnection urlConnection = null;
//		InputStream ins = null;
//		RandomAccessFile ous = null;
//		try {
//			ous = new RandomAccessFile(new File(request.getSaveFilePath()), "rw");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//			final URL url = new URL(request.getDownUrl());
//			urlConnection = (HttpURLConnection) url.openConnection();
//			urlConnection.setConnectTimeout(CONNECT_TIME);
//			urlConnection.setReadTimeout(CONNECT_TIME);
//			int fileLength = urlConnection.getContentLength();
//			FlyLog.d(url + " file size = " + fileLength + "\n");
//			// urlConnection.setDoInput(true);
//			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//				ins = urlConnection.getInputStream();
//				byte[] b = new byte[1024];
//				int nRead = 0;
//				long size = 0;
//				while ((nRead = ins.read(b, 0, 1024)) > 0) {
//					ous.write(b, 0, nRead);
//					size = size + nRead;
//				}
//				FlyLog.d(url + " read size = " + size + "\n");
//				if (request.getiDownListener() != null) {
//					request.getiDownListener().Finish(request.getDownUrl());
//				}
//			} else {
//				// TODO:添加其它HTTP返回码的处理信息
//			}
//		} catch (final IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (urlConnection != null) {
//				urlConnection.disconnect();
//			}
//			CloseableUtils.Close(ins);
//			CloseableUtils.Close(ous);
//		}
//	}
//
//}
