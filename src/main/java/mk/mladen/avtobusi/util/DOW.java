package mk.mladen.avtobusi.util;

public enum DOW {
	
	MONDAY("2"),
	TUESDAY("3"),
	WEDNESDAY("4"),
	THURSDAY("5"),
	FRIDAY("6"),
	SATURDAY("7"),
	SUNDAY("1");
	
	private String dow;
	
	private DOW(String dow) {
		this.dow = dow;
	}
	
	public static DOW getDayByDayNumber(String dayOfWeek) {
		for(DOW d : DOW.values()) {
			if(d.getDow().equals(dayOfWeek)) {
				return d;
			}
		}
		return null;
	}
	
	public static String getRealDayOfWeek(DOW day) {
		if(day == null) {
			return "0";
		}
		switch (day) {
			case MONDAY: return "1";
			case TUESDAY: return "2";
			case WEDNESDAY: return "3";
			case THURSDAY: return "4";
			case FRIDAY: return "5";
			case SATURDAY: return "6";
			case SUNDAY: return "7";
			default: return day.getDow();
		}
	}

	public String getDow() {
		return dow;
	}
}
