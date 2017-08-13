//package mk.mladen.avtobusi.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Iterator;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Test;
//
//public class ImportXlsFile {
//
//	@Test
//	public void test() {
//		try {
//			String excelFilePath = "/home/vlado/Projects/Avtobusi/avtobus/linii.xlsx";
//			System.out.println("file: " + excelFilePath);
//	        File file = new File(excelFilePath);
//	        System.out.println("file exists: " + file.exists());
//			FileInputStream inputStream = new FileInputStream(file);
//	         
//	        Workbook workbook = new XSSFWorkbook(inputStream);
//	        System.out.print("1");
//	        Sheet firstSheet = workbook.getSheetAt(0);
//	        System.out.print("2");
//	        Iterator<Row> iterator = firstSheet.iterator();
//	        System.out.print("3");
//	        
//	        int counter = 0;
//	        System.out.println("counter: " + counter);
//	        while (iterator.hasNext() && counter < 100) {
//	            Row nextRow = iterator.next();
//	            Iterator<Cell> cellIterator = nextRow.cellIterator();
//	             
//	            while (cellIterator.hasNext()) {
//	                Cell cell = cellIterator.next();
//	                 
//	                switch (cell.getCellType()) {
//	                    case Cell.CELL_TYPE_STRING:
//	                        System.out.print(cell.getStringCellValue());
//	                        break;
//	                    case Cell.CELL_TYPE_BOOLEAN:
//	                        System.out.print(cell.getBooleanCellValue());
//	                        break;
//	                    case Cell.CELL_TYPE_NUMERIC:
//	                        System.out.print(cell.getNumericCellValue());
//	                        break;
//	                }
//	                System.out.print(" - ");
//	            }
//	            System.out.println();
//	            counter++;
//	        }
//	         
//	        workbook.close();
//	        inputStream.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
