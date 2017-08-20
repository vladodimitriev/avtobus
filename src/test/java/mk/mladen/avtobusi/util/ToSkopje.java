package mk.mladen.avtobusi.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;

public class ToSkopje {

	private static final String inputFile = "/home/vlado/Projects/Avtobusi/avtobus/files/linii.xls";
	private static final String outputFile = "/home/vlado/Projects/Avtobusi/avtobus/files/0004-to-skopje.txt";
	
	public static void main(String[] args) {
		ToSkopje os = new ToSkopje();
		os.run();
	}

	private void run() {
        try {
        	File inputWorkbook = new File(inputFile);
        	Workbook w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            int rows = sheet.getRows();

            Set<String> citySet = new HashSet<String>();
            for (int i = 0; i < rows; i++) {
            	String cell = sheet.getCell(2, i).getContents();
            	if(cell == null || cell == "") {
            		continue;
            	}
            	
            	String[] cities = cell.split(" - ");
            	if(cities.length == 2) {
            		if(citiesCorrect(cities) ) {
            			String cell0 = sheet.getCell(0, i).getContents();
            			String cell2 = sheet.getCell(2, i).getContents();
            			String cell5 = sheet.getCell(5, i).getContents();
            			String cell23 = sheet.getCell(23, i).getContents();
            			citySet.add(cell0 + ";" + cell2 + ";" + cell5 + ";" + cell23);
            		}
            	}
            }
            
//            if(citySet != null) {
//            	System.out.println("Places count: " + citySet.size());
//            }
//            
//            for(String s : citySet) {
//            	System.out.println(s);
//             }
            
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            for(String s : citySet) {
            	//System.out.println(s);
            	//String sql = "insert into PLACE (name_cyrilic, countryid) values ('" + s + "', '1');";
            	writer.println(s);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private boolean citiesCorrect(String[] cities) {
		if(cities[1].trim().equalsIgnoreCase("Скопје")) {
			return true;
		}
		return false;
	}
}
