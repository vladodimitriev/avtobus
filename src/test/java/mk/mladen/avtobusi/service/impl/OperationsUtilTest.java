package mk.mladen.avtobusi.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class OperationsUtilTest {
	
	@Test
	public void macedonianToLatinPrilepTest() {
		String mkdName = OperationsUtil.createLatinName("Прилеп");
		assertNotNull(mkdName);
		assertEquals("Prilep", mkdName);
	}
	
	@Test
	public void macedonianToLatinTest() {
		String mkdName = OperationsUtil.createLatinName("Крушево");
		assertNotNull(mkdName);
		assertEquals("Krushevo", mkdName);
	}
	
	@Test
	public void latinToMacedonianTest() {
		String mkdName = OperationsUtil.createMacedonianName("Skopje");
		assertNotNull(mkdName);
		assertEquals("Скопје", mkdName);
	}

	@Test
	public void operationDaysTest() {
		String text = "Секојдневно освен недела";
		String result = getOperationDays(text);
		assertNotNull(result);
		assertEquals("1,2,3,4,5,6", result);
	}
	
    public static String getOperationPeriod(String daysOfWork) {
        daysOfWork = "01/01-31/12";
        if (StringUtils.isNotBlank(daysOfWork)) {
           if (daysOfWork.trim().equalsIgnoreCase("Сообраќа од понеделник до петок во период од 15.09 до 31,12 во годината и секој ден од понеделник до петок во период од 01,02 до 15,05 во годината")) {
               daysOfWork = "15/09 - 31/12 & 01/02 - 15/05";
           } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен сабота и недела во период од 01.09 до 10.06")) {
               daysOfWork = "01/01-10/06 & 01/09 - 31/12";
           } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден во периодот од 15.06 до 31.08, секој ден освен во вторник и петок сообраќа до и од Добрушево, во вторник и петок сообраќа до и од Ношпал")) {
               daysOfWork = "15/06 - 31/08";
           }
        }
        return daysOfWork;
    }

    public static String getOperationMonths(String daysOfWork) {
        daysOfWork = "1,2,3,4,5,6,7,8,9,10,11,12";
        if (StringUtils.isNotBlank(daysOfWork)) {
            if (daysOfWork.trim().equalsIgnoreCase("Сообраќа од понеделник до петок во период од 15.09 до 31,12 во годината и секој ден од понеделник до петок во период од 01,02 до 15,05 во годината")) {
                daysOfWork = "2,3,4,5,9,10,11,12";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен сабота и недела во период од 01.09 до 10.06")) {
                daysOfWork = "1,2,3,4,5,6,9,10,11,12";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден во периодот од 15.06 до 31.08, секој ден освен во вторник и петок сообраќа до и од Добрушево, во вторник и петок сообраќа до и од Ношпал")) {
                daysOfWork = "6,7,8";
            }
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
        return MacedonianToLatin.getInstance().translate(name);
    }

    @SuppressWarnings("unused")
	@Test
    public void generateTravelTime() {
        String arrivalTime = "18.55";
        String departureTime = "16.45";
        try {
            String[] ata = arrivalTime.split("\\.");
            String[] dta = departureTime.split("\\.");
            Integer ah = Integer.valueOf(ata[0]);
            Integer dh = Integer.valueOf(dta[0]);

            Integer am = Integer.valueOf(ata[1]);
            Integer dm = Integer.valueOf(dta[1]);

            int at = ah * 60 + am;
            int dt = dh * 60 + dm;


            int rmt = at - dt;
            int rh = rmt / 60;
            int rm = rmt % 60;
        } catch(Exception e) {
        }
        assertTrue(true);
    }

    @SuppressWarnings("unused")
	@Test
    public void generateTravelTime2() {
        String arrivalTime = "09.00";
        String departureTime = "12.40";
        try {
            String[] ata = arrivalTime.split("\\.");
            String[] dta = departureTime.split("\\.");
            Integer ah = Integer.valueOf(ata[0]);
            Integer dh = Integer.valueOf(dta[0]);

            Integer am = Integer.valueOf(ata[1]);
            Integer dm = Integer.valueOf(dta[1]);

            int at = ah * 60 + am;
            int dt = dh * 60 + dm;
            int rmt = at - dt;
            int rh = rmt / 60;
            int rm = rmt % 60;
        } catch(Exception e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @SuppressWarnings("unused")
	@Test
    public void generateTravelTime3() {
        String travelTime = "3:40";
        String result;
        try {
            String[] tta = travelTime.split(":");
            String tth = tta[0] + " " + "hours(s)";
            String ttm = "";
            if(hasMinutes(tta[1])) {
                ttm = tta[1] + " " + "min(s)";
            }
            result = tth + " " + ttm;
        } catch(Exception e) {
            result = travelTime;
        }
    }

    private boolean hasMinutes(String s) {
        try {
            Integer min = Integer.valueOf(s);
            if(min > 0) {
                return true;
            }
            return false;
        }catch(Exception e) {
            return false;
        }
    }

}

