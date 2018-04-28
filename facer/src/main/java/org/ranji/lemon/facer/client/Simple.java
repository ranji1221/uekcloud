package org.ranji.lemon.facer.client;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.ranji.lemon.facer.util.FaceUtil;

import com.baidu.aip.face.AipFace;

public class Simple {
	
	public static void main(String[] args) throws JSONException {
			
		AipFace client = FaceUtil.getInstance();
		/*
		//-- 传入可选参数调用接口
		HashMap<String,String> options = new HashMap<String,String>();
		options.put("max_face_num", "5");
		options.put("face_fields", "age,beauty,gender,expression");
		
        // 调用接口
        String image = "test6.jpg";
        JSONObject res = client.detect(
        		Simple.class.getClassLoader().getResource(image).getPath(),
        		options);
        System.out.println(res.toString(2));
        */
		
        //-- 人脸对比
		/*
		//-- 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("ext_fields", "qualities");
        options.put("image_liveness", ",faceliveness");
        options.put("types", "7,13");
        
        //参数为本地图片路径列表
        String path1 = "test1.jpg";
        String path2 = "test6.jpg";
		
        ArrayList<String> images = new ArrayList<String>();
        images.add(Simple.class.getClassLoader().getResource(path1).getPath());
        images.add(Simple.class.getClassLoader().getResource(path2).getPath());
        
        JSONObject res = client.match(images, options);
        System.out.println(res.toString(2)); 
        */
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("ext_fields", "faceliveness");
        options.put("detect_top_num", "10");
        options.put("user_top_num", "2");
	}
}
