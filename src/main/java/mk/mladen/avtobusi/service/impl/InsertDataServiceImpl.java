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
	
	public void insertDataIntoDerbyDb() {
		if(logger.isInfoEnabled()){
			logger.info("InsertDataServiceImpl insertDataIntoDerbyDb()");
		}
		
		long places = placeDao.countAll();
		if(logger.isInfoEnabled()){
			logger.info("places count: " + places);
		}
		
		if(places == 0) {
			PlaceEntity departure = new PlaceEntity("Skopje", "Скопје");
			placeDao.persist(departure);
			
			PlaceEntity destination = new PlaceEntity("Negotino", "Неготино");
			placeDao.persist(destination);
			
			PlaceEntity kavadarci = new PlaceEntity("Kavadarci", "Кавадарци");
			placeDao.persist(kavadarci);
			
			CarrierEntity carrier = new CarrierEntity("SamVel", "СамВел", kavadarci);
			carrierDao.persist(carrier);
			
			BusLineEntity bl1 = new BusLineEntity(departure, destination, "06:00", "07:30", "1,2,3,4,5,6,7", carrier);
			busLineDao.persist(bl1);
			
			BusLineEntity bl2 = new BusLineEntity(departure, destination, "08:00", "09:30", "1,2,3,4,5,6,7", carrier);
			busLineDao.persist(bl2);
			
			BusLineEntity bl3 = new BusLineEntity(departure, destination, "10:00", "11:30", "1,2,3,4,5", carrier);
			busLineDao.persist(bl3);
		} 
	}
}
