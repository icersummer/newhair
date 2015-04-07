package com.vj.newhair.utils;

import android.os.Handler;
import android.os.Message;

public class AppManager {

	public static void updateUI(Handler uiHandler, int what,
			int arg1) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		msg.what= what;
		msg.arg1= arg1;
		if(null!= uiHandler){
			uiHandler.sendMessage(msg);
		}
	}

	
}
