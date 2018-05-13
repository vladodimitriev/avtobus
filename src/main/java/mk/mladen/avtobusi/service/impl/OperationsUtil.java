package mk.mladen.avtobusi.service.impl;

import org.apache.commons.lang3.StringUtils;

public class OperationsUtil {

    public static String getOperationPeriod(String monthsOfWork) {
        if (StringUtils.isBlank(monthsOfWork)) {
            return "1,2,3,4,5,6,7,8,9,10,11,12";
        }

        if (monthsOfWork.trim().equalsIgnoreCase("Секојдневно")) {
            monthsOfWork = "1,2,3,4,5,6,7,8,9,10,11,12";
        } else if (monthsOfWork.trim().equalsIgnoreCase("Режим на одржување: Секојдневно")) {
            monthsOfWork = "1,2,3,4,5,6,7,8,9,10,11,12";
        } else {
            monthsOfWork = "1,2,3,4,5,6,7,8,9,10,11,12";
        }
        return monthsOfWork;
    }

    public static String getOperationMonths(String daysOfWork) {
        if (StringUtils.isBlank(daysOfWork)) {
            return "1,2,3,4,5,6,7,8,9,10,11,12";
        }

        if (StringUtils.isNotBlank(daysOfWork)) {
        	final String down = daysOfWork.trim();
        	switch(down) {
	        	case "Секојдневно": return "1,2,3,4,5,6,7";
	        	case "Режим на одржување: Секојдневно": return "1,2,3,4,5,6,7";
	        	case "Секојдневна": return "1,2,3,4,5,6,7";
	        	case "Секој ден": return "1,2,3,4,5,6,7";
	        	case "Секојдневно, освен сабота": return "1,2,3,4,5,7";
	        	case "Секојдневно освен сабота": return "1,2,3,4,5,7";
	        	case "Секој ден освен сабота": return "1,2,3,4,5,7";
	        	case "Само во сабота": return "6";
	        	case "Само во недела": return "7";
	        	case "Секојдневно освен недела": return "1,2,3,4,5,6";
	        	case "Секој ден освен недела": return "1,2,3,4,5,6";
	        	case "Секојден освен сабота и недела": return "1,2,3,4,5";
	        	case "Секој ден освен сабота и недела": return "1,2,3,4,5";
	        	case "Секојдневно освен сабота и недела": return "1,2,3,4,5";
	        	case "Секојдневно освен  сабота и недела": return "1,2,3,4,5";
	        	case "Секојдневно, освен недела": return "1,2,3,4,5,6";
	        	default: return "1,2,3,4,5,6,7";
        	}
        } else {
            daysOfWork = "1,2,3,4,5,6,7";
        }
        return daysOfWork;
    }

    public static String getOperationDays(String dow) {
    	String daysOfWork = createCorrectDaysOfWork(dow);
    	if (StringUtils.isNotBlank(daysOfWork)) {
        	final String down = daysOfWork.trim();
        	switch(down) {
	        	case "Секојдневно": return "1,2,3,4,5,6,7";
	        	case "Режим на одржување: Секојдневно": return "1,2,3,4,5,6,7";
	        	case "Секојдневна": return "1,2,3,4,5,6,7";
	        	case "Секој ден": return "1,2,3,4,5,6,7";
	        	case "Секојдневно, освен сабота": return "1,2,3,4,5,7";
	        	case "Секојдневно освен сабота": return "1,2,3,4,5,7";
	        	case "Секој ден освен сабота": return "1,2,3,4,5,7";
	        	case "Само во сабота": return "6";
	        	case "Само во недела": return "7";
	        	case "Секојдневно освен недела": return "1,2,3,4,5,6";
	        	case "Секој ден освен недела": return "1,2,3,4,5,6";
	        	case "Секојден освен сабота и недела": return "1,2,3,4,5";
	        	case "Секој ден освен сабота и недела": return "1,2,3,4,5";
	        	case "Секојдневно освен сабота и недела": return "1,2,3,4,5";
	        	case "Секојдневно освен  сабота и недела": return "1,2,3,4,5";
	        	case "Секојдневно, освен недела": return "1,2,3,4,5,6";
	        	default: return "1,2,3,4,5,6,7";
        	}
        } else {
            daysOfWork = "1,2,3,4,5,6,7";
        }
        return daysOfWork;
    }
    
    private static String createCorrectDaysOfWork(String dow) {
		String[] dows = dow.split(" ");
		StringBuilder result = new StringBuilder("");
		for(String d : dows) {
			if(StringUtils.isNotBlank(d)) {
				result.append(d + " ");
			}
		}
		String correct = result.toString().trim();
		return correct;
	}

    public static String createLatinName(String name) {
    	try {
    		return MacedonianToLatin.getInstance().translate(name);
    	}catch(Exception e) {
    		return "";
    	}
    }
    
    public static String createMacedonianName(String name) {
    	try {
    		return LatinToMacedonian.getInstance().translate(name);
    	} catch(Exception e) {
    		return "";
    	}
    }


}
