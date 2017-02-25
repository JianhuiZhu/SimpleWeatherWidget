package com.jianhui_zhu.simpleweatherwidget.utils;

import android.content.Context;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.R;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by jianhuizhu on 2017-02-24.
 */

public class DateTimeUtil {
    private static final int MONDAY = 1;
    private static final int TUESDAY = 2;
    private static final int WEDNESDAY = 3;
    private static final int THURSDAY = 4;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;


    public static boolean isToday(long date){
        LocalDate today = new LocalDate(System.currentTimeMillis());
        LocalDate dateToCompare = new LocalDate( date* 1000L);
        Log.d("isToday",today.toString()+" "+dateToCompare.toString());
        return today.isEqual(dateToCompare);
    }

    public static boolean isTomorrow(long date){
        LocalDate today = new LocalDate(System.currentTimeMillis());
        LocalDate dateToCompare = new LocalDate(date * 1000L);
        return today.plusDays(1).isEqual(dateToCompare);
    }

    public static String getWeekDay(Context context, long date){
        StringBuilder sb = new StringBuilder(20);
        boolean isTodayOrTomorrow = false;
        if(isToday(date)){
            return sb.append(context.getString(R.string.today)).toString();


        }else if(isTomorrow(date)){
            return sb.append(context.getString(R.string.tomorrow)).toString();

        }
        int weekday = new LocalDate(date * 1000L).getDayOfWeek();
        switch (weekday){
            case MONDAY:
                return formatWeekday(context.getString(R.string.monday),sb);
            case TUESDAY:
                return formatWeekday(context.getString(R.string.tuesday),sb);
            case WEDNESDAY:
                return formatWeekday(context.getString(R.string.wednesday),sb);
            case THURSDAY:
                return formatWeekday(context.getString(R.string.thursday),sb);
            case FRIDAY:
                return formatWeekday(context.getString(R.string.friday),sb);
            case SATURDAY:
                return formatWeekday(context.getString(R.string.saturday),sb);
            default:
                return formatWeekday(context.getString(R.string.sunday),sb);
        }
    }

    private static String formatWeekday(String weekday, StringBuilder sb){
        return sb.append(weekday).toString();
    }

    public static String getDateWithProperFormat(Context context, long date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern(context.getString(R.string.date_format));
        return fmt.print(new LocalDate(date));
    }
}
