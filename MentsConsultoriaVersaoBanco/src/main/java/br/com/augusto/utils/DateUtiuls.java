package br.com.augusto.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtiuls {
	private static final String TIME_FORMAT_PATTERN = "dd/MM/yyyy";
	
	/**
     * Converte data para String no formato 'dd/MM/yyyy'.
     *
     * @param input
     * @return
     */
    public static String formatDate(Date input) {
        LocalDateTime date = getLocalDateTime(input);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(TIME_FORMAT_PATTERN);
        return date.format(formatter);
    }
    
    /**
     * Converte Date para LocalDateTime.
     *
     * @param date
     * @return
     */
    public static LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
