package mk.mladen.avtobusi.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.entity.PlaceEntity;
import mk.mladen.avtobusi.service.InsertDataService;

@Service
@Transactional
public class InsertDataServiceImpl implements InsertDataService {
	
	private final static Logger logger = Logger.getLogger(InsertDataServiceImpl.class);

	@Autowired
	private PlaceDao placeDao;
	
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
		} 
	}
}
