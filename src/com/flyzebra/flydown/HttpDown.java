package com.flyzebra.flydown;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午1:53:26  
*/
public class HttpDown implements ISubDown {
	private int StartPos = 0;
	private int EndPos = 0;
	private String downUrl = null;
	private final int CONNECT_TIME = 30;
	private static final int CACHE_SIZE = 2 * 1024*1024;
	private List<IDownEvent> iDownEvents = new ArrayList<>();
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		if(downUrl==null) {
			FlyLog.d("下载地址未指定。"); 
			return;
		}
		HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        InputStream inputStream =null;
        OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(""));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
            final URL url = new URL(downUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(CONNECT_TIME);
            urlConnection.setReadTimeout(CONNECT_TIME);
//            urlConnection.setDoInput(true);
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                in = new BufferedInputStream(inputStream, CACHE_SIZE);
                out = new BufferedOutputStream(outputStream,CACHE_SIZE);
                int b;
                while ((b = in.read()) != -1) {
                    out.write(b);
                }
                out.flush();
                for(IDownEvent iDownEvent:iDownEvents){
                	iDownEvent.onSuccese(downUrl);
                }
                iDownEvents.remove(this);
            }else{
            	//TODO:添加其它HTTP返回码的处理信息
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            CloseableUtils.Close(in);
            CloseableUtils.Close(out);
            CloseableUtils.Close(inputStream);
            CloseableUtils.Close(outputStream);
        }
	}

	@Override
	public ISubDown setStartPos(int pos) {
		// TODO Auto-generated method stub
		this.StartPos = pos;
		return this;
	}

	@Override
	public ISubDown setEndPos(int pos) {
		// TODO Auto-generated method stub
		this.EndPos= pos;
		return this;
	}

	@Override
	public ISubDown load(String url) {
		// TODO Auto-generated method stub
		this.downUrl = url;
		return this;
	}

	@Override
	public ISubDown addListener(IDownEvent iDownEvent) {
		// TODO Auto-generated method stub
		iDownEvents.add(iDownEvent);
		return this;
	}

}
