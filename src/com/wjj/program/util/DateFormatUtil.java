package com.wjj.program.util;

import java.text.SimpleDateFormat;
import java.util.Date;


//��ȡʱ���ʽ
public class DateFormatUtil {
	public static String getFormatDate(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
