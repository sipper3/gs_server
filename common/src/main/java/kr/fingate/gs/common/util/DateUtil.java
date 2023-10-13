package kr.fingate.gs.common.util;


import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    public static String SIMPLE_DATETIME_FORMAT = "yyyyMMddHHmmss";

    public static String getToday() {
        return getSimpleDateFormat(SIMPLE_DATE_FORMAT).format(getDate());
    }

    public static String getToday(String format) {
        return format(new Date(), format);
    }
    
    public static SimpleDateFormat getSimpleDateFormat(String format) {
        return new SimpleDateFormat(format, java.util.Locale.KOREA);
    }

//    public static String format(String date) {
//    	return format(date, SIMPLE_DATE_FORMAT, SIMPLE_DATE_FORMAT);
//    }

    public static String format(String date, String resultFormat) {
    	return format(date, SIMPLE_DATE_FORMAT, resultFormat);
    }

    public static String format(String date, String format, String resultFormat) {
    	return format(parse(date, format), resultFormat);
    }

    private static String format(long date) {
    	return format(new Date(date), SIMPLE_DATE_FORMAT);
    }

    public static String format(long date, String format) {
    	if(date <= 0) return "";
    	return format(new Date(date), format);
    }

    public static String format(Date date) {
        return format(date, SIMPLE_DATE_FORMAT);
    }

    public static String format(Date date, String format) {
        if(date == null) return "";
        return getSimpleDateFormat(format).format(date);
    }

    private static Date parse(String date) {
        return parse(date, SIMPLE_DATE_FORMAT);
    }
    
    private static Date parse(String date, String format) {

        if(StringUtils.isEmpty(date) || date.length() != format.length()) return null;

        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        try {
            return simpledateformat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    public static Calendar getCalendar() {
        return Calendar.getInstance(java.util.Locale.KOREA);
    }

    public static Calendar getCalendar(String date) {
        date = toYYYYMMDDDate(date);
        return getCalendar(Integer.parseInt(date.substring(0, 4))
                , Integer.parseInt(date.substring(4, 6))
                , Integer.parseInt(date.substring(6, 8)));
    }

    /**
     * 입력된 일자를 Calendar 객체로 반환
     *
     * @param year 년
     * @param month  월
     * @param date 일
     * @return 해당일자에 해당하는 Calendar
     */
    public static Calendar getCalendar(int year, int month, int date) {
        return getCalendar(year, month, date, 0, 0, 0);
    }

    /**
     * 입력된 정보를 Calendar 객체로 반환
     *
     * @param year 년
     * @param month 월
     * @param date 일
     * @param hour 시
     * @param minute 분
     * @return 해당일자에 해당하는 Calendar
     */
    public static Calendar getCalendar(int year, int month, int date, int hour, int minute) {
        return getCalendar(year, month, date, hour, minute, 0);
    }

    /**
     * 입력된 정보를 Calendar 객체로 반환
     *
     * @param year 년
     * @param month 월
     * @param date 일
     * @param hour 시
     * @param minute 분
     * @param second 초
     * @return 해당일자에 해당하는 Calendar
     */
    public static Calendar getCalendar(int year, int month, int date, int hour, int minute, int second) {
        Calendar calendar = getCalendar();
        calendar.set(year, month - 1, date, hour, minute, second);
        return calendar;
    }
    

    /**
     * 현재 일자를 Date 객체로 반환
     *
     * @return 현재 일자에 해당하는 Date
     */
    public static Date getDate() {
        return getCalendar().getTime();
    }

    public static Date getDate(String date) {
        return parse(toYYYYMMDDDate(date), SIMPLE_DATE_FORMAT);
    }

    /**
     * 입력된 일자를 Date 객체로 반환
     * @param year 년
     * @param month 월
     * @param date 일
     * @return 해당일자에 해당하는 Date
     */
    public static Date getDate(int year, int month, int date) {
        return getCalendar(year, month, date).getTime();
    }

    /**
     * 입력된 정보를 Date 객체로 반환
     *
     * @param year 년
     * @param month 월
     * @param date 일
     * @param hour 시
     * @param minute 분
     * @return 해당일자에 해당하는 Date
     */
    public static Date getDate(int year, int month, int date, int hour, int minute) {
        return getCalendar(year, month, date, hour, minute).getTime();
    }

    /**
     * 입력된 정보를 Date 객체로 반환
     *
     * @param year 년
     * @param month 월
     * @param date 일
     * @param hour 시
     * @param minute 분
     * @param second  초
     * @return 해당일자에 해당하는 Date
     */
    public static Date getDate(int year, int month, int date, int hour, int minute, int second) {
        return getCalendar(year, month, date, hour, minute, second).getTime();
    }

    /**
     * 현재 일자를 Timestamp 객체로 반환
     *
     * @return 현재 일자에 해당하는 Timestamp
     */
    public static Timestamp getTimeStamp() {
    	return new Timestamp(getCalendar().getTimeInMillis());
    }

    public static Timestamp getTimeStamp(String data){
        Date dt = parse(data);
        return Timestamp.valueOf(getSimpleDateFormat(SIMPLE_DATETIME_FORMAT).format(dt));
    }

    /**
     * 입력된 일자를 Timestamp 객체로 반환
     *
     * @param year 년
     * @param month 월
     * @param date 일
     * @return 해당일자에 해당하는 Timestamp
     */
    public static Timestamp getTimeStamp(int year, int month, int date) {
        return new Timestamp(getCalendar(year, month, date).getTimeInMillis());
    }

    /**
     * 입력된 정보를 Timestamp 객체로 반환
     *
     * @param year 년
     * @param month 월
     * @param date 일
     * @param hour 시
     * @param minute 분
     * @return 해당일자에 해당하는 Timestamp
     */
    public static Timestamp getTimeStamp(int year, int month, int date, int hour,int minute) {
        return new Timestamp(getCalendar(year, month, date, hour, minute).getTimeInMillis());
    }

    /**
     * 입력된 정보를 Timestamp 객체로 반환
     * @param year 년
     * @param month 월
     * @param date 일
     * @param hour 시
     * @param minute 분
     * @param second 초
     * @return 해당일자에 해당하는 Timestamp
     */
    public static Timestamp getTimeStamp(int year, int month, int date, int hour,
            int minute, int second) {
        return new Timestamp(getCalendar(year, month, date, hour, minute, second).getTimeInMillis());
    }

    /**
     * 두 날짜 사이 차이를 반환
     *
     * @param date1 과거
     * @param date2 미래
     * @return int
     */
    public static int dateDiff(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static String addMonth(String date, int months) {
        return addMonth(date, SIMPLE_DATE_FORMAT, months);
    }

    public static String addMonth(String date, String format, int months) {
        SimpleDateFormat localFormat = new SimpleDateFormat(format, java.util.Locale.KOREA);
        Calendar cal = getCalendar(date);
        cal.add(Calendar.MONTH, months);
        return localFormat.format(cal.getTime());
    }

    public static String addDay(String date, int days) {
        return addDay(date, SIMPLE_DATE_FORMAT, days);
    }
    public static String addDay(String date, String format, int days) {
        SimpleDateFormat localFormat = new SimpleDateFormat(format, java.util.Locale.KOREA);
        Calendar cal = getCalendar(date);
        cal.add(Calendar.DATE, days);
        return localFormat.format(cal.getTime());
    }

    public static String addMinute(String date, int minutes) {
        return addMinute(date, SIMPLE_DATE_FORMAT, minutes);
    }
    public static String addMinute(String date, String format, int minutes) {

        SimpleDateFormat localFormat = new SimpleDateFormat(format, java.util.Locale.KOREA);
        Calendar cal = getCalendar(date);
        cal.add(Calendar.MINUTE, minutes);
        return localFormat.format(cal.getTime());
    }

    public static String addSecond(String date, int second) {
        return addSecond(date, SIMPLE_DATE_FORMAT, second);
    }
    public static String addSecond(String date, String format, int second) {
        SimpleDateFormat localFormat = new SimpleDateFormat(format, java.util.Locale.KOREA);
        Calendar cal = getCalendar(date);
        cal.add(Calendar.SECOND, second);
        return localFormat.format(cal.getTime());
    }


	/**
	 * 날짜 값이 있을 경우만 체크 (날짜의 유효성) "yyyyMMdd" 형식
	 * @param date
	 * @return
	 */
	public static boolean isValidDate(String date) {
		return isValidDate(date, SIMPLE_DATE_FORMAT);
	}

	/**
	 * 날짜 값이 있을 경우만 체크 (날짜의 유효성) "yyyyMMdd" 형식
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean isValidDate(String date, String format) {
		if (StringUtils.isEmpty(date)) return false;
		if (StringUtils.isEmpty(format)) format = SIMPLE_DATE_FORMAT;
		boolean isValidDate;
		String afterValue = format(parse(date, format), format);
		isValidDate = date.equals(afterValue);
		return isValidDate;
	}

    /**
     * YYYYMMDDD 형식으로 변경
     * @param date
     * @return
     */
    private static String toYYYYMMDDDate(String date) {
        if (date.isEmpty()) return date;

        date = date.replaceAll("[^0-9]", "").trim();

        if (date.length() == 8) {
            return date;
        } if (date.length() > 8) {
            return date.substring(0, 8);
        } else {
            if (date.length() == 6) {
                if (Integer.parseInt(date.substring(0, 2)) > 50)
                    date = "19" + date;
                else
                    date = "20" + date;
            } else {
                return "";
            }
        }
        
        return date;
    }
}
