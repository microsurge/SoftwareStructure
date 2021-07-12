package Applications;

import java.util.Calendar;

public class DateRecorder {
	private Calendar cal = Calendar.getInstance();
	public DateRecorder(long d) {
		int day = (int)(d&100);
		int year = (int)(d/10000);
		int month = (int)((d - (long)year*10000)/100) - 1;
		cal.set(year, month, day);
	}
	
	public static int getDaysBetween(long start, long end) {
		int startDate = (int)(start%100), endDate = (int)(end%100);
		int startYear = (int)(start/10000), endYear = (int)(end/10000);
		int startMonth = (int)((start - (long)startYear*10000)/100) - 1;
		int endMonth = (int)((end - (long)endYear*10000)/100) - 1;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(startYear, startMonth, startDate);
		cal2.set(endYear, endMonth, endDate);
		return (int)((cal2.getTimeInMillis() - cal1.getTimeInMillis())/(1000*60*60*24));
	}
	
	public static int getDayOfWeek(long date) {
		int tempDate = (int)(date%100);
		int tempYear = (int)(date/10000);
		int tempMonth = (int)((date - (long)tempYear*10000)/100) - 1;
		Calendar cal1 = Calendar.getInstance();
		cal1.set(tempYear, tempMonth, tempDate);
		return cal1.get(Calendar.DAY_OF_WEEK);
	}
	public static long daysAdd(long start, int add) {
		int startDate = (int)(start%100);
		int startYear = (int)(start/10000);
		int startMonth = (int)((start - (long)startYear*10000)/100) - 1;
		long ret = 0;
		Calendar cal1 = Calendar.getInstance();
		cal1.set(startYear, startMonth, startDate);
		cal1.add(Calendar.DATE, add);
		ret = (long)cal1.get(Calendar.YEAR)*10000 + (long)(cal1.get(Calendar.MONTH)+1)*100 + (long)cal1.get(Calendar.DATE);
		return ret;
	}
	
	public static void main(String[] args) {
		//test
		System.out.println(DateRecorder.getDaysBetween(20210630, 20210801));
		System.out.println(DateRecorder.daysAdd(20200730, 2));
	}
	
	
}
