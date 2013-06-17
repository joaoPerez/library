package com.library.web.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Utils {
	
	public static Date stringToDate(String data, Boolean mostraHora) {

		String[] date2 = data.split("/");
		Calendar calendar = new GregorianCalendar();

		if (!mostraHora) {
			calendar.set(Integer.valueOf(date2[2]),
					Integer.valueOf(date2[1]) - 1, Integer.valueOf(date2[0]),
					00, 00, 00);
		} else {
			calendar.set(Integer.valueOf(date2[2]),
					Integer.valueOf(date2[1]) - 1, Integer.valueOf(date2[0]));
		}
		return calendar.getTime();
	}

}
