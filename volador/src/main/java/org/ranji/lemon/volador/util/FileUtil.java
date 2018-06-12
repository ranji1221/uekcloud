package org.ranji.lemon.volador.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	//文件上传工具类方法
	public static void uploadFile(byte[] file,String filePath,String fileName) throws Exception{
		File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
	}

}
