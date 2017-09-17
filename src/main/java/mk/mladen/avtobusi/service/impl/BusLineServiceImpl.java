package mk.mladen.avtobusi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.beans.UpdateBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.entity.BusLineEntity;
import mk.mladen.avtobusi.entity.CarrierEntity;
import mk.mladen.avtobusi.entity.PlaceEntity;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.util.DOW;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
					BusLineDto dto = new BusLineDto(ent.getId(),
							ent.getName(),
							ent.getDeparture().getName(), 
							ent.getDestination().getName(),
							ent.getCarrier().getName(),
							ent.getCarrier().getNameCyrilic(),
							ent.getPrice(),
							ent.getPriceReturn(),
							ent.getDepartureTime(),
							ent.getArrivalTime(),
							ent.getDist(),
							ent.getJurneyTime());
					dto.setOperationDays(ent.getOperationDays());
					dto.setOperationMonths(ent.getOperationMonths());
					dto.setOperationPeriod(ent.getOperationPeriod());
					dto.setComment(ent.getComment());
					dto.setTravelTime(ent.getJurneyTime());
					dto.setLineNumber(ent.getName());
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
		Date date = new Date();
		if(StringUtils.isNotBlank(dateString)) {
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			} catch (ParseException e1) {
				logger.debug("date parse exception");
				e1.printStackTrace();
			}
		}
		
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.setTimeZone(TimeZone.getTimeZone("CET"));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		String dow = String.valueOf(dayOfWeek);
		String realDow = DOW.getRealDayOfWeek(DOW.getDayByDayNumber(dow));
		
		List<BusLineDto> result = new ArrayList<BusLineDto>();
		List<BusLineEntity> entities = busLineDao.find(departure, destination, realDow);
		if(entities != null && !entities.isEmpty()) {
			try {
				for(BusLineEntity ent : entities) {
					CarrierEntity carrier = ent.getCarrier();
					PlaceEntity departurePlace = ent.getDeparture();
					PlaceEntity destinationPlace = ent.getDestination();
					BusLineDto dto = new BusLineDto(ent.getId(),
							ent.getName(),
							departurePlace.getName(), 
							destinationPlace.getName(),
							carrier == null ? null : carrier.getName(),
							carrier == null ? null : carrier.getNameCyrilic(),
							ent.getPrice(),
							ent.getPriceReturn(),
							ent.getDepartureTime(),
							ent.getArrivalTime(),
							ent.getDist(),
							ent.getJurneyTime());
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
	public void deleteBusLine(String id) {
		try {
			int blId = Integer.valueOf(id);
			busLineDao.delete(blId);
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}

	}

	@Override
	public void updateBusLine(UpdateBean bean) {
		try {
			int id = Integer.valueOf(bean.getId()).intValue();
			BusLineEntity ble = busLineDao.getById(id);
			String comment = bean.getComment();
			System.out.println("Comment: " + comment);
			ble.setComment(comment);
			updateBusLineEntity(ble, bean);

			busLineDao.persist(ble);
		}catch(NumberFormatException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void updateBusLineEntity(BusLineEntity ble, UpdateBean bean) {
		ble.setComment(bean.getComment());
		//ble.setDeparture(bean.getDeparturePlace());
	}

	@Override
	public void addNewBusLine(AddBean bean) {
		BusLineEntity ble = createNewBusLineEntity(bean);
		busLineDao.persist(ble);
	}

	private BusLineEntity createNewBusLineEntity(AddBean bean) {
		BusLineEntity ble = new BusLineEntity();
		ble.setComment(bean.getComment());
		//ble.setDeparture(bean.getDeparturePlace());
		return ble;
	}
}
