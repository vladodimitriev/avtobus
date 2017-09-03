package mk.mladen.avtobusi.service.impl;

import org.apache.commons.lang3.StringUtils;

public class OperationsUtilTest {


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

    public static String getOperationDays(String daysOfWork) {
        if (StringUtils.isNotBlank(daysOfWork)) {
            if (daysOfWork.trim().equalsIgnoreCase("Секојдневно")) {
                daysOfWork = "1,2,3,4,5,6,7,p";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојден освен недела")) {
                daysOfWork = "1,2,3,4,5,6,p";
            } else if (daysOfWork.trim().equalsIgnoreCase("Линијата не сообраќа во недела и државни празници")) {
                daysOfWork = "1,2,3,4,5,6";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен недела")) {
                daysOfWork = "1,2,3,4,5,6,p";
            } else if (daysOfWork.trim().equalsIgnoreCase("Сообраќа од понеделник до петок во период од 15.09 до 31,12 во годината и секој ден од понеделник до петок во период од 01,02 до 15,05 во годината")) {
                daysOfWork = "1,2,3,4,5,p";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен сабота и недела во период од 01.09 до 10.06")) {
                daysOfWork = "1,2,3,4,5,p";
            } else if (daysOfWork.trim().equalsIgnoreCase("Секој ден во периодот од 15.06 до 31.08, секој ден освен во вторник и петок сообраќа до и од Добрушево, во вторник и петок сообраќа до и од Ношпал")) {
                daysOfWork = "1,2,3,4,5,6,7,p";
            } else if (daysOfWork.trim().equalsIgnoreCase("секој ден освен среда и сабота")) {
                daysOfWork = "1,2,4,5,7,p";
            }
            //секој ден освен среда и сабота
            //Секој ден во периодот од 15.06 до 31.08, секој ден освен во вторник и петок сообраќа до и од Добрушево, во вторник и петок сообраќа до и од Ношпал
            //Секојдневно освен сабота и недела во период од 01.09 до 10.06
            //Сообраќа од понеделник до петок во период од 15.09 до 31,12 во годината и секој ден од понеделник до петок во период од 01,02 до 15,05 во годината
            //Секој ден освен недела
            //Линијата не сообраќа во недела и државни празници
        }
        return daysOfWork;
    }

    public static String createLatinName(String name) {
        return MacedonianToLatin.getInstance().translate(name);
    }


}

