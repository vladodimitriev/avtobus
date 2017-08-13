package mk.mladen.avtobusi.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

    private String inputFile;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    private void readLinii() {
    	File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
        	w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            int columns = sheet.getColumns();
            int rows = sheet.getRows();

            System.out.println("columns: " + columns + ", rows: " + rows);
            Set<String> citySet = new HashSet<String>();
            for (int i = 0; i < rows; i++) {
            	String cell = sheet.getCell(2, i).getContents();
            	if(cell == null || cell == "") {
            		continue;
            	}
            	
            	if(cell.trim().equalsIgnoreCase("Битола - Скопје")) {
            		citySet.add(cell);
            	}
            	
            	String[] cities = cell.split("-");
            	if(cities.length >= 2) {
            		//System.out.println(cities[]);
            		//cities[0];
            		if(cities[0].trim().equalsIgnoreCase("Битола")
            				&& cities[1].trim().equalsIgnoreCase("Охрид")) {
            			
            			String cell2 = sheet.getCell(5, i).getContents();
            			citySet.add(cell + " " + cell2);
            		}
            	}
            }
            
            if(citySet != null) {
            	System.out.println("Places count: " + citySet.size());
            }
            
            PrintWriter writer = new PrintWriter("linii.sql", "UTF-8");
            for(String s : citySet) {
            	System.out.println(s);
            	//String sql = "insert into PLACE (name_cyrilic, countryid) values ('" + s + "', '1');";
            	//writer.println(s);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    private void readPlaces() throws IOException {
    	File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            int columns = sheet.getColumns();
            int rows = sheet.getRows();

            System.out.println("columns: " + columns + ", rows: " + rows);
            Set<String> citySet = new HashSet<String>();
            for (int i = 8; i < rows; i++) {
            	String cell = sheet.getCell(2, i).getContents();
            	if(cell == null || cell == "") {
            		continue;
            	}
            	
            	String[] cities = cell.split("-");
            	if(cities.length >= 2) {
            		//System.out.println(cities[]);
            		//cities[0];
            		citySet.add(cities[0].trim());
            		citySet.add(cities[1].trim());
            	}
            	
            	//citySet.add(cell);
            }
            
            if(citySet != null) {
            	System.out.println("Places count: " + citySet.size());
            }
            
            try{
                PrintWriter writer = new PrintWriter("places.sql", "UTF-8");
                for(String s : citySet) {
                	String sql = "insert into PLACE (name_cyrilic, countryid) values ('" + s + "', '1');";
                	writer.println(sql);
                }
                writer.close();
            } catch (IOException e) {
               // do something
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
		
	}

    public void readCarriers() throws IOException  {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            int columns = sheet.getColumns();
            int rows = sheet.getRows();

            System.out.println("columns: " + columns + ", rows: " + rows);
            Set<String> carriers = new HashSet<String>();
            for (int i = 8; i < rows; i++) {
            	String cell0 = sheet.getCell(0, i).getContents();
            	if(cell0 == null || cell0 == "") {
            		continue;
            	}
            	
            	carriers.add(cell0);
            	//String sql = "insert into CARRIER (name_cyrilic, carrierplaceid) values ('" + cell0 + "', '1');";
            	//System.out.println("SQL: " + sql);
//            	String cell1 = sheet.getCell(1, i).getContents();
//            	String cell2 = sheet.getCell(2, i).getContents();
//            	String cell3 = sheet.getCell(3, i).getContents();
//            	String cell4 = sheet.getCell(4, i).getContents();
//            	String cell5 = sheet.getCell(5, i).getContents();
//            	String cell6 = sheet.getCell(6, i).getContents();
//            	String cell7 = sheet.getCell(7, i).getContents();
//            	String cell8 = sheet.getCell(8, i).getContents();
//            	String cell9 = sheet.getCell(9, i).getContents();
//            	
//            	String rowString1 = "["+cell0 + "] [" + cell1 + "] [" + cell2 + "] [" + cell3 + "] [" + cell4 + "] [" + cell5 + "] [" + cell6 + "] [" + cell7 + "] [" + cell8 + "] [" + cell9 + "]";
//            	
//            	String cell10 = sheet.getCell(10, i).getContents();
//            	String cell11 = sheet.getCell(11, i).getContents();
//            	String cell12 = sheet.getCell(12, i).getContents();
//            	String cell13 = sheet.getCell(13, i).getContents();
//            	String cell14 = sheet.getCell(14, i).getContents();
//            	String cell15 = sheet.getCell(15, i).getContents();
//            	String cell16 = sheet.getCell(16, i).getContents();
//            	String cell17 = sheet.getCell(17, i).getContents();
//            	String cell18 = sheet.getCell(18, i).getContents();
//            	String cell19 = sheet.getCell(19, i).getContents();
//            	
//            	String rowString2 = "["+cell10 + "] [" + cell11 + "] [" + cell12 + "] [" + cell13 + "] [" + cell14 + "] [" + cell15 + "] [" + cell16 + "] [" + cell17 + "] [" + cell18 + "] [" + cell19 + "]";
//            	
//            	String cell20 = sheet.getCell(20, i).getContents();
//            	String cell21 = sheet.getCell(21, i).getContents();
//            	String cell22 = sheet.getCell(22, i).getContents();
//            	String cell23 = sheet.getCell(23, i).getContents();
//            	String cell24 = sheet.getCell(24, i).getContents();
//            	String cell25 = sheet.getCell(25, i).getContents();
//            	String cell26 = sheet.getCell(26, i).getContents();
//            	String cell27 = sheet.getCell(27, i).getContents();
//            	String cell28 = sheet.getCell(28, i).getContents();
//            	String cell29 = sheet.getCell(29, i).getContents();
//            	
//            	String rowString3 = "["+cell20 + "] [" + cell21 + "] [" + cell22 + "] [" + cell23 + "] [" + cell24 + "] [" + cell25 + "] [" + cell26 + "] [" + cell27 + "] [" + cell28 + "] [" + cell29 + "]";
            	//System.out.println("ROW[" + i + "]: " + rowString1 + rowString2 + rowString3);
            }
            
            if(carriers != null) {
            	System.out.println("Carrier count: " + carriers.size());
            }
            
            try{
                PrintWriter writer = new PrintWriter("carriers.sql", "UTF-8");
                for(String s : carriers) {
                	String sql = "insert into CARRIER (name_cyrilic, carrierplaceid) values ('" + s + "', '1');";
                	writer.println(sql);
                }
                writer.close();
            } catch (IOException e) {
               // do something
            }
            
//            for (int j = 0; j < columns; j++) {
//				for (int i = 0; i < rows; i++) {
//                    Cell cell = sheet.getCell(j, i);
//                    CellType type = cell.getType();
//                    if (type == CellType.LABEL) {
//                        System.out.println("I got a label " + cell.getContents());
//                    }
//
//                    if (type == CellType.NUMBER) {
//                        System.out.println("I got a number " + cell.getContents());
//                    }
//
//                }
//            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ReadExcel test = new ReadExcel();
        test.setInputFile("/home/vlado/Projects/Avtobusi/avtobus/linii.xls");
        test.readLinii();
        //test.readPlaces();
        //test.readCarriers();
    }

}