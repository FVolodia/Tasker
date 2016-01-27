package com.tasker.util;

import java.text.SimpleDateFormat;

/**
 * Created by FVolodia on 26.01.16.
 */
public class DateUtils {

    public static String getData(long data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(data);
    }

    public static String getTime(long time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
        return timeFormat.format(time);
    }

    public static String getFullDateAndTime(long data) {
        SimpleDateFormat dateAndTimeFormat = new SimpleDateFormat("dd.MM.yy  HH.mm");
        return dateAndTimeFormat.format(data);
    }
}
