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

	private static final String inputFile = "linii-good.xls";
	private static final String citiesFile = "cities.txt";

	private Set<String> citySet = new HashSet<String>();

	@Autowired
	private PlaceDao placeDao;

	@Autowired
	private CarrierDao carrierDao;

	@Autowired
	private BusLineDao busLineDao;

	public void insertDataIntoHsqldbDb() {
		if (logger.isInfoEnabled()) {
			logger.info("InsertDataServiceImpl insertDataIntoDerbyDb()");
		}

		long places = placeDao.countAll();
		if (logger.isInfoEnabled()) {
			logger.info("places count: " + places);
		}

		if (places == 0) {
			insertCities();
			try {
				createCitySet();
				createBusLines();
			} catch (IOException | BiffException e) {
				e.printStackTrace();
			}
		} else {
			long bldCount = busLineDao.countBitolaSkopje();
			if (bldCount == 0l) {
				try {
					createCitySet();
					createBusLines();
				} catch (IOException | BiffException e) {
					e.printStackTrace();
				}
			}
		}
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

	@SuppressWarnings("unused")
	private void createBusLines() throws BiffException, IOException {
		logger.info("createBusLines()");
		URL url = getClass().getResource(inputFile);
		File inputWorkbook = new File(url.getPath());
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(0);

		int rows = sheet.getRows();
		Set<City> cities = new HashSet<City>();
		int cityCount = 0;
		int lineNumber = 0;
		String carrier = null;
		String daysOfWork = null;
		String towns = null;
		boolean conditionAccomplished = false;
		for (int i = 0; i < rows; i++) {
			String cell0 = sheet.getCell(0, i).getContents(); //
			String cell1 = sheet.getCell(1, i).getContents();
			String cell2 = sheet.getCell(2, i).getContents();
			String cell3 = sheet.getCell(3, i).getContents();
			String cell4 = sheet.getCell(4, i).getContents();
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

			String cell23 = sheet.getCell(23, i).getContents();//day of week


			if (StringUtils.isNotBlank(cell1)) {
				int int1 = Integer.valueOf(cell1);
				if (conditionAccomplished(carrier, towns, lineNumber, cell0, cell2, int1)) {
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
			}

			if (conditionAccomplished && StringUtils.isNotBlank(cell5) && isCity(cell5)) {
				cityCount++;
				City city = new City(cell5, cell3, cityCount);
				city.setCarrier(carrier);
				city.setDaysOfWork(daysOfWork);
				city.setDistance(Integer.valueOf(cell4));
				cities.add(city);
			}
		}

		createLines(cities);
	}

	private void createLines(Set<City> cities) {
		if (cities.isEmpty()) {
			return;
		}
		List<City> list1 = asSortedList(cities);
		createBusLines(list1);
		List<City> list2 = asSortedReverseList(cities);
		createBusLines(list2);
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

	private boolean conditionAccomplished(String carrier, String towns, int lineNumber, 
			String cell0, String cell2, int int1) {
		if (cell0.equalsIgnoreCase(carrier) 
				&& cell2.equalsIgnoreCase(towns) 
				&& lineNumber == int1) {
			return false;
		}
		return true;
	}

	private boolean isCitySkopje(City firstCity) {
		if ("Скопје".equalsIgnoreCase(firstCity.getName())) {
			return true;
		}
		return false;
	}

	private boolean isCityNegotino(City firstCity) {
		if ("Неготино".equalsIgnoreCase(firstCity.getName())) {
			return true;
		}
		return false;
	}

	private boolean isCityOhrid(City firstCity) {
		if ("Охрид".equalsIgnoreCase(firstCity.getName())) {
			return true;
		}
		return false;
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
		ble.setDistance(city2.getDistance() - city1.getDistance());
		ble.setOperationDays(getOperationDays(daysOfWork));
		ble.setCarrier(getCarrier(city1Carrier));
		busLineDao.persist(ble);
	}

	private CarrierEntity getCarrier(String city1Carrier) {
		CarrierEntity ce = carrierDao.getByCarrierName(city1Carrier);
		if (ce == null) {
			CarrierEntity carrier = new CarrierEntity();
			carrier.setNameCyrilic(city1Carrier);
			String latinName = createLatinName(city1Carrier);
			carrier.setName(latinName);
			return carrier;
		}
		return ce;
	}

	private boolean canCreateBusLine(City city1, City city2) {
		if (city1.getName().trim().equalsIgnoreCase(city2.getName().trim())) {
			return false;
		}

		if (isCitySkopje(city1)) {
			return false;
		}

		if (isCityNegotino(city1) && isCitySkopje(city2)) {
			return false;
		}

		if (isCityOhrid(city1) && isCitySkopje(city2)) {
			return false;
		}

		return true;
	}

	private String getOperationDays(String daysOfWork) {
		if (StringUtils.isNotBlank(daysOfWork)) {
			if (daysOfWork.trim().equalsIgnoreCase("Секојдневно")) {
				daysOfWork = "1,2,3,4,5,6,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Режим на одржување: Секојдневно")) {
				daysOfWork = "1,2,3,4,5,6,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секојдневна")) {
				daysOfWork = "1,2,3,4,5,6,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секој ден")) {
				daysOfWork = "1,2,3,4,5,6,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно, освен сабота")) {
				daysOfWork = "1,2,3,4,5,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен сабота")) {
				daysOfWork = "1,2,3,4,5,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен сабота")) {
				daysOfWork = "1,2,3,4,5,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Само во недела")) {
				daysOfWork = "7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно, освен недела")) {
				daysOfWork = "1,2,3,4,5,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секојдневно освен недела")) {
				daysOfWork = "1,2,3,4,5,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен недела")) {
				daysOfWork = "1,2,3,4,5,7";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секојден освен сабота и недела")) {
				daysOfWork = "1,2,3,4,5";
			} else if (daysOfWork.trim().equalsIgnoreCase("Секој ден освен сабота и недела")) {
				daysOfWork = "1,2,3,4,5";
			} else {
				daysOfWork = "1,2,3,4,5,6,7";
			}
		} else {
			daysOfWork = "1,2,3,4,5,6,7";
		}
		return daysOfWork;
	}

	private String createLatinName(String name) {
		return MacedonianToLatin.getInstance().translate(name);
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
		if (citySet.contains(town)) {
			return true;
		}
		return false;
	}

	private void insertCities() {
		PlaceEntity skopje = new PlaceEntity("Skopje", "Скопје");
		placeDao.persist(skopje);

		PlaceEntity bitola = new PlaceEntity("Bitola", "Битола");
		placeDao.persist(bitola);

		PlaceEntity prilep = new PlaceEntity("Prilep", "Прилеп");
		placeDao.persist(prilep);

		PlaceEntity kumanovo = new PlaceEntity("Kumanovo", "Куманово");
		placeDao.persist(kumanovo);

		PlaceEntity ohrid = new PlaceEntity("Ohrid", "Охрид");
		placeDao.persist(ohrid);

		PlaceEntity negotino = new PlaceEntity("Negotino", "Неготино");
		placeDao.persist(negotino);

		PlaceEntity kavadarci = new PlaceEntity("Kavadarci", "Кавадарци");
		placeDao.persist(kavadarci);

		PlaceEntity gevgelija = new PlaceEntity("Gevgelija", "Гевгелија");
		placeDao.persist(gevgelija);

		PlaceEntity tetovo = new PlaceEntity("Tetovo", "Тетово");
		placeDao.persist(tetovo);

		PlaceEntity gostivar = new PlaceEntity("Gostivar", "Гостивар");
		placeDao.persist(gostivar);

		PlaceEntity struga = new PlaceEntity("Struga", "Струга");
		placeDao.persist(struga);

		PlaceEntity kicevo = new PlaceEntity("Kichevo", "Кичево");
		placeDao.persist(kicevo);

		PlaceEntity kocani = new PlaceEntity("Kochani", "Кочани");
		placeDao.persist(kocani);

		PlaceEntity strumica = new PlaceEntity("Strumitsa", "Струмица");
		placeDao.persist(strumica);

		PlaceEntity stip = new PlaceEntity("Shtip", "Штип");
		placeDao.persist(stip);

		PlaceEntity resen = new PlaceEntity("Resen", "Ресен");
		placeDao.persist(resen);

		PlaceEntity veles = new PlaceEntity("Veles", "Велес");
		placeDao.persist(veles);

		PlaceEntity debar = new PlaceEntity("Debar", "Дебар");
		placeDao.persist(debar);

		PlaceEntity krivaPalanka = new PlaceEntity("Kriva Palanka", "Крива Паланка");
		placeDao.persist(krivaPalanka);

		PlaceEntity demirKapija = new PlaceEntity("Demir Kapija", "Демир Капија");
		placeDao.persist(demirKapija);

		PlaceEntity valandovo = new PlaceEntity("Valandovo", "Валандово");
		placeDao.persist(valandovo);

		PlaceEntity bogdanci = new PlaceEntity("Bogdantsi", "Богданци");
		placeDao.persist(bogdanci);

		PlaceEntity makedonskiBrod = new PlaceEntity("Makedonski Brod", "Македонски Брод");
		placeDao.persist(makedonskiBrod);

		PlaceEntity krusevo = new PlaceEntity("Krushevo", "Крушево");
		placeDao.persist(krusevo);

		PlaceEntity demirHisar = new PlaceEntity("Demir Hisar", "Демир Хисар");
		placeDao.persist(demirHisar);

		PlaceEntity radovis = new PlaceEntity("Radovish", "Радовиш");
		placeDao.persist(radovis);

		PlaceEntity delcevo = new PlaceEntity("Delchevo", "Делчево");
		placeDao.persist(delcevo);

		PlaceEntity berovo = new PlaceEntity("Berovo", "Берово");
		placeDao.persist(berovo);

		PlaceEntity makedonskaKamenica = new PlaceEntity("Makedonska Kamenitsa", "Македонска Каменица");
		placeDao.persist(makedonskaKamenica);

		PlaceEntity pehcevo = new PlaceEntity("Pehchevo", "Пехчево");
		placeDao.persist(pehcevo);

		PlaceEntity kratovo = new PlaceEntity("Kratovo", "Кратово");
		placeDao.persist(kratovo);

		PlaceEntity svetiNikole = new PlaceEntity("Sveti Nikole", "Свети Николе");
		placeDao.persist(svetiNikole);

		PlaceEntity dojran = new PlaceEntity("Dojran", "Дојран");
		placeDao.persist(dojran);

		PlaceEntity probistip = new PlaceEntity("Probishtip", "Пробиштип");
		placeDao.persist(probistip);

		PlaceEntity vinica = new PlaceEntity("Vinica", "Виница");
		placeDao.persist(vinica);
	}
}
