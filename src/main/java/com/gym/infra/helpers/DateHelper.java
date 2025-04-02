package com.gym.infra.helpers;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    public static Date modify(Date date, int hours, int minutes, int seconds) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, hours);
            calendar.set(Calendar.MINUTE, minutes);
            calendar.set(Calendar.SECOND, seconds);
            return calendar.getTime();
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date atStartOfDay(Date date) {
        return modify(date, 0, 0, 0);
    }

    public static Date atEndOfDay(Date date) {
        return modify(date, 23, 59, 59);
    }

}
