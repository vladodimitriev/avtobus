package mk.mladen.avtobusi.service;

import java.util.List;

import mk.mladen.avtobusi.entity.PlaceEntity;

public interface PlaceService {

	public List<PlaceEntity> findAllPlaces();
	
	public List<PlaceEntity> findAllPlacesByLanguage();
	
	public List<String> findAllPlacesNames();
	
	public List<String> findAllPlacesNames(String name, String lang);
	
	public List<String> findAllPlacesNamesByLanguage(String language);
	
	public List<String> findAllPlacesNamesByLanguageAndName(String language, String name);
	
}
