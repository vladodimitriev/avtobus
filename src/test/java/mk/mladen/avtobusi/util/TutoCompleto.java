package mk.mladen.avtobusi.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TutoCompleto {
	
	private static final String inputFile = "/home/vlado/Projects/Avtobusi/avtobus/files/linii.xls";
	private static final String outputFile = "/home/vlado/Projects/Avtobusi/avtobus/files/all-places.txt";
	private static final String citiesFile = "/home/vlado/Projects/Avtobusi/avtobus/files/cities.xls";

	public static void main(String[] args) throws BiffException, IOException {
		TutoCompleto tc = new TutoCompleto();
		tc.run();
	}

	private void run() throws BiffException, IOException {
		File inputWorkbook = new File(inputFile);
    	Workbook w = Workbook.getWorkbook(inputWorkbook);
        Sheet sheet = w.getSheet(0);

        int rows = sheet.getRows();
        Set<String> citySet = new HashSet<String>();
        for (int i = 0; i < rows; i++) {
			String cell14 = sheet.getCell(14, i).getContents();
			citySet.add(cell14);
        }
        
        System.out.println("Places count: " + citySet.size());
//        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
//        for(String s : citySet) {
//        	writer.println(s);
//        }
//        writer.close();
	}
	
	private boolean citiesCorrect(String[] cities) {
		if(cities[1].trim().equalsIgnoreCase("Скопје")) {
			return true;
		}
		return false;
	}
}
