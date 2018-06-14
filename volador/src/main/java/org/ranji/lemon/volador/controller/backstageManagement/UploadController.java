package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.ranji.lemon.volador.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@Autowired
	private IAdminService adminService;

	@RequestMapping(value="/uploadImg",method=RequestMethod.POST)
	public void upload(
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		
		Map result=new HashMap();
		String token=request.getParameter("token");
		
		if(adminService.parseJWT(token)){
			
	        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
		       try {
		           String contentType = file.getContentType();   //图片文件类型
		           String fileName1 = file.getOriginalFilename();  //图片名字
		           String suffix = fileName1.substring(fileName1.lastIndexOf(".") + 1);
		           String fileName=System.currentTimeMillis()+"."+suffix;

		           //文件存放路径
		           String filePath = "D:/volador_home/data/Img/pic/";
		    	   
		           FileUtil.uploadFile(file.getBytes(), filePath, fileName);
		           result.put("code", 200);
		           result.put("filePath", "we/pic/"+fileName);
		        } catch (Exception e) {
		            // TODO: handle exception
		        	result.put("code", 404);
		        }
		}else{
			result.put("code", 404);
			result.put("massage", "非法请求");
		}

        

       
       pw.print(JsonUtil.objectToJson(result));
       pw.flush();
       pw.close();
		
	}
}
