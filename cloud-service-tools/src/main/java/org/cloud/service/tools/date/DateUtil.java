package org.cloud.service.tools.date;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: 日期工具类
 */
public class DateUtil {
	/**
	 * @type: {@link long}
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 多少毫秒等于一秒
	 */
	private static final long MILLIS_IN_A_SECOND = 1000;

	/**
	 * @type: {@link long}
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 多少秒等于一分钟
	 */
	private static final long SECONDS_IN_A_MINUTE = 60;

	/**
	 * @type: {@link int}
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 多少月等于一年
	 */
	private static final int MONTHS_IN_A_YEAR = 12;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 屏蔽默认构造器
	 */
	private DateUtil() {
	}

	/**
	 * Copyright © 2018 Fist Team. All rights reserved.
	 *
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 内部静态类
	 */
	private static class DateUtilInstance {
		private static final DateUtil INSTANCE = new DateUtil();
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @return {@link DateUtil}
	 * @description: 内部静态方法
	 */
	public static DateUtil getInstance() {
		return DateUtilInstance.INSTANCE;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param year
	 * @return {@link Date}
	 * @description: 获取一年最后一天的临界值 例如2018-->2018-12-31 23:59:59
	 */
	private Date getYearLastTime(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param year
	 * @return {@link Boolean}
	 * @description: 判断是否是闰年
	 */
	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param date
	 * @return {@link Date}
	 * @description: 获取某年的最后时间 精确到秒 例如2018-->2018-12-31 23:59:59
	 */
	public Date getCurrYearLast(Date date) {
		Calendar currCal = Calendar.getInstance();
		currCal.setTime(date);
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLastTime(currentYear);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param date
	 * @return {@link Integer}
	 * @description: 获取月份的天数
	 */
	public int getMonthDays(int year, int month) {
		Boolean isLeapYear = isLeapYear(year);
		int days = 31;
		switch (month) {
		case 1:
			if (isLeapYear) {
				days = 29;
			} else {
				days = 28;
			}
			break;
		case 3:
			days = 30;
			break;
		case 5:
			days = 30;
			break;
		case 8:
			days = 30;
			break;
		case 10:
			days = 30;
			break;
		default:
			break;
		}
		return days;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param origDate
	 * @param amount
	 * @param timeUnit {@link Calendar}
	 * @return {@link Date}
	 * @description: 获取某个时间之后的时间
	 */
	public Date dateAfter(Date origDate, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origDate);
		calendar.add(timeUnit, amount);
		return calendar.getTime();
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param origDate
	 * @param amount
	 * @param timeUnit {@link Calendar}
	 * @return {@link Date}
	 * @description: 获取某个时间之前的时间
	 */
	public Date dateBefore(Date origDate, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origDate);
		calendar.add(timeUnit, -amount);
		return calendar.getTime();
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param startDate
	 * @param endDate
	 * @return {@link Integer}
	 * @description: 计算两个时间相差的周年数
	 */
	public int getYearDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("startDate cannot be after endDate!");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);

		calendar.setTime(endDate);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);

		int result = year2 - year1;
		if (month2 < month1) {
			result--;
		} else if (month2 == month1 && day2 < day1) {
			result--;
		}
		return result;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param startDate
	 * @param endDate
	 * @return {@link Integer}
	 * @description: 计算两个日期之前相差的整月数
	 */
	public int getMonthDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("startDate cannot be after endDate!");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(endDate);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DAY_OF_MONTH);

		int tempY = year2 - year1;
		int tempM = month2 - month1;
		if (month2 < month1) {
			tempY--;
			tempM += 12;
		}
		if (day2 < day1 && getMonthDays(year2, month2) != day2) {
			tempM--;
		}
		return tempY * MONTHS_IN_A_YEAR + tempM;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param startDate
	 * @param endDate
	 * @return {@link Integer}
	 * @description: 计算两个日期相差的整天数
	 */
	public int getDayDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date smdate;
		try {
			smdate = sdf.parse(sdf.format(startDate));
			Date bdate = sdf.parse(sdf.format(endDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param startTime
	 * @param endTime
	 * @return {@link Integer}
	 * @date 2016年12月19日
	 * @version 1.0
	 * @description 计算time2比time1晚多少分钟，忽略日期部分
	 */
	public int getMinuteDiffByTime(Date startTime, Date endTime) {
		long startMil = 0;
		long endMil = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		calendar.set(1900, 1, 1);
		startMil = calendar.getTimeInMillis();
		calendar.setTime(endTime);
		calendar.set(1900, 1, 1);
		endMil = calendar.getTimeInMillis();
		return (int) ((endMil - startMil) / MILLIS_IN_A_SECOND / SECONDS_IN_A_MINUTE);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param dateA
	 * @param dateB
	 * @return {@link Boolean}
	 * @description: 判断日期是否是同一天
	 */
	public boolean areSameDay(Date dateA, Date dateB) {
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(dateA);

		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(dateB);

		return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR) && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH) && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param endDate
	 * @return {@link Boolean}
	 * @description: 判断传入时间是否在当前时间之前
	 */
	public boolean isBeforeNow(Date endDate) {
		if (endDate == null) {
			return false;
		}
		Calendar nowTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(endDate);
		return nowTime.before(endTime);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param t
	 * @param startTime
	 * @param endTime
	 * @return {@link Boolean}
	 * @description: 判断时间是否在某个区间
	 */
	public boolean isEffectiveDate(Date t, Date startTime, Date endTime) {
		if (t.getTime() == startTime.getTime() || t.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(t);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param millisecond
	 * @return {@link Date}
	 * @description: 获取X毫秒后的日期
	 */
	public Date getMillisecondAfter(Long millisecond) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		// 当前时间往后推X毫秒
		calendar.add(Calendar.MILLISECOND, millisecond.intValue());
		Date updateDate = calendar.getTime();
		return updateDate;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param day
	 * @return {@link Date}
	 * @description: 获取X天后的日期
	 */
	public Date getDayAfter(int day) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		Date updateDate = calendar.getTime();
		return updateDate;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param startDate
	 * @param endDate
	 * @return {@link Long}
	 * @description: 计算两个时间相差多少秒
	 */
	public long calLastedTimes(Date startDate, Date endDate) {
		long timeEnd = endDate.getTime();
		long timeStart = startDate.getTime();
		long times = (timeEnd - timeStart) / 1000;
		return times;
	}

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = sf.parse("2016-12-30 12:56:32");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		System.out.println(DateUtil.getInstance().getCurrYearLast(new Date()));
		System.out.println(DateUtil.getInstance().getMonthDays(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)));
		System.out.println(DateUtil.getInstance().isLeapYear(calendar.get(Calendar.YEAR)));
		System.out.println(DateUtil.getInstance().dateAfter(parse, 2, Calendar.DAY_OF_YEAR));
		System.out.println(DateUtil.getInstance().dateBefore(parse, 2, Calendar.DAY_OF_YEAR));
		System.out.println(DateUtil.getInstance().getYearDiff(parse, new Date()));
		System.out.println(DateUtil.getInstance().getMonthDiff(parse, new Date()));
		System.out.println(DateUtil.getInstance().getDayDiff(parse, new Date()));
		System.out.println(DateUtil.getInstance().getMinuteDiffByTime(parse, new Date()));
		System.out.println(DateUtil.getInstance().areSameDay(parse, new Date()));
	}
}