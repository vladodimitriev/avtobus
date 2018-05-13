package mk.mladen.avtobusi.service;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jxl.Sheet;
import jxl.Workbook;
import mk.mladen.avtobusi.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@TestPropertySource("classpath:application-test.properties")
public class InsertDataServiceTest {
	
    @Autowired
    private InsertDataService insertDataService;

    private Set<String> citySet = new HashSet<String>();

    @Test
    public void insertDataIntoDbTest() {
        System.out.println("Inserting data into DB");
        insertDataService.insertDataIntoDb();
        System.out.println("Data inserted into DB");
    }

    @Test
    public void readDaysOfWorkTest() {
        boolean read = false;
        boolean written = false;

        final String inputFile = "linii-v2-test.xls";

        try {
            createCitySet();
            URL url = getClass().getResource(inputFile);
            File inputWorkbook = new File(url.getPath());
            Workbook w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
            int rows = sheet.getRows();
            List<String> list = new ArrayList<String>();

            for (int i = 0; i < rows; i++) {
                String cell23 = sheet.getCell(23, i).getContents();//days of work
                String cell14 = sheet.getCell(14, i).getContents();//place
                if (StringUtils.isNotBlank(cell23) && StringUtils.isNotBlank(cell14) && isCity(cell14.trim())) {
                    String dow = cell23.trim();
                    if(!list.contains(dow)) {
                        list.add(dow);
                    }
                } else {
                    continue;
                }
            }
            read = true;

            Path path = Paths.get("src/test/resources/mk/mladen/avtobusi/service/output-test.txt");
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for(String s : list) {
                    writer.write(s);
                    writer.newLine();
                }
                written = true;
            }

        } catch(Exception e) {
            e.printStackTrace();
            assertTrue("The path of the file linii-v2-test.xls is not correct", false);
        }
        assertTrue(read);
        assertTrue(written);
    }

    private boolean isCity(String town) {
        if (citySet.contains(town)) {
            return true;
        }
        return false;
    }

    private void createCitySet() {
    	try {
	    	final String citiesFile = "cities-test.txt";
	        URL url = getClass().getResource(citiesFile);
	        Path path = Paths.get(url.getPath());
	        Stream<String> stream = Files.lines(path);
	        Iterator<String> iterator = stream.iterator();
	        while (iterator.hasNext()) {
	            citySet.add(iterator.next());
	        }
	        stream.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    		assertTrue("The path of the file cities-test.txt is not correct", false);
    	}
    }
}
