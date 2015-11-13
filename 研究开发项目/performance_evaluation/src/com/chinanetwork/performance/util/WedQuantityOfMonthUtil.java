package com.chinanetwork.performance.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WedQuantityOfMonthUtil {
	//定义星期一到星期日对应的整数
	private static final int SUNDAY=1;		//星期日
	private static final int MONDAY=2;		//星期一
	private static final int TUESDAY=3;		//星期二
	private static final int WEDNESDAY=4;	//星期三
	private static final int THURSDAY=5;	//星期四
	private static final int FRIDAY=6;		//星期五
	private static final int SATURDAY=7;	//星期六
	
	//闰年个月对应的天数        （List的序号对应月份，value对应天数）
	private static int LEAPYEARMONTHDAYS[]={31,29,31,30,31,30,31,31,30,31,30,31};
	//非闰年各月对应的天数     （List的序号对应月份，value对应天数）
	private static int NOTLEAPYEARMONTHDAYS[]={31,28,31,30,31,30,31,31,30,31,30,31};
	
	public WedQuantityOfMonthUtil(){}
	
	public static boolean judgeLeapYear(int year){
		//闰年，非整百的年份能被4整除的
		//      整百的年份能被400整除的
		if(year%100==0){
			if(year%400==0){
				return true;
			}else{
				return false;
			}
		}else{
			if(year%4==0){
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 获取绩效月份中星期三的数量
	 * @param year	绩效年份
	 * @param month 绩效月份
	 * @return
	 */
	private static int getWedQuantityOfMonth(int year,int month){
		int yearMonthDays[];		//绩效年份中各月天数数组
		//判断当前绩效年份是否是闰年，来确定yearMonthDaysList的取值
		if(WedQuantityOfMonthUtil.judgeLeapYear(year)){
			yearMonthDays=WedQuantityOfMonthUtil.LEAPYEARMONTHDAYS;
		}else{
			yearMonthDays=WedQuantityOfMonthUtil.NOTLEAPYEARMONTHDAYS;
		}
		//获取当前绩效月份中的第一天的Calendar
		Calendar beginCalendar=Calendar.getInstance();
		beginCalendar.set(year,month-1,1);
		//获取当前绩效月份中的第一天是星期几
		int weekDay=beginCalendar.get(Calendar.DAY_OF_WEEK);
		//获取当前绩效月份中的第一天与当前绩效月份中第一个星期三相差多少天
		int dayLenght=0;
		if(weekDay<=WedQuantityOfMonthUtil.WEDNESDAY){
			dayLenght=WedQuantityOfMonthUtil.WEDNESDAY-weekDay;
		}else{
			dayLenght=(WedQuantityOfMonthUtil.WEDNESDAY+7)-weekDay;
		}
		//获取当前绩效月份中的第一个星期三对应的Calendar
		Calendar wedCalendar=Calendar.getInstance();
		wedCalendar.set(year, month-1, 1+dayLenght);
		//获取当前绩效月份一共有多少天
		int monthLenght=yearMonthDays[month-1];
		//获取当前绩效月份第一个星期三是多少号
		int wedday=1+dayLenght;
		//获取星期三的数量
		int quantity=0;
		while(wedday<=monthLenght){
			wedday=wedday+7;
			quantity++;
		}
		return quantity;
	}
	
	public static List<Integer> newGetWedQuantityOfMonth(int year,int month){
		//用于存储当前绩效月份中星期三对应的月号
		List<Integer> wedList=new ArrayList<Integer>();
		
		int yearMonthDays[];	//绩效年份中各月天数数组
		//判断当前绩效年份是否是闰年，来确定yearMonthDaysList的取值
		if(WedQuantityOfMonthUtil.judgeLeapYear(year)){
			yearMonthDays=WedQuantityOfMonthUtil.LEAPYEARMONTHDAYS;
		}else{
			yearMonthDays=WedQuantityOfMonthUtil.NOTLEAPYEARMONTHDAYS;
		}
		//获取当前绩效月份一共有多少天
		int monthDays=yearMonthDays[month-1];
		
		for(int i=1;i<=monthDays;i++){
			Calendar calendar=Calendar.getInstance();
			calendar.set(year, month-1, i);
			if(calendar.get(Calendar.DAY_OF_WEEK)==WedQuantityOfMonthUtil.WEDNESDAY){
				wedList.add(i);
			}
		}
		
		return wedList;
	}
	/**
	 * 这是一个我认为较为经典的算法，从此标志着跨年，跨月，和跨星期的时间控制掌握了。
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<Date> getBeginEndDates(int year,int month){
		List<Date> beginEndDates=new ArrayList<Date>();
		int monthDaysArr[];
		if(WedQuantityOfMonthUtil.judgeLeapYear(year)){
			monthDaysArr=WedQuantityOfMonthUtil.LEAPYEARMONTHDAYS;
		}else{
			monthDaysArr=WedQuantityOfMonthUtil.NOTLEAPYEARMONTHDAYS;
		}
		
		List<Integer> wedQuantitys=WedQuantityOfMonthUtil.newGetWedQuantityOfMonth(year, month);
		Calendar beginCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		
		for(int i=0;i<wedQuantitys.size();i++){
			int day=wedQuantitys.get(i);
			if(day<=2){
				if(month==1){//如果是1月，说明有跨年的问题
					endCalendar.set(year, month-1, day,23,59,59);
					beginCalendar.set(year-1, 11, 31+(day-2),0,0,0);   //如果是1，12月30号是星期一；如果是2，12月31号是星期一
					beginEndDates.add(beginCalendar.getTime());
					beginEndDates.add(endCalendar.getTime());
				}else{//如果不是一月，说明有跨月的问题，而且还要有闰年和非闰年的问题
					//结束时间
					endCalendar.set(year, month-1, day,23,59,59);
					//开始时间
					beginCalendar.set(year, month-2, monthDaysArr[month-2]+(day-2),0,0,0);	//月份的最后一号号即是月份的天数
					beginEndDates.add(beginCalendar.getTime());
					beginEndDates.add(endCalendar.getTime());
				}
			}else{//如果号大于2号，说明问题比较简单
				//结束时间
				endCalendar.set(year, month-1, day,23,59,59);
				//开始时间
				beginCalendar.set(year, month-1,(day-2),0,0,0);
				
				beginEndDates.add(beginCalendar.getTime());
				beginEndDates.add(endCalendar.getTime());
			}
		}
		
		return beginEndDates;
	}
	
	public static List<Date> getMonthBeginEndDate(int year, int month)
	{
		int yearMonthDays[];	//绩效年份中各月天数数组
		//判断当前绩效年份是否是闰年，来确定yearMonthDaysList的取值
		if(WedQuantityOfMonthUtil.judgeLeapYear(year)){
			yearMonthDays=WedQuantityOfMonthUtil.LEAPYEARMONTHDAYS;
		}else{
			yearMonthDays=WedQuantityOfMonthUtil.NOTLEAPYEARMONTHDAYS;
		}
		//获取当前绩效月份一共有多少天
		int monthDays=yearMonthDays[month-1];
		
		Date beginDate = new Date(year, month, 1);
		Date endDate = new Date(year, month, monthDays);
		
		List<Date> fuck = new ArrayList<Date>();
		fuck.add(beginDate);
		fuck.add(endDate);
		
		return fuck;
		
	}
}
