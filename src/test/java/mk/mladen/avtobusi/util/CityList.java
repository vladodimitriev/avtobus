package mk.mladen.avtobusi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.dao.CarrierDao;
import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.entity.BusLineEntity;
import mk.mladen.avtobusi.entity.CarrierEntity;
import mk.mladen.avtobusi.entity.PlaceEntity;

public class CityList {
	
	private static final String inputFile = "static/files/linii-good.xls";
	private static final String outputFile = "/home/vlado/Projects/Avtobusi/avtobus/files/all-places.txt";
	private static final String citiesFile = "static/files/cities.txt";
	private Set<String> citySet = new HashSet<String>();
	
	private static long ccc = 0l;
	
	public static void main(String[] args) throws BiffException, IOException {
		CityList cl = new CityList();
		cl.createCitySet();
		cl.run();
	}

	private void createCitySet() throws IOException {
		Path path = Paths.get(citiesFile);
    	Stream<String> stream = Files.lines(path);
    	Iterator<String> iterator = stream.iterator();
    	while(iterator.hasNext()) {
    		citySet.add(iterator.next());
    	}
    	stream.close();
	}

	private void run() throws BiffException, IOException {
		File inputWorkbook = new File(inputFile);
    	Workbook w = Workbook.getWorkbook(inputWorkbook);
        Sheet sheet = w.getSheet(0);

        int rows = sheet.getRows();
        Set<String> citySet = new HashSet<String>();
        String controller = "";
        String[] twoCities = null;
        String town1 = null;
        String town2 = null;
        
        String city1 = null;
		String city2= null;
        boolean trigger = false;
        boolean city1Set = false;
        Set<City> cities = new HashSet<City>();
        int cityCount = 0;
        int lineNumber = 0;
        String carrier = null;
        String daysOfWork = null;
        String towns = null;
        boolean conditionAccomplished = false;
        for (int i = 0; i < rows; i++) {
        	String cell0 = sheet.getCell(0, i).getContents();
        	String cell1 = sheet.getCell(1, i).getContents();
        	String cell2 = sheet.getCell(2, i).getContents();
        	String cell3 = sheet.getCell(3, i).getContents();
        	String cell4 = sheet.getCell(4, i).getContents();
        	String cell5 = sheet.getCell(5, i).getContents();
        	String cell6 = sheet.getCell(6, i).getContents();
        	String cell7 = sheet.getCell(7, i).getContents();
        	
        	if(StringUtils.isNotBlank(cell1)) {
        		try {
	        		int int1 = Integer.valueOf(cell1);
	        		if(conditionAccomplished(carrier, towns, lineNumber, cell0, cell2, int1)) {
	        			conditionAccomplished = true;
	        			createLines(cities);
	        			carrier = cell0;
	        			lineNumber = int1;
	        			daysOfWork = cell7;
	        			cityCount = 0;
	        			towns = cell2;
	        			cities.clear();
	        		} else {
	        			conditionAccomplished = false;
	        		}
        		} catch(NumberFormatException nfe) {
        			conditionAccomplished = false;
        			nfe.printStackTrace();
        		}
        	}
        	
        	if(conditionAccomplished && StringUtils.isNotBlank(cell5) && isCity(cell5)) {
        		cityCount++;
    			City city = new City(cell5, cell3, cityCount);
    			city.setCarrier(carrier);
    			city.setDaysOfWork(daysOfWork);
    			city.setDistance(Integer.valueOf(cell4));
    			cities.add(city);
    		}
        }
        
        createLines(cities);
        System.out.println("CCC: " + ccc);
	}
	
	private boolean conditionAccomplished(String carrier, String towns, int lineNumber, String cell0, String cell2,
			int int1) {
		if(cell0.equalsIgnoreCase(carrier) && cell2.equalsIgnoreCase(towns) && lineNumber == int1) {
			return false;
		}
		return true;
	}

	private void createLines(Set<City> cities) {
		if(cities.isEmpty()) {
        	return;
        }
		List<City> list1 = asSortedList(cities); 
		createBusLines(list1);
		List<City> list2 = asSortedReverseList(cities); 
		createBusLines(list2);
	}

	private void createBusLines(List<City> list) {
		if(list.isEmpty()) {
        	return;
        }
        City firstCity = list.get(0);
        int counter = 1;
        int size = list.size();
        while(counter < size) {
        	City city1 = list.get(counter);
        	createBusLine(firstCity, city1);
        	counter++;
        }
        list.remove(firstCity);
        createBusLines(list);
	}

	private void createBusLine(City city1, City city2) {
		if(city1.getName().trim().equalsIgnoreCase(city2.getName().trim())) {
			return;
		}
		BusLine bl = new BusLine();
		bl.setDeparturePlace(city1.getName());
		bl.setDestinationPlace(city2.getName());
		bl.setDepartureTime(city1.getTime());
		bl.setArrivalTime(city2.getTime());
		bl.setDaysOfWork(city1.getDaysOfWork());
		bl.setCarrier(city1.getCarrier());
		bl.setLineNumber(city1.getLineNumber());
		bl.setDistance(city2.getDistance() - city1.getDistance());
		System.out.println(bl);
		ccc = ccc + 1;
	}

	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  Collections.sort(list);
	  return list;
	}
	
	public static <T extends Comparable<? super T>> List<T> asSortedReverseList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  Collections.sort(list);
	  Collections.reverse(list);
	  return list;
	}

	private boolean isCity(String town) {
		if(citySet.contains(town)) {
			return true;
		}
		return false;
	}
}
