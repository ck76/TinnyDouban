package com.ck.tinnydouban.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

    public static final String FORMAT_SLASH = "yyyy/MM/dd";

    public static final String FORMAT_ACROSS = "yyyy-MM-dd";

    private DateUtil() {

    }

    public static Date formatSlashStr2Date(String formatStr) {
        return formatStr2Date(FORMAT_SLASH, formatStr);
    }

    public static Date formatStr2Date(String formatType, String formatStr) {

        if(StringUtils.isEmpty(formatStr)) {
            return null;
        }


        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);//注意月份是MM
            Date date = simpleDateFormat.parse(formatStr);

            return date;
        } catch (ParseException e) {

            return new Date(System.currentTimeMillis());
        }
    }


    public static Date currentDate() {
        return new Date(System.currentTimeMillis());
    }


}
