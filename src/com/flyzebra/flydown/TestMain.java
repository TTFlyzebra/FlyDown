package com.flyzebra.flydown;

import com.flyzebra.flydown.task.IFileTaskListener;
import com.flyzebra.flydown.utils.FlyLog;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午3:32:00  
*/
public class TestMain{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlyLog.d("-----start main-----\n");
		String downUrl = "http://127.0.0.1:8080/video//tsy1.mp4";
		IFileTaskListener listener = new IFileTaskListener() {

			@Override
			public void Error(String url, int ErrorCode) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void Finish(String url) {
				// TODO Auto-generated method stub
				FlyLog.d("--onFinish----"+url+"\n");
			}

			@Override
			public void Pause(String url) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void Progress(String url, long downBytes, long sumBytes) {
				// TODO Auto-generated method stub
				
			}
		};
		FlyDown.load(downUrl).listener(listener).start();
//		FlyDown.load("http://127.0.0.1:8080/video/tsy2.mp4").listener(listener).start();
//		FlyDown.load("http://127.0.0.1:8080/video/tsy3.mp4").listener(listener).start();
//		FlyDown.load("http://127.0.0.1:8080/video/tsy4.mp4").listener(listener).start();
//		FlyDown.load("http://127.0.0.1:8080/video/tsy5.mp4").listener(listener).start();
		FlyLog.d("-----end main-----\n");
	}
	
	
	

}
