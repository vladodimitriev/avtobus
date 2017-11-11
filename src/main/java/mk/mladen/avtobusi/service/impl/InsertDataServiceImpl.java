package mk.mladen.avtobusi.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.dao.CarrierDao;
import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.entity.BusLineEntity;
import mk.mladen.avtobusi.entity.CarrierEntity;
import mk.mladen.avtobusi.entity.PlaceEntity;
import mk.mladen.avtobusi.service.InsertDataService;

@Service
@Transactional
public class InsertDataServiceImpl implements InsertDataService {

	private final static Logger logger = Logger.getLogger(InsertDataServiceImpl.class);

	private static final String inputFile = "linii-bad-small.xls";
	private static final String citiesFile = "cities.txt";

	@Autowired
	private PlaceDao placeDao;

	@Autowired
	private CarrierDao carrierDao;
	
	@Autowired
	private BusLineDao busLineDao;

	private int line_count = 0;
	
	private int blcounter = 0;

	private Set<String> citySet = new HashSet<String>();
	
	private Map<String, String> sinonimi = new HashMap<String, String>();

	public void insertDataIntoDb() {
		try {
			createCitySet();
			createSinonimi();
			insertCities();
			insertCarriers();
			createBusLines();
			System.out.println("BL count: " + blcounter);
		} catch (IOException | BiffException e) {
			e.printStackTrace();
		}
	}

	private void insertCarriers() throws IOException, BiffException {
		logger.info("insertCarriers()");
		System.out.println("insertCarriers");
		System.out.println("============================================================================================");
		URL url = getClass().getResource(inputFile);
		File inputWorkbook = new File(url.getPath());
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(0);
		int rows = sheet.getRows();
		for (int i = 0; i < rows; i++) {
			String cell0 = sheet.getCell(0, i).getContents();
			if (StringUtils.isNotBlank(cell0)) {
				String cyrilic = cell0.trim();
				cyrilic = createCyrillicName(i, cyrilic);
				CarrierEntity ce = carrierDao.getByCarrierName(cyrilic);
				if (ce == null) {
					CarrierEntity carrier = new CarrierEntity();
					carrier.setNameCyrilic(cyrilic);
					String latinName = OperationsUtil.createLatinName(cyrilic);
					carrier.setName(latinName);
					carrierDao.persist(carrier);
				}
			}
		}
		System.out.println("============================================================================================");
	}

	private String createCyrillicName(int counter, String cyrilic) {
		if(StringUtils.isBlank(cyrilic)) {
			return " ";
		}
		String result = cyrilic;
		result = result.replace(".","");
		result = result.replace(",","");
		result = result.replace("-"," ");
		String[] names = result.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String n : names) {
			if (StringUtils.isNotBlank(n)
				&& !"АД".equalsIgnoreCase(n.trim())
				&& !"ДОО".equalsIgnoreCase(n.trim())
				&& !"ДООЕЛ".equalsIgnoreCase(n.trim())) {
				
				sb.append(n + " ");
			}
		}
		String endresult = sb.toString().trim();
		return endresult;
	}

	@SuppressWarnings("unused")
	private void createBusLines() throws BiffException, IOException {
		logger.info("createBusLines()");
		URL url = getClass().getResource(inputFile);
		File inputWorkbook = new File(url.getPath());
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(0);

		int rows = sheet.getRows();
		System.out.println("rows: " + rows);
		List<City> cities = new ArrayList<City>();
		int cityCount = 0;
		int lineNumber = 0;
		String lineName = "";
		String carrier = null;
		String comment = "";
		String daysOfWork = null;
		String towns = null;
		boolean conditionAccomplished = false;

		boolean light1 = false;
		boolean light2 = false;
		for(int j = 5; j < 13; j++) {
			for (int i = 0; i < rows; i++) {
				String cell0 = sheet.getCell(0, i).getContents();  //carrier
				String cell1 = sheet.getCell(1, i).getContents();  //line number (not unique)
				String cell2 = sheet.getCell(2, i).getContents();  //first - last place / line name
				String cell3 = sheet.getCell(3, i).getContents();  //number of lines
				String cell4 = sheet.getCell(4, i).getContents();  //number of lines return
				String cell5 = sheet.getCell(5, i).getContents();  //time1
				String cell6 = sheet.getCell(6, i).getContents();  //time2
				String cell7 = sheet.getCell(7, i).getContents();  //time3
				String cell8 = sheet.getCell(8, i).getContents();  //time4
				String cell9 = sheet.getCell(9, i).getContents();  //time5
				String cell10 = sheet.getCell(10, i).getContents();//time6
				String cell11 = sheet.getCell(11, i).getContents();//time7
				String cell12 = sheet.getCell(12, i).getContents();//time8
				String cell13 = sheet.getCell(13, i).getContents();//distance
				String cell14 = sheet.getCell(14, i).getContents();//place
				String cell15 = sheet.getCell(15, i).getContents();//return time1
				String cell16 = sheet.getCell(16, i).getContents();//return time2
				String cell17 = sheet.getCell(17, i).getContents();//return time3
				String cell18 = sheet.getCell(18, i).getContents();//return time4
				String cell19 = sheet.getCell(19, i).getContents();//return time5
				String cell20 = sheet.getCell(20, i).getContents();//return time6
				String cell21 = sheet.getCell(21, i).getContents();//return time7
				String cell22 = sheet.getCell(22, i).getContents();//return time8
				String cell23 = sheet.getCell(23, i).getContents();//days of work or comment

				String timeCell = "";
				if(j == 5) {
					timeCell = cell5;
				} else if(j == 6) {
					timeCell = cell6;
				} else if(j == 7) {
					timeCell = cell7;
				} else if(j == 8) {
					timeCell = cell8;
				} else if(j == 9) {
					timeCell = cell9;
				} else if(j == 10) {
					timeCell = cell10;
				} else if(j == 11) {
					timeCell = cell11;
				} else if(j == 12) {
					timeCell = cell12;
				}

				//String carrier = "";
				if (StringUtils.isNotBlank(cell0)) {
					carrier = cell0;
				}
				
				if (StringUtils.isNotBlank(cell23)) {
					comment = cell23;
				}
				
				if (StringUtils.isNotBlank(cell2)) {
					lineName = cell2;
				}

				if (StringUtils.isNotBlank(cell14)) {
					light1 = true;
					daysOfWork = cell23;
					cityCount = 0;
				} else {
					light1 = false;
				}

				if (light1) {
					cityCount++;
					String cn = createCityCN(cell14.trim());
					if (StringUtils.isNotBlank(timeCell) && !timeCell.equals("/") && isCity(cn)) {
						City city = new City(cn, timeCell, cityCount);
						city.setCarrier(carrier);
						city.setDaysOfWork(daysOfWork);
						city.setComment(comment);
						city.setLineName(lineName);
						if(j > 4) {
							city.setRedenBroj(j - 4);
						} else {
							city.setRedenBroj(0);
						}
						Integer linenumber = null;
						try {
							linenumber = Integer.valueOf(cell1);
							city.setLineNumber(linenumber);
						} catch(NumberFormatException nfe) {
							linenumber = null;
						}

						try {
							if (StringUtils.isNotBlank(cell13)) {
								//city.setDistance(Integer.valueOf(cell13));
								city.setDist(Double.valueOf(cell13.trim()));
							}
						} catch (NumberFormatException nfe) {
							//
						}
						if (!cities.contains(city)) {
							cities.add(city);
						}
					}
					light2 = true;
				}
				if (!light1 && light2) {
					createLines(cities, 1);
					cities.clear();
				}
			}

			createLines(cities, 1);
		}

		////////////////////////////////////////////////
		for(int j = 15; j < 23; j++) {
			for (int i = rows-1; i >= 0; i--) {
				String cell0 = sheet.getCell(0, i).getContents();  //carrier
				String cell1 = sheet.getCell(1, i).getContents();  //line number (not unique)
				String cell2 = sheet.getCell(2, i).getContents();  //first - last place
				String cell3 = sheet.getCell(3, i).getContents();  //number of lines
				String cell4 = sheet.getCell(4, i).getContents();  //number of lines return
				String cell5 = sheet.getCell(5, i).getContents();  //time1
				String cell6 = sheet.getCell(6, i).getContents();  //time2
				String cell7 = sheet.getCell(7, i).getContents();  //time3
				String cell8 = sheet.getCell(8, i).getContents();  //time4
				String cell9 = sheet.getCell(9, i).getContents();  //time5
				String cell10 = sheet.getCell(10, i).getContents();//time6
				String cell11 = sheet.getCell(11, i).getContents();//time7
				String cell12 = sheet.getCell(12, i).getContents();//time8
				String cell13 = sheet.getCell(13, i).getContents();//distance
				String cell14 = sheet.getCell(14, i).getContents();//place
				String cell15 = sheet.getCell(15, i).getContents();//return time1
				String cell16 = sheet.getCell(16, i).getContents();//return time2
				String cell17 = sheet.getCell(17, i).getContents();//return time3
				String cell18 = sheet.getCell(18, i).getContents();//return time4
				String cell19 = sheet.getCell(19, i).getContents();//return time5
				String cell20 = sheet.getCell(20, i).getContents();//return time6
				String cell21 = sheet.getCell(21, i).getContents();//return time7
				String cell22 = sheet.getCell(22, i).getContents();//return time8
				String cell23 = sheet.getCell(23, i).getContents();//days of work

				String timeCell = "";
				if (j == 15) {
					timeCell = cell15;
				} else if (j == 16) {
					timeCell = cell16;
				} else if (j == 17) {
					timeCell = cell17;
				} else if (j == 18) {
					timeCell = cell18;
				} else if (j == 19) {
					timeCell = cell19;
				} else if (j == 20) {
					timeCell = cell20;
				} else if (j == 21) {
					timeCell = cell21;
				} else if (j == 22) {
					timeCell = cell22;
				}

				//String carrier = "";
				if (StringUtils.isNotBlank(cell0)) {
					carrier = cell0;
				} 
				
				if (StringUtils.isNotBlank(cell23)) {
					comment = cell23;
				}
				
				if (StringUtils.isNotBlank(cell2)) {
					lineName = cell2;
				}

				if (StringUtils.isNotBlank(cell14)) {
					light1 = true;
					//createLines(cities);
					daysOfWork = cell23;
					cityCount = 0;
					//cities.clear();
				} else {
					light1 = false;
				}

				if (light1) {
					cityCount++;
					String cn = createCityCN(cell14.trim());
					if (StringUtils.isNotBlank(timeCell) && !timeCell.equals("/") && isCity(cn)) {
						City city = new City(cn, timeCell, cityCount);
						city.setCarrier(carrier);
						city.setDaysOfWork(daysOfWork);
						city.setComment(comment);
						city.setLineName(lineName);
						if(j > 14) {
							city.setRedenBroj(j - 14);
						} else {
							city.setRedenBroj(0);
						}
						Integer linenumber = null;
						try {
							linenumber = Integer.valueOf(cell1);
							city.setLineNumber(linenumber);
						} catch(NumberFormatException nfe) {

						}
						try {
							if (StringUtils.isNotBlank(cell13)) {
								//city.setDistance(Integer.valueOf(cell13));
								city.setDist(Double.valueOf(cell13.trim()));
							}
						} catch (NumberFormatException nfe) {
							//
						}
						if (!cities.contains(city)) {
							cities.add(city);
						}
					}
					light2 = true;
				}
				if (!light1 && light2) {
					createLines(cities, 2);
					cities.clear();
					//carrier = null;
				}
			}
			createLines(cities, 2);
		}
	}

	private void createLines(List<City> cities, int order) {
		if (cities.isEmpty()) {
			return;
		}
		createBusLines(cities, order);
		
	}

	private void createBusLines(List<City> list, int order) {
		if (list.isEmpty()) {
			return;
		}
		City firstCity = list.get(0);
		int counter = 1;
		int size = list.size();
		List<City> smallplaces = new ArrayList<City>();
		smallplaces.add(firstCity);
		while (counter < size) {
			City city1 = list.get(counter);
			smallplaces.add(city1);
			createBusLine(firstCity, city1, smallplaces, order);
			counter++;
		}
		list.remove(firstCity);
		createBusLines(list, order);
	}


	private void createBusLine(City city1, City city2, List<City> smallplaces, int order) {
		if (!canCreateBusLine(city1, city2)) {
			return;
		}

		String daysOfWork = city1.getDaysOfWork();
		String city1Carrier = city1.getCarrier();
		String city2Carrier = city2.getCarrier();
		
		String city1Comment = city1.getComment();
		String city2Comment = city2.getComment();
		
		int city1RedenBroj = city1.getRedenBroj();
		int city2RedenBroj = city2.getRedenBroj();
		
		String city1LineName = city1.getLineName();
		String city2LineName = city2.getLineName();
		
		int city1LineNumber = city1.getLineNumber();
		int city2LineNumber = city2.getLineNumber();
		
		String city1Name = city1.getName();
		PlaceEntity pe1 = placeDao.getByCyrilicNameForInsert(city1Name);
		
		String city2Name = city2.getName();
		PlaceEntity pe2 = placeDao.getByCyrilicNameForInsert(city2Name);

		BusLineEntity ble = new BusLineEntity();
		ble.setDeparture(pe1);
		ble.setDestination(pe2);
		ble.setDepartureTime(city1.getTime());
		ble.setArrivalTime(city2.getTime());
		ble.setDist(city2.getDist() - city1.getDist());
		ble.setOperationDays(OperationsUtil.getOperationDays(daysOfWork));
		
		String smallplacesstr = createSmallPlaces(smallplaces);
		ble.setSmallPlaces(smallplacesstr);
		String lineName = createName(city1Name, city2Name);
		ble.setName(lineName);
		//ble.setOperationPeriod(OperationsUtil.getOperationPeriod(daysOfWork));
		//ble.setOperationMonths(OperationsUtil.getOperationMonths(daysOfWork));
		
		String comment = "";
		if(StringUtils.isNotBlank(city1Comment) && order == 1) {
			comment = city1Comment;
		} else if(StringUtils.isNotBlank(city2Comment) && order == 2) {
			comment = city2Comment;
		}
		ble.setComment(comment);
		
		int redenBroj = 0;
		int lineNumber = 0;
		String lineNameNonOfficial = "";
		if(order == 1) {
			redenBroj = city1RedenBroj;
			lineNumber = city1LineNumber;
			lineNameNonOfficial = city1LineName;
		} else if(order == 2) {
			redenBroj = city2RedenBroj;
			lineNumber = city2LineNumber;
			lineNameNonOfficial = city2LineName;
		}
		ble.setRedenBroj(redenBroj);
		ble.setLineName(lineNameNonOfficial);
		ble.setLineNumber(lineNumber);
		
		CarrierEntity ce = null;
		if(StringUtils.isNotBlank(city1Carrier) && order == 1) {
			city1Carrier = createCyrillicName(0, city1Carrier);
			ce = carrierDao.getByCarrierName(city1Carrier);
		} else if(StringUtils.isNotBlank(city2Carrier) && order == 2) {
			city2Carrier = createCyrillicName(0, city2Carrier);
			ce = carrierDao.getByCarrierName(city2Carrier);
		}
		
		if(ce != null) {
			ble.setCarrier(ce);
		}
		if(ble != null) {
			line_count = line_count + 1;
			busLineDao.persist(ble);
		}
		blcounter = blcounter + 1;
	}

	private String createName(String name1, String name2) {
		String name = name1 + " - " + name2;
		return name;
	}

	private String createSmallPlaces(List<City> smallplaces) {
		String smallplace = "";
		for(City sp : smallplaces) {
			smallplace = smallplace + sp.getName() + "=" + sp.getTime() + ",";
		}
		if(StringUtils.isNotBlank(smallplace)) {
			smallplace = smallplace.substring(0, smallplace.length() - 1);
		}
		return  smallplace;
	}

	private boolean canCreateBusLine(City city1, City city2) {
		if (city1.getName().trim().equalsIgnoreCase(city2.getName().trim())) {
			return false;
		}

		if(city1.getTime().trim().equalsIgnoreCase("/")
				|| city2.getTime().trim().equalsIgnoreCase("/")) {
			return false;
		}

		if(city1.getOrder() != city2.getOrder()) {
			return false;
		}
		return true;
	}



	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
		List<T> list = new ArrayList<T>(c);
		Collections.sort(list);
		return list;
	}

	public static List<City> asList(Collection<City> c) {
		List<City> list = new ArrayList<City>(c);
		return list;
	}

	public static <T extends Comparable<? super T>> List<T> asSortedReverseList(Collection<T> c) {
		List<T> list = new ArrayList<T>(c);
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

	private void insertCities() throws BiffException, IOException {
		logger.info("insertCities()");
		System.out.println("============================================================================================");
		long placeCount = placeDao.countAll();
		if(placeCount < 1) {
			URL url = getClass().getResource(inputFile);
			File inputWorkbook = new File(url.getPath());
			Workbook w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			int rows = sheet.getRows();
			for (int i = 0; i < rows; i++) {
				String cell14 = sheet.getCell(14, i).getContents();//place
				if (StringUtils.isNotBlank(cell14)) {
					String cyrilic = cell14.trim();
					String cn = createCityCN(cyrilic);
					if (isCity(cn)) {
						PlaceEntity pe = placeDao.getByCyrilicNameForInsert(cn);
						if (pe == null) {
							PlaceEntity pen = new PlaceEntity();
							pen.setNameCyrilic(cn);
							String latinName = OperationsUtil.createLatinName(cn);
							pen.setName(latinName);
							placeDao.persist(pen);
						}
					}
				}
			}
			System.out.println("============================================================================================");
		}
	}


	private String createCityCN(String name) {
		name = name.replace(".", " ");
		name = name.replace("-", "");
		if(sinonimi.containsKey(name)) {
			return sinonimi.get(name);
		}
		return name;
	}

	private boolean isCity(String town) {
		if (citySet.contains(town)) {
			return true;
		}
		return false;
	}

	private void createCitySet() throws IOException {
		URL url = getClass().getResource(citiesFile);
		Path path = Paths.get(url.getPath());
		Stream<String> stream = Files.lines(path);
		Iterator<String> iterator = stream.iterator();
		while (iterator.hasNext()) {
			String city = iterator.next();
			citySet.add(city);
		}
		stream.close();
	}

	private void createSinonimi() {
		sinonimi.put("Стар Дојран", "Стар Дојран");
		sinonimi.put("Ст Дојран", "Стар Дојран");
		sinonimi.put("С Дојран", "Стар Дојран");
		
		sinonimi.put("Нов Дојран", "Нов Дојран");
		sinonimi.put("Н Дојран", "Нов Дојран");
		
		sinonimi.put("Демир Хисар", "Демир Хисар");
		sinonimi.put("Д Хисар", "Демир Хисар");
		
		sinonimi.put("Крива Паланка", "Крива Паланка");
		sinonimi.put("Кр Паланка", "Крива Паланка");
		sinonimi.put("К Паланка", "Крива Паланка");
		sinonimi.put("Паланка", "Крива Паланка");
		
		sinonimi.put("Демир Капија", "Демир Капија");
		sinonimi.put("Д Капија", "Демир Капија");
		
		sinonimi.put("Свети Николе", "Свети Николе");
		sinonimi.put("Св Николе", "Свети Николе");
		sinonimi.put("С Николе", "Свети Николе");
		
		sinonimi.put("Македонски Брод", "Македонски Брод");
		sinonimi.put("Мак Брод", "Македонски Брод");
		sinonimi.put("М Брод", "Македонски Брод");
		sinonimi.put("Брод", "Македонски Брод");
		
		sinonimi.put("Македонска Каменица", "Македонска Каменица");
		sinonimi.put("Мак Каменица", "Македонска Каменица");
		sinonimi.put("М Каменица", "Македонска Каменица");
		sinonimi.put("Каменица", "Македонска Каменица");
		
	}

}
