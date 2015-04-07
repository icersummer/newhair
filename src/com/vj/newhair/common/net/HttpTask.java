package com.vj.newhair.common.net;

import java.util.Map;

import org.json.JSONException;

import android.os.Handler;

import com.vj.newhair.R;
import com.vj.newhair.common.json.JsonParser;
import com.vj.newhair.common.json.JsonResult;
import com.vj.newhair.common.net.ActionOfUrl.JsonAction;
import com.vj.newhair.utils.AppLog;
import com.vj.newhair.utils.AppManager;

public class HttpTask {
	//private static final String FILE_NAME = "/sdcard/testrequest.json";
	//private static final int MaxBufferSize = 8 * 1024;

	private static final String TAG= "HttpTask";
	private Handler uiHandler;
	HHttp hhttp= null;
	
	public HttpTask(Handler uiHandler){
		this.uiHandler= uiHandler;
		hhttp= new HHttp();
	}
	public HttpTask(){
		hhttp= new HHttp();
	}
	
	public JsonResult getRequestByPost(JsonAction act, String url, Map<String, String> mapstr){
		String urlrequest= null;
		JsonResult requestResult= null;
		try{
			urlrequest= hhttp.doPost(ActionOfUrl.getURL(act, url), mapstr);
			requestResult= JsonParser.parse(urlrequest, act);
		} catch (JSONException e){
			e.printStackTrace();
			AppLog.d(TAG, e.getMessage());
			//TODO study deeper of AppManager.updateUI
			AppManager.updateUI(uiHandler, R.id.ui_show_text, R.string.exception_parse);
		} catch (Exception e) {
			e.printStackTrace();
			AppLog.d(TAG, e.getMessage());
			AppManager.updateUI(uiHandler, R.id.ui_show_text,R.string.exception_timeout);
		}	
		return requestResult;
	}
}
