package mk.mladen.avtobusi.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class SkopjeVeles {

	private String inputFile;
	
	public static void main(String[] args) throws IOException {
		SkopjeVeles test = new SkopjeVeles();
        test.setInputFile("/home/vlado/Projects/Avtobusi/avtobus/files/1011-skopje-veles.txt");
        test.readLines();
        //test.testOperationDays();
        //test.smallTestAsterics();
        //test.readPlaces();
        //test.readCarriers();
    }

    private void testOperationDays() {
    	String s7 = " ПОН; ВТО; СРЕ; ЧЕТ; ПЕТ; САБ; НЕД";
		String gen = generateOperationDaysString(s7.trim());
		System.out.println("Days: " + gen);
	}

	private void smallTestAsterics() {
    	//* 8*/ insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Sam-Vel', 'САМВЕЛ КАВАДАРЦИ', '3');
    	//* 9*/ insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Ekstra bus', 'ЕКСТРА БУС', '3');
    	//*10*/ insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Kam Smark DOO', 'КАМ СМАРК ДОО', '3');
    	String s = "/*10*/ insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Sam-Vel', 'САМВЕЛ КАВАДАРЦИ', '3');";
    	String s4 = "САМВЕЛ КАВАДАРЦИ";
		if(s.contains(s4)) {
			String id = s.substring(2, 4);
			System.out.println("ID: " + id.trim());
		}
		
	}

	private void readLines() throws IOException {
    	Path path = Paths.get(inputFile);
    	Stream<String> stream = Files.lines(path);
    	Iterator<String> iterator = stream.iterator();
    	int counter = 0;
    	String sql = "";
    	while(iterator.hasNext()) {
    		String s = iterator.next();
    		sql = sql + createSqlQuery(s);
    	}
    	
    	Files.write(Paths.get("/home/vlado/Projects/Avtobusi/avtobus/files/1011-skopje-veles.sql"), sql.getBytes());
    	stream.close();
    	System.out.println("Counter: " + counter);
	}

	private String createSqlQuery(String s) throws IOException {
		String[] arr = s.split("   ");
		int length = arr.length;
		if(length != 7) {
			return null;
		}
		
		/*
		insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
		values ('Skopje-Negotino', '08:30', '09:55', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
		*/
		String s1 = arr[0].trim();//vreme na poaganje
		String s2 = arr[1].trim();//vreme na pristignuvanje
		String s4 = arr[3].trim();//prevoznik
		String s5 = arr[4].trim();//cena eden pravec
		String s6 = arr[5].trim();//cena povratna
		String s7 = arr[6].trim();//koi denovi soobraca
		
		String op = generateOperationDaysString(s7);
		String carr = generateCarriedIdString(s4);
		
		String s8 = "1"; //departureplaceid
		String s9 = "5"; //destinationplaceid 
		String s10 = "1"; //carrierid
		
		String sql1 = "insert into BUSLINE (name, departuretime, arrivaltime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) ";
		String sql2 = "values ('Skopje-Veles','" + s1 + "','" + s2 + "','" + op + "','" + s5 + "','" + s6 + "','" + s8 + "','" + s9 + "','" + carr + "');";
		
		String sql = sql1 + sql2 + "\n";
		
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
    			return id.trim();
    		}
    	}
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

	public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    
}
