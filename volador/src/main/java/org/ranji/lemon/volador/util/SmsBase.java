package org.ranji.lemon.volador.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder; 

/**
 * 手机验证码工具类
 * @author 范小亚
 *
 */
public class SmsBase
{
	private String x_id="uekwlb";
	private String x_pwd="wangziqin123";
	public String SendSms(String mobile,String content) throws UnsupportedEncodingException{  
	    Integer x_ac=10;//发送信息  
	    HttpURLConnection httpconn = null;  
	    String result="Error";  
	    StringBuilder sb = new StringBuilder();  
	    sb.append("http://jiekou.56dxw.com/sms/HttpInterface.aspx?");  
	      
	    //以下是参数  
	    sb.append("comid=").append("3236"); 
	    sb.append("&username=").append(URLEncoder.encode(x_id, "gbk"));
	    sb.append("&userpwd=").append("wangziqin123");  
	    sb.append("&handtel=").append(mobile);  
	    sb.append("&&sendcontent=").append(URLEncoder.encode(content, "gbk"));   
	    sb.append("&sendtime=&smsnumber=").append("1"); 
	 
	    try {  
	        URL url = new URL(sb.toString()); 
	        System.out.println(url);
	        httpconn = (HttpURLConnection) url.openConnection();  
	        BufferedReader rd = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));  
	        result = rd.readLine();  
	        rd.close();  
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } finally{  
	            if(httpconn!=null){  
	                httpconn.disconnect();  
	                httpconn=null;  
	            }  
	      
	        }  
	        return result;  
	    }  
	/*
	private static final Logger Log = LoggerFactory.getLogger(MobileServerUtils.class);
	private String serviceURL = "";
	private String sn = "";         //序列号
	private String password = "";
	private String pwd = "";
	
	
	
	 * 构造函数
	 
	private MobileServerUtils(String serviceURL, String sn, String password) throws UnsupportedEncodingException {
		this.serviceURL = serviceURL;
		this.sn = sn;
		this.password = password;
		//密码为md(sn+password)
		this.pwd = this.getMD5(sn + password);
	}


	
	 * 方法名称：getMD5
	 * 功       能：字符串MD5加密
	 * 参       数：带转换的字符串
	 * 返  回  值：加密之后的字符串
	 
	private String getMD5(String sourceStr) throws UnsupportedEncodingException{
		String resultStr = "";
		try{
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			
			byte[] b = md5.digest();
			for(int i = 0; i < b.length; i++){
				char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8',  
                        '9', 'A', 'B', 'C', 'D', 'E', 'F'};
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4 & 0X0F)];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	 
     * 方法名称：mdgetSninfo  
     * 功    能：获取信息 
     * 参    数：sn,pwd(软件序列号，加密密码md5(sn+password)) 
     *  
     
	private String mdgetSninfo() {  
        String result = "";  
        String soapAction = "http://entinfo.cn/mdgetSninfo";  
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";  
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";  
        xml += "<soap:Body>";  
        xml += "<mdgetSninfo xmlns=\"http://entinfo.cn/\">";  
        xml += "<sn>" + sn + "</sn>";  
        xml += "<pwd>" + pwd + "</pwd>";  
        xml += "</mdgetSninfo>";  
        xml += "</soap:Body>";  
        xml += "</soap:Envelope>";  
  
        URL url;  
        try {  
            url = new URL(serviceURL);  
  
            URLConnection connection = url.openConnection();  
            HttpURLConnection httpconn = (HttpURLConnection) connection;  
            ByteArrayOutputStream bout = new ByteArrayOutputStream();  
            bout.write(xml.getBytes());  
            byte[] b = bout.toByteArray();  
            httpconn.setRequestProperty("Content-Length", String  
                    .valueOf(b.length));  
            httpconn.setRequestProperty("Content-Type",  
                    "text/xml; charset=gb2312");  
            httpconn.setRequestProperty("SOAPAction", soapAction);  
            httpconn.setRequestMethod("POST");  
            httpconn.setDoInput(true);  
            httpconn.setDoOutput(true);  
  
            OutputStream out = httpconn.getOutputStream();  
            out.write(b);  
            out.close();  
  
            InputStreamReader isr = new InputStreamReader(httpconn  
                    .getInputStream());  
            BufferedReader in = new BufferedReader(isr);  
            String inputLine;  
            while (null != (inputLine = in.readLine())) {  
                Pattern pattern = Pattern.compile("<mdgetSninfoResult>(.*)</mdgetSninfoResult>");  
                Matcher matcher = pattern.matcher(inputLine);  
                while (matcher.find()) {  
                    result = matcher.group(1);  
                }  
            }  
            return result;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
    }
	
	 
     * 方法名称：mdgxsend  
     * 功    能：发送个性短信  
     * 参    数：mobile,content,ext,stime,rrid,msgfmt(手机号，内容，扩展码，定时时间，唯一标识，内容编码) 
     * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的 
       
    private String mdgxsend(String mobile, String content, String ext, String stime,  
            String rrid, String msgfmt) {  
        String result = "";  
        String soapAction = "http://entinfo.cn/mdgxsend";  
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";  
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";  
        xml += "<soap:Body>";  
        xml += "<mdgxsend xmlns=\"http://entinfo.cn/\">";  
        xml += "<sn>" + sn + "</sn>";  
        xml += "<pwd>" + pwd + "</pwd>";  
        xml += "<mobile>" + mobile + "</mobile>";  
        xml += "<content>" + content + "</content>";  
        xml += "<ext>" + ext + "</ext>";  
        xml += "<stime>" + stime + "</stime>";  
        xml += "<rrid>" + rrid + "</rrid>";  
        xml += "<msgfmt>" + msgfmt + "</msgfmt>";  
        xml += "</mdgxsend>";  
        xml += "</soap:Body>";  
        xml += "</soap:Envelope>";  
  
        URL url;  
        try {  
            url = new URL(serviceURL);  
  
            URLConnection connection = url.openConnection();  
            HttpURLConnection httpconn = (HttpURLConnection) connection;  
            ByteArrayOutputStream bout = new ByteArrayOutputStream();  
            bout.write(xml.getBytes());  
            byte[] b = bout.toByteArray();  
            httpconn.setRequestProperty("Content-Length", String  
                    .valueOf(b.length));  
            httpconn.setRequestProperty("Content-Type",  
                    "text/xml; charset=gb2312");  
            httpconn.setRequestProperty("SOAPAction", soapAction);  
            httpconn.setRequestMethod("POST");  
            httpconn.setDoInput(true);  
            httpconn.setDoOutput(true);  
  
            OutputStream out = httpconn.getOutputStream();  
            out.write(b);  
            out.close();  

            InputStreamReader isr = new InputStreamReader(httpconn  
                    .getInputStream());  
            BufferedReader in = new BufferedReader(isr);  
            String inputLine;  
            while (null != (inputLine = in.readLine())) {  
                Pattern pattern = Pattern.compile("<mdgxsendResult>(.*)</mdgxsendResult>");  
                Matcher matcher = pattern.matcher(inputLine);  
                while (matcher.find()) {  
                    result = matcher.group(1);  
                }  
            }  
            return result;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
    }  
      
      
    *//** 
     * 方法名称：mdsmssend 
     * 功    能：发送短信  
     * @param mobile 手机号 
     * @param content 内容 
     * @param ext 扩展码 
     * @param stime 定时时间 
     * @param rrid 唯一标识 
     * @param msgfmt 内容编码 
     * @return 唯一标识，如果不填写rrid将返回系统生成的 
     *//*  
    private String mdsmssend(String mobile, String content, String ext, String stime,  
            String rrid,String msgfmt) {  
        String result = "";  
        String soapAction = "http://entinfo.cn/mdsmssend";  
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";  
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";  
        xml += "<soap:Body>";  
        xml += "<mdsmssend  xmlns=\"http://entinfo.cn/\">";  
        xml += "<sn>" + sn + "</sn>";  
        xml += "<pwd>" + pwd + "</pwd>";  
        xml += "<mobile>" + mobile + "</mobile>";  
        xml += "<content>" + content + "</content>";  
        xml += "<ext>" + ext + "</ext>";  
        xml += "<stime>" + stime + "</stime>";  
        xml += "<rrid>" + rrid + "</rrid>";  
        xml += "<msgfmt>" + msgfmt + "</msgfmt>";  
        xml += "</mdsmssend>";  
        xml += "</soap:Body>";  
        xml += "</soap:Envelope>";  
  
        URL url;  
        try {  
            url = new URL(serviceURL);  
  
            URLConnection connection = url.openConnection();  
            HttpURLConnection httpconn = (HttpURLConnection) connection;  
            ByteArrayOutputStream bout = new ByteArrayOutputStream();  
            //bout.write(xml.getBytes());  
            bout.write(xml.getBytes("GBK"));  
            byte[] b = bout.toByteArray();  
            httpconn.setRequestProperty("Content-Length", String  
                    .valueOf(b.length));  
            httpconn.setRequestProperty("Content-Type",  
                    "text/xml; charset=gb2312");  
            httpconn.setRequestProperty("SOAPAction", soapAction);  
            httpconn.setRequestMethod("POST");  
            httpconn.setDoInput(true);  
            httpconn.setDoOutput(true);  
  
            OutputStream out = httpconn.getOutputStream();  
            out.write(b);  
            out.close();  
  
            InputStreamReader isr = new InputStreamReader(httpconn  
                    .getInputStream());  
            BufferedReader in = new BufferedReader(isr);  
            String inputLine;  
            while (null != (inputLine = in.readLine())) {  
                Pattern pattern = Pattern.compile("<mdsmssendResult>(.*)</mdsmssendResult>");  
                Matcher matcher = pattern.matcher(inputLine);  
                while (matcher.find()) {  
                    result = matcher.group(1);  
                }  
            }  
            return result;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
    } */ 
}
