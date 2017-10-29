package mk.mladen.avtobusi.service;

import jxl.Sheet;
import jxl.Workbook;
import mk.mladen.avtobusi.ApplicationTestConfig;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTestConfig.class})
public class InsertDataServiceTest {

    @Autowired
    private InsertDataService insertDataService;

    private Set<String> citySet = new HashSet<String>();

    @Test
    public void insertDataIntoDvTest() {
        System.out.println("Inserting data into DB");
        insertDataService.insertDataIntoDb();
        System.out.println("Data inserted into DB");
    }

    @Test
    @Ignore
    public void readDaysOfWorkTest() {
        boolean read = false;
        boolean written = false;

        final String inputFile = "linii-bad.xls";
        final String outputFile = "output.txt";

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

            URL url2 = getClass().getResource(outputFile);
            Path path = Paths.get(url2.getPath());
            System.out.println("path: " + url2.getPath());
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for(String s : list) {
                    writer.write(s);
                    writer.newLine();
                }
                written = true;
            }

        } catch(Exception e) {
            e.printStackTrace();
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

    private void createCitySet() throws IOException {
    	final String citiesFile = "cities.txt";
        URL url = getClass().getResource(citiesFile);
        Path path = Paths.get(url.getPath());
        Stream<String> stream = Files.lines(path);
        Iterator<String> iterator = stream.iterator();
        while (iterator.hasNext()) {
            citySet.add(iterator.next());
        }
        stream.close();
    }
}
