package com.vj.newhair.utils;

import com.vj.newhair.widget.CustomProgressDialog;

import android.content.Context;

public class HProgess {
	
	private static CustomProgressDialog dialog= null;
	
	public static void show(Context context, String message){
		try {
			if(context==null ) return;
			if(dialog!=null)
				dialog.dismiss();
			dialog = new CustomProgressDialog(context, null);
			// dialog.setCancelable(false);
			dialog.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void dismiss(){
		try{
			if(dialog!=null)
				dialog.dismiss();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
