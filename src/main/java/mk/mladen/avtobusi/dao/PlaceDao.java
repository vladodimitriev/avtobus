package mk.mladen.avtobusi.dao;

import mk.mladen.avtobusi.entity.PlaceEntity;

import java.util.List;

public interface PlaceDao extends GenericDao<PlaceEntity> {

	public long countAll();

	public List<String> getAllPlacesNames();

	public List<String> getAllPlacesNames(String name);
	
	public List<String> getAllPlacesNamesByLanguage(String language);

	public PlaceEntity getByCyrilicName(String name);
	
	public PlaceEntity getByCyrilicNameForInsert(String name);

	public List<String> getAllPlacesNamesByLanguageAndName(String language, String name);
	
	public List<String> getAllPlacesCyrillicNamesByName(String language, String name);

    public List<String> getCommonPlaces(String language);

    public PlaceEntity getByName(String departurePlace);

	public List<String> getAllPlacesNamesByLanguageAndNameAndMask(String language, String name);
	
	public List<String> getAllPlacesCyrillicNamesByNameAndMask(String language, String name);

	public List<PlaceEntity> getAllPlaces(String name);
}
