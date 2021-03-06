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
            if (daysOfWork.trim().equalsIgnoreCase("Секојдневно")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Режим на одржување: Секојдневно")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневна")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно, освен сабота")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен сабота")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен сабота")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Само во недела")) {
                daysOfWork = "7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно, освен недела")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен недела")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен недела")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојден освен сабота и недела")) {
                daysOfWork = "1,2,3,4,5";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен сабота и недела")) {
                daysOfWork = "1,2,3,4,5";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен сабота и недела")) {
                daysOfWork = "1,2,3,4,5";
            } else {
                daysOfWork = "1,2,3,4,5,6,7";
            }
        } else {
            daysOfWork = "1,2,3,4,5,6,7";
        }
        return daysOfWork;
    }

    public static String getOperationDays(String dow) {
    	String daysOfWork = createCorrectDaysOfWork(dow);
        if (StringUtils.isNotBlank(daysOfWork)) {
            if (daysOfWork.trim().equalsIgnoreCase("Секојдневно")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Режим на одржување: Секојдневно")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневна")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден")) {
                daysOfWork = "1,2,3,4,5,6,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно, освен сабота")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен сабота")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен сабота")) {
                daysOfWork = "1,2,3,4,5,7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Само во недела")) {
                daysOfWork = "7";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно, освен недела")) {
                daysOfWork = "1,2,3,4,5,6";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен недела")) {
                daysOfWork = "1,2,3,4,5,6";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен недела")) {
                daysOfWork = "1,2,3,4,5,6";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојден освен сабота и недела")) {
                daysOfWork = "1,2,3,4,5";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен сабота и недела")) {
                daysOfWork = "1,2,3,4,5";
            } else {
                daysOfWork = "1,2,3,4,5,6,7";
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
