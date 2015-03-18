package com.vj.newhair.utils;

import android.os.Environment;

public class PathUtil {
	public static final String ROOT = Environment.getExternalStorageDirectory().getPath() + "/newhair/";
	
	 /**
     * 应用日志目录文件
     */
    public static String APP_LOG_PATH = ROOT + "log/";
}
