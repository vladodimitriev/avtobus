package mk.mladen.avtobusi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class BusLineServiceTest {

	@Test
	public void dateConverterTest() {
	
		String dateString = "18/06/2017";
		Date date = new Date();
		if(StringUtils.isNotBlank(dateString)) {
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println("date string: " + dateString);
		System.out.println("date: " + date);
		
	}
}
