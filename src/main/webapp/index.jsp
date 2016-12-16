<%@page import="java.util.Date"%>  
<%@page import="org.dom4j.Element"%>  
<%@page import="org.dom4j.DocumentHelper"%>  
<%@page import="org.dom4j.Document"%>  
<%@page import="java.io.IOException"%>  
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>  
<%@page import="java.io.BufferedReader"%>  
<%@page import="java.io.Reader"%>
<%@page import="java.io.PrintWriter"%>   
<%@page import="java.security.MessageDigest"%>  
<%@page import="java.util.Arrays"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="org.apache.http.HttpResponse"%>
<%@page import="org.apache.http.client.ClientProtocolException"%>
<%@page import="org.apache.http.client.methods.HttpGet"%>
<%@page import="org.apache.http.impl.client.HttpClients"%>
<%@page import="org.apache.http.util.EntityUtils"%>  
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%  
    //WeiXinHandler为内部类不能使用非final类型的对象  
    final String TOKEN="malei";  
    final HttpServletRequest final_request=request;   
    final HttpServletResponse final_response=response;  
%>  
<%   
class WeiXinHandler{  
    public void valid() throws ClientProtocolException, IOException{  
        String echostr=final_request.getParameter("echostr");  
        if(null==echostr||echostr.isEmpty()){  
                 System.out.println("request come"); 
            responseMsg();  
                 System.out.println("response over"); 
        }else{  
            if(this.checkSignature()){  
                this.print(echostr);  
            }else{  
                this.print("error");                                                                                                                                                                                                                                                                                                                                           
            }  
        }  
    }  
    //自动回复内容  
    public void responseMsg() throws ClientProtocolException, IOException{  
        String postStr=null;  
        try{  
            postStr=this.readStreamParameter(final_request.getInputStream());  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        if (null!=postStr&&!postStr.isEmpty()){  
            Document document=null;  
            try{  
                document = DocumentHelper.parseText(postStr);  
            }catch(Exception e){  
                e.printStackTrace();  
            }  

            if(null==document){  
                return;  
            }  
            Element root=document.getRootElement();  
            String fromUsername = root.elementText("FromUserName");  
            String toUsername = root.elementText("ToUserName");  
            String keyword = URLEncoder.encode(root.elementTextTrim("Content"), "utf-8");  
            //System.out.println("keyword:" + keyword); 
            String time = new Date().getTime()+"";  
            String resultStr = "";
            String textTpl = "<xml>"+  
                        "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
                        "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
                        "<CreateTime>%3$s</CreateTime>"+  
                        "<MsgType><![CDATA[%4$s]]></MsgType>"+  
                        "<Content><![CDATA[%5$s]]></Content>"+  
                        "</xml>";               
           if (null!=keyword && !keyword.equals("") && "wowopic".equals(keyword) ) {

                textTpl = "<xml>"+  
                            "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
                            "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
                            "<CreateTime>%3$s</CreateTime>"+  
                            "<MsgType><![CDATA[news]]></MsgType>"+  
                            "<ArticleCount>1</ArticleCount>" +
                            "<Articles>" +
                            "<item>" +
                            "<Title><![CDATA[title]]></Title>" +
                            "<Description><![CDATA[description]]></Description>" +
                            "<PicUrl><![CDATA[http://megumi.mybluemix.net/megumi/pic/homePagePhoto.jpg]]></PicUrl>" +
                            "<Url><![CDATA[http://megumi.mybluemix.net/megumi/pic/homePagePhoto.jpg]]></Url>" +
                            "</item>" +
                            "</Articles>" +                           
                            "</xml>"; 

                String picUrlStr = "http://megumi.mybluemix.net/megumi/pic/homePagePhoto.jpg";  
                resultStr = textTpl.format(textTpl, fromUsername, toUsername, time, picUrlStr);  
                 System.out.println(resultStr); 
                this.print(resultStr);  

           } else if(null!=keyword&&!keyword.equals(""))  
            {  
                String msgType = "text";  
                String contentStr = getTulingApi(keyword);
                if ("owk8XwgWqFLyL2soalhKuDZc4HAM".equals(fromUsername)) {
                    contentStr = "刘燕儿" +  contentStr ;
                }  
                resultStr = textTpl.format(textTpl, fromUsername, toUsername, time, msgType, contentStr);  
                 System.out.println(resultStr); 
                this.print(resultStr);  
            }else{  
                this.print("Input something...");  
            }  
  
        }else {
            this.print(getTulingApi(URLEncoder.encode("你好", "utf-8")));  
        }  
    }  
    //微信接口验证  
    public boolean checkSignature(){  
        String signature = final_request.getParameter("signature");  
        String timestamp = final_request.getParameter("timestamp");  
        String nonce = final_request.getParameter("nonce");  
        String token=TOKEN;  
        String[] tmpArr={token,timestamp,nonce};  
        Arrays.sort(tmpArr);  
        String tmpStr=this.ArrayToString(tmpArr);  
        tmpStr=this.SHA1Encode(tmpStr);  
        if(tmpStr.equalsIgnoreCase(signature)){  
        System.out.println("Singn OK!"); 
            return true;  
        }else{  
            return false;  
        }  
    }  
    //向请求端发送返回数据  
    public void print(String content){  
        try{
            final_response.setContentType("text/xml");  
            final_response.getWriter().print(content);  
            final_response.getWriter().flush();  
            final_response.getWriter().close();  

        //PrintWriter out = final_response.getWriter();  
        //out.print(content);
        //out.close(); 
        }catch(Exception e){  
              
        }  
    }  
    //数组转字符串  
    public String ArrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
    //sha1加密  
    public String SHA1Encode(String sourceString) {  
        String resultString = null;  
        try {  
           resultString = new String(sourceString);  
           MessageDigest md = MessageDigest.getInstance("SHA-1");  
           resultString = byte2hexString(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }  
    public final String byte2hexString(byte[] bytes) {  
        StringBuffer buf = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            if (((int) bytes[i] & 0xff) < 0x10) {  
                buf.append("0");  
            }  
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
        }  
        return buf.toString().toUpperCase();  
    }  
    //从输入流读取post参数  
    public String readStreamParameter(InputStream in){  
        StringBuilder buffer = new StringBuilder();  
        BufferedReader reader=null;   
        try{  
            reader = new BufferedReader(new InputStreamReader(in,"utf-8"));  
            String line=null;  
                    System.out.println(reader);
            while((line = reader.readLine())!=null){  
                System.out.println(line);  
                buffer.append(line);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(null!=reader){  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return buffer.toString();  
    }
    
    /** 调用图灵机器人平台接口 
        *   需要导入的包：commons-logging-1.0.4.jar、 httpclient-4.3.1.jar、httpcore-4.3.jar  
        */  
        public String getTulingApi(String inputSt) throws ClientProtocolException, IOException {      
      
      System.out.println("inputSt:" + URLDecoder.decode(inputSt, "utf-8"));  
      
            //String INFO = URLEncoder.encode(inputSt, "utf-8");  
            String requesturl = "http://www.tuling123.com/openapi/api?key=1407a77fee49d56c7d9535e5318af602&info="+ URLDecoder.decode(inputSt, "utf-8"); 
            HttpGet request = new HttpGet(requesturl); 
            String result = null; 
            HttpResponse response = HttpClients.createDefault().execute(request);  
      
            //200即正确的返回码  
            if(response.getStatusLine().getStatusCode()==200){  
                result = EntityUtils.toString(response.getEntity());  
                System.out.println("返回结果："+result); 
            }
            String[] tmp = result.split(":");  
            return  tmp[2];
        }  
      
}  
%>  
<%  
    WeiXinHandler handler=new WeiXinHandler();  
    handler.valid();  
%>