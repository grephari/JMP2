package com.epam.jmp2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
	
	public static String parseStringDateToGetYear(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date formatteddate = formatter.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(formatteddate);
		return String.valueOf(c.get(Calendar.YEAR));
	}
}