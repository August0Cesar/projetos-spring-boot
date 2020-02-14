package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final String TIME_FORMAT_PATTERN = "dd/MM/yyyy";
	
	
	/**
	 * Classe formta Data para String formato "yyyy/MM/dd"
	 * @return String da data formatada
	 */
	static public String formataDate(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(calendar.getTime()).replaceAll("/", "-");
	}

}
