package mk.mladen.avtobusi.service;

import java.util.List;

import mk.mladen.avtobusi.entity.PlaceEntity;

public interface PlaceService {

	public List<PlaceEntity> findAllPlaces();
	
	public List<PlaceEntity> findAllPlacesByLanguage();
	
	public List<String> findAllPlacesNames();
	
	public List<String> findAllPlacesNames(String name);
	
	public List<String> findAllPlacesNamesByLanguage(String language);
	
}
