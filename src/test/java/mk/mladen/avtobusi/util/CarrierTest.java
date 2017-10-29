package mk.mladen.avtobusi.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class CarrierTest {

    @Test
    public void createCarrierName() {
        String name1 = "TRАNSKOP А.D - BITOLА";
        String name2 = "TRАNSKOP - BITOLА";
        String name3 = "TRАNSKOP-BITOLА";

        String name4 = "АVTO АTOM KOCHАNI";
        String name5 = "АVTO АTOM - KOCHАNI";
        String name6 = "АVTO АTOM DOO - KOCHАNI";

        String name7 = "STRUMICА EKSPRES-STRUMICА";
        String name8 = "STRUMICА EKSPRES STRUMICА";
        String name9 = "SАM-VEL KOMPАNI KАVАDАRCI";

        String[] carriers = new String[]{name1, name2, name3, name4, name5, name6, name7, name8, name9};

        name1 = name1.replace("-"," ");
        name1 = name1.replace(".","");
        System.out.println(name1);

        for(String car : carriers) {
            car = car.replace("-"," ");
            car = car.replace(".","");
            String[] names = car.split(" ");
            //System.out.println(names.length);
            StringBuilder sb = new StringBuilder();
            for (String n : names) {
                //System.out.println("name: " + n);
                if (StringUtils.isNotBlank(n) &&
                        !"АD".equalsIgnoreCase(n.trim()) &&
                        !"DOO".equalsIgnoreCase(n.trim()) &&
                        !"DOOEL".equalsIgnoreCase(n.trim())) {

                    sb.append(n + " ");
                }
            }
            String result = sb.toString().trim();
            System.out.println("Result: " + result);
        }
//        names = result.split(" ");
//        for(String n : names) {
//
//            if(StringUtils.isNotBlank(n) &&
//                    !"АD".equalsIgnoreCase(n.trim())) {
//                System.out.println("name2: " + n);
//            }
//        }


    }

    @Test
    public void twoStringsTest() {
        System.setProperty("file.encoding","UTF-8");
        String n = new String("АD");
        //String n = " AD ";
        if(StringUtils.isNotBlank(n) &&
                !"АD".equalsIgnoreCase(n.trim())) {
            System.out.println("AD true" );
        } else {
            System.out.println("AD false" );
        }
    }
}
