package mk.mladen.avtobusi.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private BusLineDao busLineDao;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private CarrierDao carrierDao;
	
	public void insertDataIntoHsqldbDb() {
		if(logger.isInfoEnabled()){
			logger.info("InsertDataServiceImpl insertDataIntoDerbyDb()");
		}
		
		long places = placeDao.countAll();
		if(logger.isInfoEnabled()){
			logger.info("places count: " + places);
		}
		
		if(places == 0) {
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
			
			PlaceEntity starDojran = new PlaceEntity("Star Dojran", "Стар Дојран");
			placeDao.persist(starDojran);
			
			PlaceEntity novDojran = new PlaceEntity("Nov Dojran", "Нов Дојран");
			placeDao.persist(novDojran);
			
			PlaceEntity probistip = new PlaceEntity("Probishtip", "Пробиштип");
			placeDao.persist(probistip);
			
			CarrierEntity samvel = new CarrierEntity("Samvel Kavadarci", "САМ-ВЕЛ Кавадарци", kavadarci);
			carrierDao.persist(samvel);
			
			CarrierEntity delfinaTurs = new CarrierEntity("Delfina Turs", "Делфина Турс", ohrid);
			carrierDao.persist(delfinaTurs);
			
			CarrierEntity ruleTurs = new CarrierEntity("Rule Turs", "Руле Турс", skopje);
			carrierDao.persist(ruleTurs);
			
			CarrierEntity dajoTurs = new CarrierEntity("Dajo Turs", "Дајо Турс", negotino);
			carrierDao.persist(dajoTurs);
			
			CarrierEntity transkop = new CarrierEntity("Transkop", "Транскоп", bitola);
			carrierDao.persist(transkop);
			
			CarrierEntity galeb = new CarrierEntity("Galeb", "Галеб", ohrid);
			carrierDao.persist(galeb);
			
			CarrierEntity klasikKompani = new CarrierEntity("Klasik Kompani", "Класик Компани", ohrid);
			carrierDao.persist(klasikKompani);
			
			CarrierEntity ekstraBus = new CarrierEntity("Ekstra Bus", "Екстра Бус", kavadarci);
			carrierDao.persist(ekstraBus);
			
			CarrierEntity kamSmarkDoo = new CarrierEntity("Kam Smark DOO", "Кам Смарк ДОО", kavadarci);
			carrierDao.persist(kamSmarkDoo);
			
			CarrierEntity pelagonijaTrans = new CarrierEntity("Pelagonija Trans", "Пелагонија Транс", prilep);
			carrierDao.persist(pelagonijaTrans);
			
			/* Skopje - Negotino */
			BusLineEntity bl = new BusLineEntity(skopje, negotino, "08:30", "09:55", "1,2,3,4,5,6,7", ruleTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "10:00", "11:25", "1,2,3,4,5", dajoTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "11:00", "12:30", "1,2,3,4,5,6,7", ruleTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "12:00", "13:25", "1,2,3,4,5,6,7", samvel);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "13:00", "14:15", "1,2,3,4,5,6", dajoTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "14:00", "15:30", "1,2,3,4,5,6,7", ruleTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "15:00", "16:25", "1,2,3,4,5,6,7", samvel);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "16:00", "17:40", "1,2,3,4,5,6,7", ruleTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "18:00", "19:10", "1,2,3,4,5,6,7", ruleTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "18:30", "20:00", "1,2,3,4,5", dajoTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "19:30", "21:25", "1,2,3,4,5,6,7", samvel);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, negotino, "20:30", "21:50", "7", dajoTurs);
			bl.setPrice("240");
			bl.setPriceReturn("370");
			busLineDao.persist(bl);
			
			/* Skopje - Ohrid */
			bl = new BusLineEntity(skopje, ohrid, "05:30", "08:30", "1,2,3,4,5,6,7", delfinaTurs);
			bl.setPrice("450");
			bl.setPriceReturn("680");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "06:00", "09:30", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("520");
			bl.setPriceReturn("750");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "06:00", "12:15", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("580");
			bl.setPriceReturn("870");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "07:00", "10:15", "1,2,3,4,5,6,7", delfinaTurs);
			bl.setPrice("450");
			bl.setPriceReturn("680");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "08:00", "11:30", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("520");
			bl.setPriceReturn("750");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "09:00", "12:15", "1,2,3,4,5,6,7", delfinaTurs);
			bl.setPrice("450");
			bl.setPriceReturn("680");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "10:00", "13:15", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("520");
			bl.setPriceReturn("750");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "11:00", "14:00", "1,2,3,4,5,6,7", klasikKompani);
			bl.setPrice("450");
			bl.setPriceReturn("630");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "12:00", "15:30", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("520");
			bl.setPriceReturn("750");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "12:30", "16:00", "1,2,3,4,5,6,7", klasikKompani);
			bl.setPrice("450");
			bl.setPriceReturn("630");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "14:00", "17:15", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("520");
			bl.setPriceReturn("750");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "14:45", "17:45", "1,2,3,4,5,6,7", delfinaTurs);
			bl.setPrice("450");
			bl.setPriceReturn("680");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "16:00", "19:00", "1,2,3,4,5,6,7", delfinaTurs);
			bl.setPrice("450");
			bl.setPriceReturn("680");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "16:30", "19:45", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("520");
			bl.setPriceReturn("750");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "16:30", "21:45", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("580");
			bl.setPriceReturn("870");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "18:30", "22:00", "1,2,3,4,5,6,7", galeb);
			bl.setPrice("520");
			bl.setPriceReturn("750");
			busLineDao.persist(bl);
			
			bl = new BusLineEntity(skopje, ohrid, "19:30", "22:45", "1,2,3,4,5,6,7", klasikKompani);
			bl.setPrice("450");
			bl.setPriceReturn("630");
			busLineDao.persist(bl);
		} 
	}
}
