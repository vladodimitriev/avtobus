package mk.mladen.avtobusi.service.impl;

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
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class InsertDataServiceImpl implements InsertDataService {

	private final static Logger logger = Logger.getLogger(InsertDataServiceImpl.class);

	private static final String inputFile = "linii-bad-small.xls";
	private static final String citiesFile = "cities-bad-small.txt";

	@Autowired
	private PlaceDao placeDao;

	@Autowired
	private CarrierDao carrierDao;

	@Autowired
	private BusLineDao busLineDao;

	private int line_count = 0;

	private Set<String> citySet = new HashSet<String>();

	public void insertDataIntoHsqldbDb() {
		try {
			long placeCount = placeDao.countAll();
//			if(placeCount < 1) {
				createCitySet();
//				insertCities();
//				insertCarriers();
//				Date d1 = new Date();
//				long l1 = d1.getTime();
//				System.out.println("create lines time1: " + l1);
				createBusLines();
//				d1 = new Date();
//				long l2 = d1.getTime();
//				System.out.println("finish create lines time2: " + l2);
//				System.out.println("Time difference: " + (l2 - l1));
//				System.out.println("Bus line count: " + line_count);
//			}
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
			String cell0 = sheet.getCell(0, i).getContents();//carrier
			//System.out.println(cell0);
			if (StringUtils.isNotBlank(cell0)) {
				String cyrilic = cell0.trim();
				CarrierEntity ce = carrierDao.getByCarrierName(cyrilic);
				if (ce == null) {
					CarrierEntity carrier = new CarrierEntity();
					carrier.setNameCyrilic(cyrilic);
					String latinName = OperationsUtil.createLatinName(cyrilic);
					carrier.setName(latinName);
					carrierDao.persist(carrier);
					//System.out.println(carrier);
				}
			}
		}
		System.out.println("============================================================================================");
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
		String carrier = null;
		String daysOfWork = null;
		String towns = null;
		boolean conditionAccomplished = false;

		boolean light1 = false;
		boolean light2 = false;
		for(int j = 5; j < 13; j++) {
			for (int i = 0; i < rows; i++) {
				//logger.info("createBusLines i=" + i);
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

				if (StringUtils.isNotBlank(cell0)) {
					carrier = cell0;
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
					if (StringUtils.isNotBlank(timeCell) && !timeCell.equals("/") && isCity(cell14.trim())) {
						City city = new City(cell14, timeCell, cityCount);
						city.setCarrier(carrier);
						city.setDaysOfWork(daysOfWork);
						//logger.info("create lines, city = " + city);
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

					createLines(cities);
					cities.clear();
				}

			}

			createLines(cities);
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

				if (StringUtils.isNotBlank(cell0)) {
					carrier = cell0;
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
					if (StringUtils.isNotBlank(timeCell) && !timeCell.equals("/") && isCity(cell14.trim())) {
						City city = new City(cell14, timeCell, cityCount);
						city.setCarrier(carrier);
						city.setDaysOfWork(daysOfWork);
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
					createLines(cities);
					cities.clear();
				}

			}

			createLines(cities);
		}
	}

	private void createLines(List<City> cities) {
		if (cities.isEmpty()) {
			return;
		}
		//List<City> list1 = asSortedList(cities);
		createBusLines(cities);
		//List<City> list2 = asSortedReverseList(cities);
		//createBusLines(list2);
	}

	private void createBusLines(List<City> list) {
		if (list.isEmpty()) {
			return;
		}
		City firstCity = list.get(0);
		int counter = 1;
		int size = list.size();
		while (counter < size) {
			City city1 = list.get(counter);
			createBusLine(firstCity, city1);
			counter++;
		}
		list.remove(firstCity);
		createBusLines(list);
	}


	private void createBusLine(City city1, City city2) {
		if (!canCreateBusLine(city1, city2)) {
			return;
		}

		String daysOfWork = city1.getDaysOfWork();
		String city1Carrier = city1.getCarrier();

		PlaceEntity pe1 = placeDao.getByCyrilicName(city1.getName());
		PlaceEntity pe2 = placeDao.getByCyrilicName(city2.getName());

		BusLineEntity ble = new BusLineEntity();
		ble.setDeparture(pe1);
		ble.setDestination(pe2);
		ble.setDepartureTime(city1.getTime());
		ble.setArrivalTime(city2.getTime());
		ble.setDist(city2.getDist() - city1.getDist());
		ble.setOperationDays(OperationsUtil.getOperationDays(daysOfWork));
		ble.setComment(daysOfWork);
		//ble.setOperationPeriod(OperationsUtil.getOperationPeriod(daysOfWork));
		//ble.setOperationMonths(OperationsUtil.getOperationMonths(daysOfWork));
		CarrierEntity ce = carrierDao.getByCarrierName(city1Carrier);
		ble.setCarrier(ce);

		//System.out.println(ble);

		if(ble != null) {
			line_count = line_count + 1;
			//System.out.println(ble);
			busLineDao.persist(ble);
		}

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
			List list = placeDao.getAll();
			URL url = getClass().getResource(inputFile);
			File inputWorkbook = new File(url.getPath());
			Workbook w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			int rows = sheet.getRows();
			for (int i = 0; i < rows; i++) {
				String cell14 = sheet.getCell(14, i).getContents();//place
				if (StringUtils.isNotBlank(cell14)) {
					String cyrilic = cell14.trim();
					if (isCity(cyrilic)) {
						PlaceEntity pe = placeDao.getByCyrilicName(cyrilic);
						if (pe == null) {
							PlaceEntity pen = new PlaceEntity();
							pen.setNameCyrilic(cyrilic);
							String latinName = OperationsUtil.createLatinName(cyrilic);
							pen.setName(latinName);
							placeDao.persist(pen);
							System.out.println(pen);
						}
					}
				}
			}
			System.out.println("============================================================================================");
		}
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
			citySet.add(iterator.next());
		}
		stream.close();
	}


}
