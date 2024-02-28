package top.yat.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * 时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static final String YMDH00 = "yyyy-MM-dd HH:00:00";
    public static final String YMD = "yyyy-MM-dd";


    /**
     * @Description: 过去月份返回数组
     * @Param: * past 天数 flag true未来 flag过去
     * @return: void
     * @Author: mxl
     * @Date: 2021/2/20
     */
    public static List<String> contextLoadsYue(Date date, long l) {
        if (null == date) {
            date = new Date();
        }
        LocalDate today = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ArrayList arrayList = new ArrayList();
        for (long i = 0L; i <= l; i++) {
            LocalDate localDate = today.minusMonths(i);
            String month = localDate.toString().substring(0, 7);
            arrayList.add(month);
        }
        Collections.reverse(arrayList);
//        System.out.println(arrayList+"--------------------");
        return arrayList;
    }

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }


    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getDatemin() {
        return dateTimeNow(YYYY_MM_DD_HH_MM);
    }

    public static Date getDateToday() {
        return parseDate(dateTimeNow(YYYY_MM_DD), YYYY_MM_DD);
    }

    public static String getDateYYYYMdd() {
        return dateTimeNow(YYYYMMDD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String getStartTime() {
        String str = dateTimeNow(YYYY_MM_DD_HH_MM_SS).split(" ")[0] + " 00:00:00";
        return str;
    }

    public static final String getEndTime() {
        String str = dateTimeNow(YYYY_MM_DD_HH_MM_SS).split(" ")[0] + " 23:59:00";
        return str;
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param timestamp 精确到秒的字符串
     * @return
     */
    public static String timeStampForString(Long timestamp, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(new Date(Long.parseLong(String.valueOf(timestamp))));
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param timestamp 精确到秒的字符串
     * @return
     */
    public static String timeStampForString(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(String.valueOf(timestamp))));
    }

    /**
     * 日期date转秒时间戳
     *
     * @param date
     * @return
     */
    public static Long dateFortimeStamp(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long timestamp = 0l;
        try {
            String valueOf = String.valueOf(sdf.parse(date).getTime() / 1000);
            timestamp = Long.parseLong(valueOf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return timestamp;
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param timestamp 精确到秒的字符串
     * @return
     */
    public static String timeForString(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(String.valueOf(timestamp))));
    }


    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期加减
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addDate(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, number);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        return calendar.getTime();
    }

    public static Date addMinute(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, number);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, number);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, number);
        return calendar.getTime();
    }

    public static Date addHour(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, number);
        return calendar.getTime();
    }

    public static Calendar getDefaultCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar;
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 计算两个时间相差多少秒
     */
    public static long getDatePoorSecond(Date endDate, Date nowDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        return diff / 1000;
    }

    /**
     * 查询指定日期范围内所有日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<String> findDates(Date dBegin, Date dEnd, String... dateFormat) {
        if (dBegin == null || dEnd == null) {
            return new ArrayList<>();
        }
        dBegin = parseDate(formatDate(dBegin, YYYY_MM_DD));
        dEnd = parseDate(formatDate(dEnd, YYYY_MM_DD));

        List<String> dateList = new ArrayList<String>();
        String format = YYYY_MM_DD;
        if (dateFormat != null && dateFormat.length > 0) {
            format = dateFormat[0];
        }
        SimpleDateFormat sd = new SimpleDateFormat(format);
        dateList.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(sd.format(calBegin.getTime()));
        }
        return dateList;
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Long getCurrentTimeStamp() {
        return getTimeStamp(new Date());
    }

    public static Long getTimeStamp(Date date) {
        return date.getTime() / 1000;
    }

    public static Long getTimeStamp13(Date date) {
        return date.getTime();
    }

    /**
     * 判断星期几
     *
     * @param datetime
     * @return
     */
    public static String dateOfWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String dateOfWeek() {
        return dateOfWeek(getDate());
    }

    /**
     * 获取某一天最后一秒
     *
     * @param date
     * @return
     */
    public static Date getLastSecondOfDay(Date date) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 昨天
     *
     * @param c
     * @return
     */
    public static Calendar getYesterday(Calendar c) {
        Calendar c2 = getCalendarByDate(c.getTime());
        c2.add(Calendar.DATE, -1);
        return c2;
    }

    /**
     * 昨天
     *
     * @return
     */
    public static Calendar getYesterdayCalendar() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return c;
    }

    /**
     * 昨天
     *
     * @return
     */
    public static Date getYesterday() {
        return getYesterdayCalendar().getTime();
    }

    /**
     * 获取昨天
     *
     * @param pattern
     * @return
     */
    public static String getYesterday(String pattern) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(calendar.getTime());
    }

    /**
     * 本年第一天
     *
     * @param date
     * @return
     */
    public static Date thisYearFirstDay(Date date) {
        Calendar c = getCalendarByDate(date);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * 本年最后一天
     *
     * @param date
     * @return
     */
    public static Date thisYearLastDay(Date date) {
        Calendar c = getCalendarByDate(date);
        c.set(Calendar.MONTH, 11);
        c.set(Calendar.DATE, 31);
        return c.getTime();
    }

    /**
     * 本月第一天
     *
     * @param date
     * @return
     */
    public static Date thisMonthFirstDay(Date date) {
        Calendar c = getCalendarByDate(date);
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * 本周周一
     *
     * @param date
     * @return
     */
    public static Date thisWeekMonday(Date date) {
        Calendar c = getCalendarByDate(date);
        c.set(Calendar.DAY_OF_WEEK, 2);
        return c.getTime();
    }

    /**
     * 本月最后一天
     *
     * @param date
     * @return
     */
    public static Date thisMonthLastDay(Date date) {
        Calendar c = getCalendarByDate(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 本季度第一天
     *
     * @param date
     * @return
     */
    public static Date thisQuarterFirstDay(Date date) {
        int firstMonth = thisQuarterFirstMonth(date);
        Calendar c = getCalendarByDate(date);
        c.set(Calendar.MONTH, firstMonth - 1);
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * 本季度第一个月
     *
     * @param date
     * @return
     */
    public static int thisQuarterFirstMonth(Date date) {
        Calendar c = getCalendarByDate(date);
        int q = getQuarterNumber(c);
        int month = 1;
        switch (q) {
            case 1:
                month = 1;
                break;
            case 2:
                month = 4;
                break;
            case 3:
                month = 7;
                break;
            case 4:
                month = 10;
                break;
        }
        return month;
    }

    /**
     * 本季度最后一个月
     *
     * @param date
     * @return
     */
    public static int thisQuarterLastMonth(Date date) {
        Calendar c = getCalendarByDate(date);
        int q = getQuarterNumber(c);
        int month = 1;
        switch (q) {
            case 1:
                month = 3;
                break;
            case 2:
                month = 6;
                break;
            case 3:
                month = 9;
                break;
            case 4:
                month = 12;
                break;
        }
        return month;
    }

    /**
     * 周末
     *
     * @param calWhichDay
     * @return
     */
    public static boolean isSunday(Date calWhichDay) {
        return getCalendarByDate(calWhichDay).get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 月最后一天
     *
     * @param calWhichDay
     * @return
     */
    public static boolean isLastDayOfTheMonth(Date calWhichDay) {
        Calendar c = getCalendarByDate(calWhichDay);
        c.add(Calendar.DATE, 1);
        if (c.get(Calendar.DATE) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 季度最后一天
     *
     * @param calWhichDay
     * @return
     */
    public static boolean isLastDayOfTheQuarter(Date calWhichDay) {
        if (!isLastDayOfTheMonth(calWhichDay)) {
            return false;
        }
        int currentMonth = calWhichDay.getMonth() + 1;
        if (currentMonth == 3 || currentMonth == 6 || currentMonth == 9
                || currentMonth == 12) {
            return true;
        }
        return false;
    }

    /**
     * 年最后一天
     *
     * @param calWhichDay
     * @return
     */
    public static boolean isLastDayOfTheYear(Date calWhichDay) {
        Calendar c = getCalendarByDate(calWhichDay);
        if (c.get(Calendar.MONTH) + 1 == 12 && c.get(Calendar.DATE) == 31) {
            return true;
        }
        return false;
    }

    public static Calendar getCalendarByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        return cal;
    }

    /**
     * 得到季度
     *
     * @param cal
     * @return
     */
    public static int getQuarterNumber(Calendar cal) {
        int month = cal.get(Calendar.MONTH) + 1;
        if (month <= 3) {
            return 1;
        }
        if (month <= 6) {
            return 2;
        }
        if (month <= 9) {
            return 3;
        }
        if (month <= 12) {
            return 4;
        }
        return 0;
    }

    /**
     * 根据日期获取季度
     *
     * @param date
     * @return
     */
    public static int getQuarterNumber(Date date) {
        return getQuarterNumber(getCalendarByDate(date));
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     */

    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {

        if (nowTime == null || startTime == null || endTime == null) {
            return false;
        }

        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();  // 当前时间

        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();

        begin.setTime(startTime); // 设置开始时间

        Calendar end = Calendar.getInstance();

        end.setTime(endTime);  // 结束时间

        if (date.after(begin) && date.before(end)) {
            return true;

        }
        return false;
    }

    /**
     * 当前系统时间 是否在时间段内
     *
     * @return
     * @throws Exception
     */
    public static boolean isTimeRange(String sTime, String eTime) {


        if (sTime == null || eTime == null || sTime.equals("") || eTime.equals("")) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = null;
        Date begin = null;
        Date end = null;
        try {
            now = df.parse(df.format(new Date()));
            begin = df.parse(sTime);
            end = df.parse(eTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(now);
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(begin);
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(end);
        if (nowTime.before(endTime) && nowTime.after(beginTime)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws ParseException {
//        Date date1 = DateUtils.parseDate("2021-04-13 12:26:58", YYYY_MM_DD);
//        Date date2 = parseDate("2021-04-13 12:27:00", YYYY_MM_DD);
//        List<String> dates = findDates(date1, date2);
//        System.out.println(dates);
//
//        System.out.println(dateOfWeek());
//        System.out.println(thisMonthLastDay(new Date()));
//
//        System.out.println(DateUtils.addDate(DateUtils.parseDate(DateUtils.getDate() + " 23:59:59"), 1));
//        System.out.println(getLastSecondOfDay(DateUtils.addDate(DateUtils.parseDate("2021-09-01 17:56:56"), 1)));

//        Date date = DateUtils.parseDate(DateUtils.getDate()+" 00:00:00");
//        System.out.println(date);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

        Date start = simpleDateFormat.parse("2021-10-29 00:00:00");

        Date end = simpleDateFormat.parse("2021-11-29 00:00:00");

        System.out.println(isEffectiveDate(new Date(), start, end));
        System.out.println(thisQuarterFirstDay(start));
        System.out.println(getCurrentMonth());
        System.out.println(thisWeekMonday(new Date()));
    }

    public static Date getDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        String pattern = null;

        // TODO 换成使用正则表达式 对常用的日期类型都做支持
        if (date.contains("T")) {
            date = date.replaceAll("T", " ");
        }
        if (date.contains(":")) {
            if (StringUtils.count(date, ':') == 2) {
                pattern = YYYY_MM_DD_HH_MM_SS;
            } else if (StringUtils.count(date, ':') == 1) {
                pattern = YYYY_MM_DD_HH_MM;
            }
        } else {
            pattern = YYYY_MM_DD;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得一年前的时间
     *
     * @param now
     * @return
     */
    public static Date get365Before(Date now) {
        Calendar cal = getCalendarByDate(now);
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    /**
     * 计算两个时间差返回天数
     */
    public static int getDiffDay(Date startTime, Date endTime) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return (int) day;
    }

    public static int getDateType(String startTime, String endTime) {
        int day = getDiffDay(getDate(startTime), getDate(endTime));
        if (day <= 1) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 得到一年第一天
     *
     * @param year
     * @return
     */
    public static Date getYearFirstDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 根据年份获取最后一天
     *
     * @param year
     * @return
     */
    public static Date getYearLastDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 31);
        return calendar.getTime();
    }

    public static Date getYearFirstDay() {
        Calendar calendar = Calendar.getInstance();
        Calendar currCal = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
        return calendar.getTime();
    }

    /**
     * 本年第一天
     *
     * @param date
     * @return
     */
    public static Date getYearFirstDay(Date date) {
        Calendar c = getCalendarByDate(date);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * 获取num月前的第一天
     *
     * @param num
     * @return
     */
    public static Date getLastMonthFirstDate(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -num);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime();
    }

    /*
     * 获取上个月第一天
     * @return
     */
    public static Date getLastMonthFirstDate() {
        return getLastMonthFirstDate(1);
    }

    /**
     * 上个月最后一天
     *
     * @param date
     * @return
     */
    public static Date lastMonthLastDay(Date date) {
        Calendar cal = getCalendarByDate(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    /**
     * 获得当年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = getDefaultCalendar();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获得当月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar calendar = getDefaultCalendar();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 某个月的天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 查询日期范围内的所有月份
     *
     * @param dBegin
     * @param dEnd
     * @param dateFormat
     * @return
     */
    public static List<String> findMonths(Date dBegin, Date dEnd, String... dateFormat) {
        if (dBegin == null || dEnd == null) {
            return new ArrayList<>();
        }
        dBegin = parseDate(formatDate(dBegin, YYYY_MM));
        dEnd = parseDate(formatDate(dEnd, YYYY_MM));

        List<String> dateList = new ArrayList<String>();
        String format = YYYY_MM;
        if (dateFormat != null && dateFormat.length > 0) {
            format = dateFormat[0];
        }
        SimpleDateFormat sd = new SimpleDateFormat(format);
        dateList.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.MONTH, 1);
            dateList.add(sd.format(calBegin.getTime()));
        }
        return dateList;
    }


    // 获取指定日期的全部自然周的第一天和最后一天
    public static List<Map<String, Object>> findWeek(String startTime, String endTime) {
        String start = startTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int num = 0;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, String> stringStringMap = new HashMap<>();

        while (start.compareTo(endTime) > 0) {
            Calendar cal = Calendar.getInstance(); // 获得一个日历
            Date datet = null;
            try {
                datet = f.parse(start);
                cal.setTime(datet);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
            stringStringMap = new HashMap<>();
            if (w != 1) {
                if (num == 0) {
                    stringStringMap.put("startTime", startTime);
                } else {
                    cal.add(Calendar.MONTH, +1);
                    Date time = cal.getTime();
                    stringStringMap.put("startTime", sdf.format(time));
                }
                cal.add(Calendar.MONTH, +(7 - w));
                Date time = cal.getTime();
                stringStringMap.put("endTime", sdf.format(time));
                start = sdf.format(time);
            }

        }


        return maps;
    }

    // 返回时间格式如：2020-02-17 00:00:00
    public static String getStartOfDay(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    // 返回时间格式如：2020-02-19 23:59:59
    public static String getEndOfDay(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String shijianCha(Date startDate, Date endDate) {
        String cha = "";
        // 相差的毫秒值
        Long milliseconds = endDate.getTime() - startDate.getTime();

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数

        long day = milliseconds / nd; // 计算相差多少天
        long hour = milliseconds % nd / nh; // 计算相差剩余多少小时
        long min = milliseconds % nd % nh / nm; // 计算相差剩余多少分钟
        long sec = milliseconds % nd % nh % nm / ns; // 计算相差剩余多少秒
//        System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒");
        // 时间相差：1天23小时59分钟59秒
        cha = day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        if (0 == day) {
            cha = hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (0 == hour) {
            cha = min + "分钟" + sec + "秒";
        }
        if (0 == min) {
            cha = sec + "秒";
        }
        return cha;
//        long hourAll = milliseconds / nh; // 计算相差多少小时
//        System.out.println("时间相差：" + hourAll + "小时" + min + "分钟" + sec + "秒");
//        // 时间相差：47小时59分钟59秒
//
//        long min2 = milliseconds / nm; // 计算相差多少分钟
//        System.out.println("时间相差：" + min2 + "分钟" + sec + "秒");
//        // 时间相差：2879分钟59秒
    }
}
