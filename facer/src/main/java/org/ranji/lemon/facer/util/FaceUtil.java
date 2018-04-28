package org.ranji.lemon.facer.util;

import com.baidu.aip.face.AipFace;

/**
 * 人脸识别工具
 * @author RanJi
 */
public class FaceUtil {
	//设置APPID/AK/SK
    public static final String APP_ID = "11149861";
    public static final String API_KEY = "deSVkqD1C10CgLw3Fpmmrkdq";
    public static final String SECRET_KEY = "De24TmA3g8ZoYK6FX7U5BnRQbvdGkppB";
    
    private static AipFace airface; 
    
    private FaceUtil(){}
    
    public static AipFace getInstance(){
    	if(airface==null){
    		// 初始化一个AipFace
            airface = new AipFace(APP_ID, API_KEY, SECRET_KEY);
            
    		// 可选：设置网络连接参数
            airface.setConnectionTimeoutInMillis(2000);
            airface.setSocketTimeoutInMillis(60000);
            
            // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
            //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
            //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
    	}
    	return airface;
    }
}
