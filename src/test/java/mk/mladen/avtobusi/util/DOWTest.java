package mk.mladen.avtobusi.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class DOWTest {

	@Test
	public void dowTest() {
		String dateString = "18/06/2017";
		Date date = new Date();
		if(StringUtils.isNotBlank(dateString)) {
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.setTimeZone(TimeZone.getTimeZone("CET"));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		String dow = String.valueOf(dayOfWeek);
		assertEquals("1", dow);
		
		DOW day = DOW.getDayByDayNumber(dow);
		String realDow = dow;
		if(day != null) {
		    realDow = DOW.getRealDayOfWeek(day);
		}
		
		assertEquals(DOW.SUNDAY, day);
		assertEquals("7", realDow);
	}
	
	@Test
	public void getRealDayOfWeekTest() {
		String realDow = DOW.getRealDayOfWeek(null);
		assertEquals("0", realDow);
	}
}
