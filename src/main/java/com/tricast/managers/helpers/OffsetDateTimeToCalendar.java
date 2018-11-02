package com.tricast.managers.helpers;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class OffsetDateTimeToCalendar {
	static public Calendar convert(OffsetDateTime odt) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(odt.toZonedDateTime().getZone()));
    	cal.set(odt.getYear(), odt.getMonthValue()-1, odt.getDayOfMonth(), odt.getHour(), odt.getMinute(), odt.getSecond());
    	return cal;
	}
}
