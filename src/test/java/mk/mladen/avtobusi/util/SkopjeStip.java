package mk.mladen.avtobusi.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class SkopjeStip {

	private static final String inputFile = "/home/vlado/Projects/Avtobusi/avtobus/files/1014-skopje-stip.txt";
	private static final String outputFile = "/home/vlado/Projects/Avtobusi/avtobus/files/1014-skopje-stip.sql";
	
	public static void main(String[] args) throws IOException {
		SkopjeStip test = new SkopjeStip();
        test.readLines();
    }
	
	private void readLines() throws IOException {
    	Path path = Paths.get(inputFile);
    	Stream<String> stream = Files.lines(path);
    	Iterator<String> iterator = stream.iterator();
    	String sql = "";
    	while(iterator.hasNext()) {
    		String s = iterator.next();
    		sql = sql + createSqlQuery(s);
    	}
    	
    	Files.write(Paths.get(outputFile), sql.getBytes());
    	stream.close();
	}

	private String createSqlQuery(String s) throws IOException {
		String[] arr = s.split("    ");
		int length = arr.length;
		System.out.println("Length: " + length);
		if(length != 7) {
			return null;
		}
		String s1 = arr[0].trim();//vreme na poaganje
		String s2 = arr[1].trim();//vreme na pristignuvanje
		String s4 = arr[3].trim();//prevoznik
		String s5 = arr[4].trim();//cena eden pravec
		String s6 = arr[5].trim();//cena povratna
		String s7 = arr[6].trim();//koi denovi soobraca
		
		String op = generateOperationDaysString(s7);
		String carr = generateCarriedIdString(s4);
		System.out.println("Carrier: " + carr);
		
		String s8 = "1"; //departureplaceid
		String s9 = "5"; //destinationplaceid 
		String s10 = "1"; //carrierid
		
		String sql1 = "insert into BUSLINE (name, departuretime, arrivaltime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) ";
		String sql2 = "values ('Skopje-Stip','" + s1 + "','" + s2 + "','" + op + "','" + s5 + "','" + s6 + "','" + s8 + "','" + s9 + "','" + carr + "');";
		
		String sql = sql1 + sql2 + "\n";
		System.out.println(sql);
		return sql;
	}

	private String generateCarriedIdString(String s4) throws IOException {
		Path path = Paths.get("/home/vlado/Projects/Avtobusi/avtobus/files/0003-carriers.sql");
    	Stream<String> stream = Files.lines(path);
    	Iterator<String> iterator = stream.iterator();
    	while(iterator.hasNext()) {
    		String s = iterator.next();
    		if(s.contains(s4)) {
    			String id = s.substring(2, 4);
    			stream.close();
    			return id.trim();
    		}
    	}
    	stream.close();
		return null;
	}

	private String generateOperationDaysString(String s7) {
		if("ПОН; ВТО; СРЕ; ЧЕТ; ПЕТ; САБ; НЕД".equalsIgnoreCase(s7)) {
			return "1,2,3,4,5,6,7";
		} else if("ПОН; ВТО; СРЕ; ЧЕТ; ПЕТ; САБ".equalsIgnoreCase(s7)) {
			return "1,2,3,4,5,6";
		} else if("ПОН; ВТО; СРЕ; ЧЕТ; ПЕТ".equalsIgnoreCase(s7)) {
			return "1,2,3,4,5";
		} else if("САБ; НЕД".equalsIgnoreCase(s7)) {
			return "6,7";
		} else if("САБ".equalsIgnoreCase(s7)) {
			return "6";
		} else if("НЕД".equalsIgnoreCase(s7)) {
			return "7";
		}
		return null;
	}

}
