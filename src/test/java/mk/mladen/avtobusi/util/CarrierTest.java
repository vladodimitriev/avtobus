package mk.mladen.avtobusi.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
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

        for(String car : carriers) {
            car = car.replace("-"," ");
            car = car.replace(".","");
            String[] names = car.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String n : names) {
                if (StringUtils.isNotBlank(n) &&
                        !"АD".equalsIgnoreCase(n.trim()) &&
                        !"DOO".equalsIgnoreCase(n.trim()) &&
                        !"DOOEL".equalsIgnoreCase(n.trim())) {

                    sb.append(n + " ");
                }
            }
            String result = sb.toString().trim();
            assertNotNull(result);
        }
    }

    @Test
    public void twoStringsTest() {
        System.setProperty("file.encoding","UTF-8");
        String n = "АD";
        assertEquals("AD", n);
    }
}
