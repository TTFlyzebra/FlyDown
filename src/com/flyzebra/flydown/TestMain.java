package com.flyzebra.flydown;

import com.flyzebra.flydown.request.IFileReQuestListener;
import com.flyzebra.flydown.utils.FlyLog;
import com.flyzebra.flydown.utils.HttpUtils;

/** 
* 功能说明：
* @author 作者：FlyZebra 
* @version 创建时间：2017年2月27日 下午3:32:00  
*/
public class TestMain{

	public static void main(String[] args) {
		FlyLog.d("-----start main-----\n");
		String downUrl = "http://127.0.0.1:8080/video/tsy1.mp4";
		IFileReQuestListener listener = new IFileReQuestListener() {

			@Override
			public void Error(String url, int ErrorCode) {
				
			}

			@Override
			public void Finish(String url) {
				FlyLog.d("--onFinish----%s\n",url);
			}

			@Override
			public void Pause(String url) {
				
			}

			@Override
			public void Progress(String url, long downBytes, long sumBytes) {
				
			}
		};
		FlyDown.load(downUrl).listener(listener).goStart();
		
		
//		FlyDown.load("http://127.0.0.1:8080/video/tsy2.mp4").listener(listener).start();
//		FlyDown.load("http://127.0.0.1:8080/video/tsy3.mp4").listener(listener).start();
//		FlyDown.load("http://127.0.0.1:8080/video/tsy4.mp4").listener(listener).start();
//		FlyDown.load("http://127.0.0.1:8080/video/tsy5.mp4").listener(listener).start();
		
		FlyLog.d("file l = %d \n",HttpUtils.getLength("http://127.0.0.1:8080/video/fly.avi"));
		
		FlyLog.d("-----end main-----\n");
	}
	
	
	

}
