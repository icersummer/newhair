package com.vj.newhair.utils;

import android.text.format.DateFormat;

public class StringUtil {

	/** 年月日时分秒 */
	public final static String FORMAT_YMDHMS = "yyyy-MM-dd kk:mm:ss";
	/** 获得当前时间 */
	public static CharSequence currentTime(String inFormat) {
		return DateFormat.format(inFormat, System.currentTimeMillis());
	}

}
