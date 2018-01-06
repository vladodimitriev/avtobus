package mk.mladen.avtobusi.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenerateTimeTest {

	@Test
	public void test() {
		String arrivalTime = "1:30"; 
		String departureTime = "21:30";
		String travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("4:0", travelTime);
		
		departureTime = "21:10";
		arrivalTime = "1:50"; 
		travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("4:40", travelTime);
		
		departureTime = "21:50";
		arrivalTime = "1:10"; 
		travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("3:20", travelTime);
		
		departureTime = "21:30";
		arrivalTime = "23:30"; 
		travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("2:0", travelTime);
		
		departureTime = "11:30";
		arrivalTime = "15:30"; 
		travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("4:0", travelTime);
		
		departureTime = "00:01";
		arrivalTime = "00:22"; 
		travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("0:21", travelTime);
		
		departureTime = "11:58";
		arrivalTime = "00:23"; 
		travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("12:25", travelTime);
		
		departureTime = "23:58";
		arrivalTime = "00:23"; 
		travelTime = generateTravelTime(arrivalTime, departureTime);
		assertNotNull(travelTime);
		assertEquals("0:25", travelTime);
	}
	
	@Test
	public void testModul24() {
		int a = 21;
		int c = a % 12;
		assertTrue(c > 0);
	}
	
	private String generateTravelTime(String arrivalTime, String departureTime) {
		try {
			String[] ata = arrivalTime.split("\\.");
			String[] dta = departureTime.split("\\.");
			
			if(ata.length < 2 || dta.length < 2) {
				ata = arrivalTime.split("\\:");
				dta = departureTime.split("\\:");
			}
			Integer ah = Integer.valueOf(ata[0]);
			Integer dh = Integer.valueOf(dta[0]);

			System.out.println("AH: " + ah + " DH: " + dh);
			if(ah < dh) {
				Integer am = Integer.valueOf(ata[1]);
				Integer dm = Integer.valueOf(dta[1]);
				dh = 24 - dh;
				ah = 0 + ah;
				int mm = am - dm;
				int hh = ah + dh;
				int tm = hh * 60 + mm;
				int rh = tm / 60;
				int rm = tm % 60;
				return "" + rh + ":" + rm;
			} else {
				Integer am = Integer.valueOf(ata[1]);
				Integer dm = Integer.valueOf(dta[1]);

				int at = ah * 60 + am;
				int dt = dh * 60 + dm;

				int rmt = at - dt;
				int rh = rmt / 60;
				int rm = rmt % 60;
				return "" + rh + ":" + rm;
			}
		} catch(Exception e) {
			return "";
		}
	}
}
