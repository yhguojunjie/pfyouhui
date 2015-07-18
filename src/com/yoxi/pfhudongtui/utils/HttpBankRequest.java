package com.yoxi.pfhudongtui.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yoxi.jgframework.util.JSONHelper;
public class HttpBankRequest {
		
	    public static void main(String[] args) throws Exception {
	    	//{
	    	//	"reason": "成功返回",
	    	//	"result": {
	    	//	"card": "6228370126867142",  /*卡号*/
	    	//	"province": "广东省",                /*归属省份*/
	    	//	"city": "深圳",                          /*归属城市*/
	    	//	"bank": "中国农业银行",           /*归属银行*/
	    	//	"type": "贷记卡",                      /*银行卡类型*/
	    	//	"cardname": "金穗贷记卡",       /*银行卡名称*/
	    	//	"tel": "95599"                           /*客服电话*/
	    	//	},
	    	//	"error_code": 0
	    	//	}

	    	//appkey
	    	String appkey = "32bd08a765b0a26fe03013ef36105647";
	    	//银行卡号
	    	String card = "6214855920729981";
	    	//返回数据的格式json/xml
	    	String dtype="json";
	        //发送 GET 请求
	        String s=HttpBankRequest.post("http://v.juhe.cn/bankcard/query", "key="+appkey+"&card="+card+"&dtype="+dtype);
	        System.out.println(s);
            JSONObject obj =JSONHelper.toJSONObject(s);  
            //JSONArray obj2 =JSONHelper.toJSONArray("result");
            String bank = obj.getJSONObject("result").getString("bank");
            String type = obj.getJSONObject("result").getString("type");
            String cardname = obj.getJSONObject("result").getString("cardname");
	        System.out.println("reason:"+obj.getString("reason"));
	        System.out.println("bank:"+bank);
	        
	        //发送 POST 请求
	      //  String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
	        //System.out.println(sr);
	    }
	
	    /**
	     * 
	     * @param card
	     * @return
	     * @throws Exception 
	     */
	    public static String getCardStr(String card) throws Exception{
	    	//appkey
	    	String appkey = "32bd08a765b0a26fe03013ef36105647";
	    	//返回数据的格式json/xml
	    	String dtype="json";
	        //发送 GET 请求
	    	String s=HttpBankRequest.post("http://v.juhe.cn/bankcard/query", "key="+appkey+"&card="+card+"&dtype="+dtype);
	    	// s="{\"reason\":\"成功\",\"result\":{\"card\":\"6214855920729981\",\"province\":\"\",\"city\":\"\",\"bank\":\"招商银行\",\"cardname\":\"银联IC金卡\",\"type\":\"借记卡\",\"tel\":\"95555\"},\"error_code\":0}";
	           
	        System.out.println(s);
	        JSONObject obj =JSONHelper.toJSONObject(s);  
            //JSONArray obj2 =JSONHelper.toJSONArray("result");
            String bank = obj.getJSONObject("result").getString("bank");
            String type = obj.getJSONObject("result").getString("type");
            String cardname = obj.getJSONObject("result").getString("cardname");
	        System.out.println("reason:"+obj.getString("reason"));
	        System.out.println("bank:"+bank);
            
	        return bank+"-"+type+"-"+cardname;
	    }
	  //发送一个GET请求
	    public static String get(String path) throws Exception{
	        HttpURLConnection httpConn=null;
	        BufferedReader in=null;
	        try {
	            URL url=new URL(path);
	            httpConn=(HttpURLConnection)url.openConnection();
	             
	            //读取响应
	            if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){
	                StringBuffer content=new StringBuffer();
	                String tempStr="";
	                in=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
	                while((tempStr=in.readLine())!=null){
	                    content.append(tempStr);
	                }
	                return content.toString();
	            }else{
	                throw new Exception("请求出现了问题!");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            in.close();
	            httpConn.disconnect();
	        }
	        return null;
	    }
	    //发送一个GET请求,参数形式key1=value1&key2=value2...
	    public static String post(String path,String params) throws Exception{
	        HttpURLConnection httpConn=null;
	        BufferedReader in=null;
	        PrintWriter out=null;
	        try {
	            URL url=new URL(path);
	            httpConn=(HttpURLConnection)url.openConnection();
	            httpConn.setRequestMethod("POST");
	            httpConn.setDoInput(true);
	            httpConn.setDoOutput(true);
	             
	            //发送post请求参数
	            out=new PrintWriter(httpConn.getOutputStream());
	            out.println(params);
	            out.flush();
	             
	            //读取响应
	            if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){
	                StringBuffer content=new StringBuffer();
	                String tempStr="";
	                in=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"utf-8"));
	                while((tempStr=in.readLine())!=null){
	                    content.append(tempStr);
	                }
	                return content.toString();
	            }else{
	                throw new Exception("请求出现了问题!");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            in.close();
	            out.close();
	            httpConn.disconnect();
	        }
	        return null;
	    }
	     
	}
