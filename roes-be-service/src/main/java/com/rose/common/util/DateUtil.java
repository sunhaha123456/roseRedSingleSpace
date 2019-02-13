package com.rose.common.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public final static String DATEFORMAT = "yyyy-MM-dd";
    public final static String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDateTime() {
        return format(new Date(), DATEFORMAT);
    }

    public static String formatDate2Date(Date date) {
        return format(date, DATEFORMAT);
    }

    public static String formatDate2Time(Date date) {
        return format(date, TIMEFORMAT);
    }

    public static Date formatStr2Date(String date) {
        return format(date, DATEFORMAT);
    }

    public static Date formatStr2Time(String date) {
        return format(date, TIMEFORMAT);
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date format(String strDate, String format) {
        if (StringUtil.isNotEmpty(strDate)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(strDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}