package com.epam.jmp2.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/*
 * Added by Kevin: yes, utility classes are evil, but better to extract date/time methods here than to put them everywhere...
 */
public abstract class DateTimeUtils {

	private static final Pattern strYearPattern = Pattern.compile("^\\d{4}$");
	private static final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

	public static boolean isValidYear(String year) {
		return year != null && strYearPattern.matcher(year).matches();
	}

	public static String formatDate(Date date) {
		if (date == null)
			return null;
		return dateFormatter.format(date);
	}

	public static Date toDate(String date) {
		if (date == null)
			return null;
		try {
			return dateFormatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatLocalDate(LocalDate lDate) {
		if (lDate == null)
			return null;
		return lDate.format(dateTimeFormatter);
	}

	public static String formatLocalTime(LocalTime lTime) {
		if (lTime == null)
			return null;
		return lTime.format(timeFormatter);
	}

	public static LocalDate toLocalDate(String strDate) {
		if (strDate == null)
			return null;
		return LocalDate.parse(strDate, dateTimeFormatter);
	}

	public static LocalTime toLocalTime(String strTime) {
		if (strTime == null)
			return null;
		return LocalTime.parse(strTime, timeFormatter);
	}

}
