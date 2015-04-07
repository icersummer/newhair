package com.vj.newhair.utils;

import android.os.Environment;

public class LPhone {
	
	public static boolean sdcard(){
		String status = Environment.getExternalStorageState();
		if(status.equals(Environment.MEDIA_MOUNTED)){
			return true;
		} else {
			return false;
		}
	}

}
