package com.junior.expensemanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

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
}
