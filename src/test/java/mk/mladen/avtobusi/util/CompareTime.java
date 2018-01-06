package mk.mladen.avtobusi.util;

@SuppressWarnings("unused")
public class CompareTime {

	public static void main(String[] args) {
		CompareTime ct = new CompareTime();
		String t = ct.convertTime("13.45");
		t = ct.convertTime("2.15");
		t = ct.convertTime("13:45:00");
		t = ct.convertTime("9:45:00");
		
	} 

	private void run() {
		String dt1 = "10.05";
		String dt2 = "8.15";
		
		String[] dta1 = dt1.split("\\.");
		String[] dta2 = dt2.split("\\.");
		
		if(dta1.length == 2 && dta2.length == 2) {
			System.out.println(dt1.compareTo(dt2));
		}
		Integer i1 = Integer.valueOf(dta1[0]);
		Integer i2 = Integer.valueOf(dta2[0]);
	}
	
	private String convertTime(String time) {
		String newTime = time;
		if(time != null && (time.length() == 4)) { 
			newTime = "0"+time;
		} else if(time != null && (time.length() == 7)) { 
			newTime = "0"+time;
			newTime = newTime.substring(0, 5);
		} else if(time != null && (time.length() == 8)) { 
			newTime = newTime.substring(0, 5);
		}
		if(newTime.contains(".")) {
			newTime = newTime.replace(".", ":");
		}
		
		return newTime;
	}
}
