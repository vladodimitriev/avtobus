package mk.mladen.avtobusi.dao;

import java.util.List;

import mk.mladen.avtobusi.entity.PlaceEntity;

public interface PlaceDao extends GenericDao<PlaceEntity> {

	public long countAll();

	public List<String> getAllPlacesNames();

	public List<String> getAllPlacesNames(String name);
	
	public List<String> getAllPlacesNamesByLanguage(String language);

	public PlaceEntity getByCyrilicName(String name);

	public List<String> getAllPlacesNamesByLanguageAndName(String language, String name);

    public List<String> getCommonPlaces(String language);

    public PlaceEntity getByName(String departurePlace);
}
