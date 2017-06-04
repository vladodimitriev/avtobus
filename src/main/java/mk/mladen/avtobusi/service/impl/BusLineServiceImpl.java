package mk.mladen.avtobusi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.entity.BusLineEntity;
import mk.mladen.avtobusi.service.BusLineService;

@Service
@SuppressWarnings("unchecked")
public class BusLineServiceImpl implements BusLineService {
	
	private final static Logger logger = Logger.getLogger(BusLineServiceImpl.class);

	@Autowired
	private BusLineDao busLineDao;
	
	@Override
	public List<BusLineDto> getRelation(String departure, String destination) {
		List<BusLineDto> result = new ArrayList<BusLineDto>();
		List<BusLineEntity> entities = busLineDao.find(departure, destination);
		
		if(entities != null && !entities.isEmpty()) {
			try {
				for(BusLineEntity ent : entities) {
					BusLineDto dto = new BusLineDto(ent.getName(), 
							ent.getDeparture().getName(), 
							ent.getDestination().getName(),
							ent.getCarrier().getName(),
							ent.getCarrier().getNameCyrilic(),
							ent.getPrice(),
							ent.getDepartureTime(),
							ent.getArrivalTime());
					result.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(result);
		return result;
	}
	
	@Override
	public List<BusLineDto> getRelation(String departure, String destination, String dateString) {
		if(logger.isInfoEnabled()){
			logger.info("DATE: " + dateString);
		}
		Date date = new Date();
		if(StringUtils.isNotBlank(dateString)) {
			try {
				date = new SimpleDateFormat("dd/mm/yyyy").parse(dateString);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.setTimeZone(TimeZone.getTimeZone("CET"));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		String dow = String.valueOf(dayOfWeek);
		if(logger.isInfoEnabled()){
			logger.info("dayOfWeek: " + dayOfWeek);
		}
		
		List<BusLineDto> result = new ArrayList<BusLineDto>();
		List<BusLineEntity> entities = busLineDao.find(departure, destination, dow);
		if(entities != null && !entities.isEmpty()) {
			try {
				for(BusLineEntity ent : entities) {
					BusLineDto dto = new BusLineDto(ent.getName(), 
							ent.getDeparture().getName(), 
							ent.getDestination().getName(),
							ent.getCarrier().getName(),
							ent.getCarrier().getNameCyrilic(),
							ent.getPrice(),
							ent.getDepartureTime(),
							ent.getArrivalTime());
					result.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(result);
		return result;
	}
}