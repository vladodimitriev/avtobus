package mk.mladen.avtobusi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.entity.PlaceEntity;
import mk.mladen.avtobusi.service.PlaceService;

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
	public List<String> findAllPlacesNames(String name) {
		return placeDao.getAllPlacesNames(name);
	}

	@Override
	public List<String> findAllPlacesNamesByLanguage(String language) {
		return placeDao.getAllPlacesNamesByLanguage(language);
	}

}
