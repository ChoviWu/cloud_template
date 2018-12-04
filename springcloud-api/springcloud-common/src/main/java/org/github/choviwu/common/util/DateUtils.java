package org.github..common.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @date 2018/7/6
 * Description :
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    private final static String FORMAT[] = {"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd hh:mm:ss"};

    /**
     * format Date
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date,String pattern){
        return DateFormatUtils.format(date,pattern);
    }

    /**
     * format Date
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        return DateFormatUtils.format(date,FORMAT[0]);
    }

    /**
     * format Date
     * @param time
     * @return
     */
    public static String formatDate(Long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return DateFormatUtils.format(calendar,FORMAT[0]);
    }


    /**
     * format Date
     * @param datetime
     * @return
     */
    public static Date parseDate(String datetime){
        return parseDate(datetime);
    }


}
