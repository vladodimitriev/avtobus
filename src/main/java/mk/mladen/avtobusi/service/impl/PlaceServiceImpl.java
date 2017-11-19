package mk.mladen.avtobusi.service.impl;

import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.entity.BusLineEntity;
import mk.mladen.avtobusi.entity.PlaceEntity;
import mk.mladen.avtobusi.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;
	
	@Override

	public List<PlaceDto> findAllPlaces() {
		List<PlaceDto> result = new ArrayList<PlaceDto>();
		List<PlaceEntity> entities = placeDao.getAll();
		if(entities == null || entities.isEmpty()) {
			return result;
		}

		for(PlaceEntity entity : entities) {
			PlaceDto dto = new PlaceDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setNameCyrilic(entity.getNameCyrilic());
			dto.setImportance(entity.getImportance());
			dto.setSinonimi(entity.getSinonimi());
			result.add(dto);
		}

		return result;
	}
	
	@Override
	public List<PlaceEntity> findAllPlacesByLanguage() {
		return placeDao.getAll();
	}

	@Override
	public List<String> findAllPlacesNames() {
		return placeDao.getAllPlacesNames();
	}
	
	@Override
	public List<String> findAllPlacesNames(String name, String language) {
		return placeDao.getAllPlacesNames(name);
	}

	@Override
	public List<String> findAllPlacesNamesByLanguage(String language) {
		return placeDao.getAllPlacesNamesByLanguage(language);
	}
	
	@Override
	public List<String> findAllPlacesNamesByLanguageAndName(String language, String name) {
		List<String> places = null;
		if("MK".equalsIgnoreCase(language)) {
			places = placeDao.getAllPlacesCyrillicNamesByName(language, name);
		} else {
			places = placeDao.getAllPlacesNamesByLanguageAndName(language, name);
		}
		
		if(places == null || places.isEmpty()) {
			if("MK".equalsIgnoreCase(language)) {
				places = placeDao.getAllPlacesCyrillicNamesByNameAndMask(language, name);
			} else {
				places = placeDao.getAllPlacesNamesByLanguageAndNameAndMask(language, name);
			}
		}
		if(places != null) {
			Collections.sort(places);
		} else {
			places = new ArrayList<String>();
		}
		return places;
	}

	@Override
	public List<String> findCommonPlaces(String lang) {
		return placeDao.getCommonPlaces(lang);
	}

	@Override
	public PlaceDto findPlace(String place) {
		//PlaceEntity placeEntity = placeDao.getByName(place);
		return new PlaceDto();
	}

	@Override
	public List<PlaceDto> findPlaces(String place) {
		List<PlaceDto> result = new ArrayList<PlaceDto>();
		List<PlaceEntity> entities = placeDao.getAllPlaces(place);
		if(entities == null || entities.isEmpty()) {
			return result;
		}

		for(PlaceEntity entity : entities) {
			PlaceDto dto = new PlaceDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setNameCyrilic(entity.getNameCyrilic());
			dto.setImportance(entity.getImportance());
			dto.setSinonimi(entity.getSinonimi());
			//dto.setCountry(entity.getCountry() != null ? entity.getCountry().get);
			result.add(dto);
		}

		return result;
	}

	@Override
	public void updatePlace(PlaceDto placeDto) {
		try {
			int id = Integer.valueOf(placeDto.getId()).intValue();
			PlaceEntity ble = placeDao.getById(id);
			if(ble != null) {
				updatePlaceEntity(ble, placeDto);
				placeDao.persist(ble);
			}
		}catch(NumberFormatException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void updatePlaceEntity(PlaceEntity place, PlaceDto placeDto) {
		place.setName(placeDto.getName());
		place.setNameCyrilic(placeDto.getNameCyrilic());
		place.setImportance(placeDto.getImportance());
		place.setSinonimi(placeDto.getSinonimi());
	}

	@Override
	public void deletePlace(int id) {
		try {
			int blId = Integer.valueOf(id);
			placeDao.delete(blId);
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
	}
}
