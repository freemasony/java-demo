package com.HttpClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientUtil {

	private static Log log = LogFactory.getLog(HttpClientUtil.class);


	private static CloseableHttpClient httpClient;
	private static HttpEntity entity;
	public HttpClient client=new DefaultHttpClient(new ThreadSafeClientConnManager());

	static {
		PoolingHttpClientConnectionManager cm=new PoolingHttpClientConnectionManager();
		cm.setDefaultMaxPerRoute(10);
		cm.setMaxTotal(100);
		cm.setDefaultMaxPerRoute(50);
		httpClient= HttpClients.custom().setConnectionManager(cm).build();
	}


	public static String getMethod(String url){
		CloseableHttpResponse response=null;
		BufferedReader in=null;
		String result="";

		HttpGet httpGet=new HttpGet(url);
		try {
			response=httpClient.execute(httpGet);
			in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb=new StringBuffer();
			String line="";
			while ((line=in.readLine())!=null){
				sb.append(line);
			}
			in.close();
			result=sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null!=response)
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return result;
	}

	public static String postMethod(String url){
		CloseableHttpResponse response=null;
		StringBuffer buffer=new StringBuffer();
		String result="";

		HttpPost httpGet=new HttpPost(url);
		try {
			response=httpClient.execute(httpGet);
			 if(HttpStatus.SC_OK== response.getStatusLine().getStatusCode()){
			 	entity=response.getEntity();
				 buffer.append(EntityUtils.toByteArray(entity));
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null!=response)
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return buffer.toString();
	}



	public static String doGet(String url) {
		StringBuffer reponse = new StringBuffer();
		HttpClient client = new DefaultHttpClient();

		HttpGet HttpGet = new HttpGet(url);
		HttpResponse result = null;
		try {
			result = client.execute(HttpGet);
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String body = EntityUtils.toString(result.getEntity());
				System.out.println(body);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String doPost(String url) {
		StringBuffer reponse = new StringBuffer();
		HttpClient client = new DefaultHttpClient();

		client.getParams().setParameter("http.protocol.content-charset",
				HTTP.UTF_8);
		client.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
		client.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
		client.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,
				HTTP.UTF_8);

		HttpPost httpPost = new HttpPost(url);

		 
		httpPost.getParams().setParameter("http.protocol.content-charset",
				HTTP.UTF_8);
		httpPost.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
		httpPost.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
		httpPost.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,
				HTTP.UTF_8);

		 
		httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		 
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpResponse result = null;
		try {
			result = client.execute(httpPost);
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String body = EntityUtils.toString(result.getEntity());
				System.out.println(body);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}



	public static void main(String[] args) {
//		doGet("http://localhost:8090/lecturer/removeuser/1024388");

		doPost("http://127.0.0.1:8080/app/api/new/index?mobile=&device=ios&ver=474&sign=1");

	}

}
