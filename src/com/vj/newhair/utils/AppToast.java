package com.vj.newhair.utils;

import android.content.Context;
import android.widget.Toast;

public class AppToast {
	
	protected static final String TAG = "AppToast";
	public static Toast toast;
	
	public static void showShortText(Context context, int resId){
		try {
			if(context== null)
				return;
			if(toast!= null)
				toast.cancel();
			toast = Toast.makeText(context, context.getString(resId), Toast.LENGTH_LONG);
			toast.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppLog.e(TAG, e.getMessage());
		}
	}

}
