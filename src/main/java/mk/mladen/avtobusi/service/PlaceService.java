package mk.mladen.avtobusi.service;

import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.entity.PlaceEntity;

import java.util.List;

public interface PlaceService {

	public List<PlaceDto> findAllPlaces();
	
	public List<PlaceEntity> findAllPlacesByLanguage();
	
	public List<String> findAllPlacesNames();
	
	public List<String> findAllPlacesNames(String name, String lang);
	
	public List<String> findAllPlacesNamesByLanguage(String language);
	
	public List<String> findAllPlacesNamesByLanguageAndName(String language, String name);

    public List<String> findCommonPlaces(String lang);

    public PlaceDto findPlace(String place);

	public List<PlaceDto> findPlaces(String place);

    public void updatePlace(PlaceDto placeDto);

	public void deletePlace(int id);
}
