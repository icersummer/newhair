package com.vj.newhair.common.net;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.vj.newhair.utils.AppLog;

public class HHttp {
	
	/**
	 * 编码格式，统一使用UTF-8
	 */
	private String encode;
	private HttpClient httpClient;
	private HttpParams httpParams;
	
	/**
	 * 请求超时
	 */
	private static int timeout;
	
	/**
	 * 缓存大小
	 */
	private int bufferSize;
	
	private static final String TAG = "HHttp";
	
	
	public HHttp(){
		timeout= 30*10000; // 300s
		bufferSize= 8192;
		encode= "UTF-8";
		httpParams= new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
		HttpConnectionParams.setSoTimeout(httpParams, timeout);
		HttpConnectionParams.setSocketBufferSize(httpParams, bufferSize);
		HttpClientParams.setRedirecting(httpParams, true);
		httpClient= new DefaultHttpClient(httpParams);
		
	}
	
	/**
	 * GET请求，无需参数
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url) throws Exception{
		return doGet(url, null);
	}
	
	/**
	 * POST请求，无需参数
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String doPost(String url) throws Exception{
		return doPost(url, null);
	}
	
	/**
	 * GET请求，添加参数
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Map<String, String> params) throws Exception{
		//		添加QueryString
		String paramStr= "";
		if(params!= null){
			Iterator<Entry<String, String>> iter = params.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, String> entry = iter.next();
				paramStr+= "&"+ entry.getKey()+ "="+ URLEncoder.encode(entry.getValue(), encode);
			}
		}
		
		// 创建HttpGet对象
		HttpGet get= new HttpGet(url);
		try{
			String strResp= "";
			// 发起请求
			AppLog.i(TAG, "doGet:"+url);
			HttpResponse resp= httpClient.execute(get);
			if(resp.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK){
				strResp= EntityUtils.toString(resp.getEntity()); //TODO good, EntityUtils
			} else {
				// 如果返回的StatusCode不是OK则抛出异常
				throw new Exception("Error Response:" + resp.getStatusLine().toString());
			}
			return strResp;
		}finally{
			get.abort();
		}
	}
	
	public String doPost(String url, Map<String, String> params) throws Exception {
		List<NameValuePair> data= new ArrayList<NameValuePair>();
		if(params!=null){
			Iterator<Entry<String, String>> iter = params.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, String> entry = iter.next();
				data.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			AppLog.i("params", data.toString());
		}
		HttpPost post= new HttpPost(url);
		try{
			if(params!= null){
				post.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
			}
			AppLog.i(TAG, "doPost:"+url);
			HttpResponse resp= httpClient.execute(post);
			String strResp= "";
			if (resp.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK){
				strResp = EntityUtils.toString(resp.getEntity());
			} else if(resp.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_INTERNAL_ERROR){
				throw new Exception("Error JSON:"+ resp.getStatusLine().toString());
			} else {
				throw new Exception("Error Response:" + resp.getStatusLine().toString());
			}
			AppLog.i(TAG, "result:"+strResp);
			return strResp;
		}finally{
			post.abort();
		}
	}

}
