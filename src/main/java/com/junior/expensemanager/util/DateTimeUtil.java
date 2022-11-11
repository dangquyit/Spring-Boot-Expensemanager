package com.junior.expensemanager.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String convertToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(date);
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = sdf.parse(dateString);
        return new Date(utilDate.getTime());
    }

    public static String getCurrentMonthStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        return today.withDayOfMonth(1).format(formatter);
    }

    public static String getCurrentMonthDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }
}
