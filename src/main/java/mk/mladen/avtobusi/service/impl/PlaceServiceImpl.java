package mk.mladen.avtobusi.service.impl;

import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.entity.PlaceEntity;
import mk.mladen.avtobusi.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;
	
	@Override
	public List<PlaceEntity> findAllPlaces() {
		return placeDao.getAll();
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
		List<String> places = placeDao.getAllPlacesNamesByLanguageAndName(language, name);
		if(places == null || places.isEmpty()) {
			places = placeDao.getAllPlacesNamesByLanguageAndNameAndMask(language, name);
		}
		Collections.sort(places);
		return places;
	}

	@Override
	public List<String> findCommonPlaces(String lang) {
		return placeDao.getCommonPlaces(lang);
	}

}
